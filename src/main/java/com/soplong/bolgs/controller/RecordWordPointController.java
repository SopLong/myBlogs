package com.soplong.bolgs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soplong.bolgs.pojo.WorkPointRecord;
import com.soplong.bolgs.pojo.system.ResultData;
import com.soplong.bolgs.service.WorkPointService;
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
@RestController
@RequestMapping("workPoint")
public class RecordWordPointController {

    @Autowired
    private WorkPointService workPointService;

    @PostMapping("saveWorkPoint")
    public ResultData saveWorkPoint(@RequestBody WorkPointRecord workPointRecord) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date day = simpleDateFormat.parse(workPointRecord.getDateTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);

        workPointRecord.setDateTime(workPointRecord.getDateTime().substring(0, 10));

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            System.out.println("周末");
            workPointRecord.setWeekendWorkHours(workPointRecord.getAllHours() < 8 ? workPointRecord.getAllHours() : 8);
            workPointRecord.setWeekendOverworkHours(workPointRecord.getAllHours() > 8 ? workPointRecord.getAllHours() - 8 : 0);
            workPointRecord.setNormalWorkHours(0);
            workPointRecord.setNormalOverworkHours(0);
        } else {
            System.out.println("工作日");
            workPointRecord.setNormalWorkHours(workPointRecord.getAllHours() < 8 ? workPointRecord.getAllHours() : 8);
            workPointRecord.setNormalOverworkHours(workPointRecord.getAllHours() > 8 ? workPointRecord.getAllHours() - 8 : 0);
            workPointRecord.setWeekendWorkHours(0);
            workPointRecord.setWeekendOverworkHours(0);
        }

        workPointService.insert(workPointRecord);

        return new ResultData();
    }

    @GetMapping("generarePayroll")
    public ResultData generarePayroll(String currentMonth) {
        List<WorkPointRecord> workPointRecords = workPointService.selectList(new EntityWrapper<WorkPointRecord>().like("date_time", currentMonth));

        double normalOverHours = workPointRecords.stream().mapToDouble(s -> s.getNormalOverworkHours()).sum();
        double normalHours = workPointRecords.stream().mapToDouble(s -> s.getNormalWorkHours()).sum();
        double weekOverHours = workPointRecords.stream().mapToDouble(s -> s.getWeekendOverworkHours()).sum();
        double weekHours = workPointRecords.stream().mapToDouble(s -> s.getWeekendWorkHours()).sum();

        System.out.println("正常工作：" + normalHours * 7.8);
        System.out.println("正常加班：" + normalOverHours * 11.7);

        System.out.println("周末正常工作：" + weekHours * 15.6);
        System.out.println("周末加班：" + weekOverHours * 23.4);

        System.out.println("总工资：" + ((normalHours * 7.8) + (normalOverHours * 11.7) + (weekHours * 15.6) + (weekOverHours * 23.4)));
        return new ResultData();
    }
}
