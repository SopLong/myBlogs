package com.soplong.bolgs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soplong.bolgs.mapper.WorkPointMapper;
import com.soplong.bolgs.pojo.WorkPointRecord;
import com.soplong.bolgs.service.WorkPointService;
import org.springframework.stereotype.Service;

/**
 * Created by SopLong on 2019/8/4.
 */
@Service
public class WorkPointServiceImpl extends ServiceImpl<WorkPointMapper, WorkPointRecord> implements WorkPointService {
}
