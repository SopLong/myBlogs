package com.soplong.bolgs.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soplong.bolgs.pojo.system.SysCode;
import com.soplong.bolgs.pojo.system.ResultData;
import com.soplong.bolgs.service.system.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by SopLong on 2019/8/18.
 */
@RestController
@RequestMapping("sysCode")
public class SysCodeController {

    @Autowired
    private SysCodeService sysCodeService;

    /**
     * 获取字典下拉数据
     * @param type
     * @return
     */
    @GetMapping("getCodeDropList/{type}")
    public ResultData getCodeDropList(@PathVariable String type){
        List<Map<String, Object>> maps = sysCodeService.selectMaps(new EntityWrapper<SysCode>()
                .setSqlSelect("item_code as value,item_name as name")
                .eq("del_flag", 0)
                .eq("type", type));
        return new ResultData(maps);
    }
}
