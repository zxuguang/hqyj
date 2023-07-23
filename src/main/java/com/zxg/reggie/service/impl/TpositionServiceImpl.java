package com.zxg.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxg.reggie.entity.Tposition;
import com.zxg.reggie.mapper.TpositionMapper;
import com.zxg.reggie.service.TpositionService;
import org.springframework.stereotype.Service;

@Service
public class TpositionServiceImpl extends ServiceImpl<TpositionMapper, Tposition> implements TpositionService {
}
