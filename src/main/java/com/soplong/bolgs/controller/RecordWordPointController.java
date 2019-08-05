package com.soplong.bolgs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soplong.bolgs.pojo.WorkPointRecord;
import com.soplong.bolgs.pojo.system.ResultData;
import com.soplong.bolgs.service.WorkPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by SopLong on 2019/8/4.
 */
@Slf4j
@RestController
@RequestMapping("workPoint")
public class RecordWordPointController {

    @Autowired
    private WorkPointService workPointService;

    @PostMapping("saveWorkPoint")
    public ResultData saveWorkPoint(@RequestBody WorkPointRecord workPointRecord){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date day = simpleDateFormat.parse(workPointRecord.getDateTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(day);

            WorkPointRecord workPointRecorded = workPointService.selectOne(new EntityWrapper<WorkPointRecord>().eq("date_time", workPointRecord.getDateTime().substring(0, 10)).eq("del_flag", 0));

            if(null == workPointRecorded){
                workPointRecorded = new WorkPointRecord();
                BeanUtils.copyProperties(workPointRecord,workPointRecorded);
            }else{
                workPointRecorded.setAllHours(workPointRecord.getAllHours());
            }

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                workPointRecorded.setWeekendWorkHours(workPointRecord.getAllHours() < 8 ? workPointRecord.getAllHours() : 8);
                workPointRecorded.setWeekendOverworkHours(workPointRecord.getAllHours() > 8 ? workPointRecord.getAllHours() - 8 : 0);
                workPointRecorded.setNormalWorkHours(0);
                workPointRecorded.setNormalOverworkHours(0);
                workPointRecorded.setIsWeek(1);
            } else {
                workPointRecorded.setNormalWorkHours(workPointRecord.getAllHours() < 8 ? workPointRecord.getAllHours() : 8);
                workPointRecorded.setNormalOverworkHours(workPointRecord.getAllHours() > 8 ? workPointRecord.getAllHours() - 8 : 0);
                workPointRecorded.setWeekendWorkHours(0);
                workPointRecorded.setWeekendOverworkHours(0);
                workPointRecorded.setIsWeek(0);
            }
            workPointRecorded.setDelFlag(0);
            workPointService.insertOrUpdate(workPointRecorded);
        }catch (Exception e){
            log.error("工作时长保存失败:",e);
            throw new RuntimeException("保存失败!");
        }
        return new ResultData();
    }

    @GetMapping("generarePayroll")
    public ResultData generarePayroll(String currentMonth) {
        List<WorkPointRecord> workPointRecords = workPointService.selectList(new EntityWrapper<WorkPointRecord>().like("date_time", currentMonth));

        double normalOverHours = workPointRecords.stream().mapToDouble(s -> s.getNormalOverworkHours()).sum();
        double normalHours = workPointRecords.stream().mapToDouble(s -> s.getNormalWorkHours()).sum();
        double weekOverHours = workPointRecords.stream().mapToDouble(s -> s.getWeekendOverworkHours()).sum();
        double weekHours = workPointRecords.stream().mapToDouble(s -> s.getWeekendWorkHours()).sum();

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("normalFee",(double)Math.round((normalHours * 7.8)*100)/100);
        returnMap.put("normalOverFee",(double)Math.round((normalOverHours * 11.7)*100)/100);
        returnMap.put("weekFee",(double)Math.round((weekHours * 15.6)*100)/100);
        returnMap.put("weekOverFee",(double)Math.round((weekOverHours * 23.4)*100)/100);
        returnMap.put("allFee",(double)Math.round(((normalHours * 7.8) + (normalOverHours * 11.7) + (weekHours * 15.6) + (weekOverHours * 23.4))*100)/100);
        return new ResultData(returnMap);
    }

    /**
     * 获取每月工资信息
     * @param currentMonth
     * @return
     */
    @GetMapping("getMonthInfo")
    public ResultData getMonthInfo(String currentMonth){

        List<Map<String, Object>> list = workPointService.selectMaps(new EntityWrapper<WorkPointRecord>().setSqlSelect("case when is_week = 0 then concat( '工作:', all_hours,'\\n', '加班:', normal_overwork_hours)\n" +
                "\telse concat('工作:', all_hours,'\\n', '加班', weekend_overwork_hours) end as title,\n" +
                "\tdate_time as start,\n" +
                "\tdate_time as end,\n" +
                "\t'red' as cssClass ").like("date_time", currentMonth));

        return new ResultData(list);
    }
}
