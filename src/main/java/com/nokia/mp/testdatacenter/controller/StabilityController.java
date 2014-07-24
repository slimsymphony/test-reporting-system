package com.nokia.mp.testdatacenter.controller;

import com.nokia.mp.testdatacenter.utils.*;

import java.util.HashMap;
import java.util.List.*;
import java.util.*;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.nokia.mp.testdatacenter.service.StabilityService;
/**
 *
 * @author alex.2.wu
 */
@Controller
@RequestMapping("/api")
public class StabilityController {
    private Log logger = LogFactory.getLog(ContextLoader.class);

    @Resource(name="stabilityStatusService")
    private StabilityService stabilityService;

    @RequestMapping(value = "/stability/list/{product}/{testware}/{trigger}/{start}/{end}/{scale}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getStabilityFigures( @PathVariable String product,
                                                @PathVariable String testware,
                                                @PathVariable String trigger,
                                                @PathVariable String start,
                                                @PathVariable String end,
                                                @PathVariable String scale) {
        logger.info("getStabilityFigures() is started...");

        int year = Integer.parseInt(start.substring(0, 4));
        int startweek = Utils.getWeekOfYear(start);
        int endweek = Utils.getWeekOfYear(end);

        int fail=0;
        int pass=0;
        int wk=0;
        int wk_relative=0;

        String wk_start="";
        String wk_end="";

        long wk_start_l=0;  // start time in seconds
        long wk_end_l=0;    // end time in seconds

        int record_tmp=0;
        int record_num=0;  // total number of excution




        // used to return the result
        Map<String, Object> ret = new HashMap<String, Object>();
        LinkedList<Object> data = new LinkedList<Object>();

        try {
            // get data from DB
            List<List<String>> tableData = stabilityService.findExecutionsByConditions(testware, product, trigger, start, end);

            wk=startweek;
            wk_start = Utils.getStartDayOfWeekNo(year, wk)+" 00:00:00";
            wk_end = Utils.getEndDayOfWeekNo(year, wk)+" 23:59:59";
            wk_start_l = Utils.str2timeLong(wk_start);
            wk_end_l = Utils.str2timeLong(wk_end);
            logger.info("" + wk + "(" + wk_start +"->" + wk_end + ")");
            record_num = tableData.size();

            // if starttime is far before the first record
            while(Utils.str2timeLong(tableData.get(0).get(3))> wk_end_l) {
                Map<String, Object> line = new HashMap<String, Object>();
                Map<String, Integer> figs = new HashMap<String, Integer>();

                figs.put("pass", 0);
                figs.put("fail", 0);
                line.put("wk "+wk, figs);
                data.add(line);

                wk++;
                wk_start = Utils.getStartDayOfWeekNo(year, wk)+" 00:00:00";
                wk_end = Utils.getEndDayOfWeekNo(year, wk)+" 23:59:59";
                wk_start_l = Utils.str2timeLong(wk_start);
                wk_end_l = Utils.str2timeLong(wk_end);
                logger.info("==> before week " + wk + " , no result");
            }

            for(record_tmp=0; record_tmp<record_num; record_tmp++) {
                String str_start = tableData.get(record_tmp).get(3);  // get the start time in String
                if(Utils.str2timeLong(str_start)>wk_end_l) {
                    // go to next week
                    Map<String, Object> line = new HashMap<String, Object>();
                    Map<String, Integer> figs = new HashMap<String, Integer>();

                    figs.put("pass", pass);
                    figs.put("fail", fail);
                    line.put("wk "+wk, figs);
                    data.add(line);

                    // initialize data
                    pass=0;
                    fail=0;
                    wk++;
                    wk_start = Utils.getStartDayOfWeekNo(year, wk)+" 00:00:00";
                    wk_end = Utils.getEndDayOfWeekNo(year, wk)+" 23:59:59";
                    wk_start_l = Utils.str2timeLong(wk_start);
                    wk_end_l = Utils.str2timeLong(wk_end);
                    logger.info("==> in week " + wk + "(" + wk_start +"->" + wk_end + ")");
                }

                String str_status = tableData.get(record_tmp).get(2);
                if(str_status.equals("ok")) {
                    pass++;
                }
                else {
                    fail++;
                }
            }

            if((pass+fail)>0) {
                Map<String, Object> line = new HashMap<String, Object>();
                Map<String, Integer> figs = new HashMap<String, Integer>();

                figs.put("pass", pass);
                figs.put("fail", fail);
                line.put("wk "+wk, figs);
                data.add(line);
            }

            // if the end time if far after last record
            int last_record_wk = Utils.getWeekOfYear(tableData.get(record_num-1).get(3).substring(0, 10));
            int last_target_wk =  Utils.getWeekOfYear(end);
            while( last_record_wk < last_target_wk) {
                last_record_wk++;

                Map<String, Object> line = new HashMap<String, Object>();
                Map<String, Integer> figs = new HashMap<String, Integer>();

                figs.put("pass", 0);
                figs.put("fail", 0);
                line.put("wk "+last_record_wk, figs);
                data.add(line);

                logger.info("==>after week " + last_record_wk + " , no result");
            }




            ret.put("records", tableData.size());
            ret.put("data", data);
            ret.put("success", "true");
        } catch (Exception e) {
            logger.info("findExecutionStatusByAllConditions() got exception: " + e.getMessage());
            ret.put("success", "false");
        }
        logger.info("getStabilityFigures() is end...");
        return ret;
    }
}