package com.zhilutec.uwb.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhilutec.uwb.entity.Coordinate;
import com.zhilutec.uwb.service.ICoordinateService;
import com.zhilutec.uwb.service.IRedisService;
import com.zhilutec.uwb.util.ConstantUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class CoordinateServiceImpl implements ICoordinateService {

    @Resource
    IRedisService redisService;


    @Override
    public Coordinate getCache(Long tagId) {
        Coordinate coordinate = null;
        String key = redisService.genRedisKey(ConstantUtil.COORDINATE_KEY_PRE, tagId);
        Map map = redisService.hashGetMap(key);
        if (map == null) {
            return coordinate;
        }
        String mapStr = JSONObject.toJSONString(map);
        coordinate = JSONObject.parseObject(mapStr, Coordinate.class);
        return coordinate;
    }


    @Override
    public void addRedisCoordinate(Coordinate coordinate, Long expire) {
        Long tagId = coordinate.getTagId();
        String keyPre = ConstantUtil.COORDINATE_KEY_PRE;
        String key = redisService.genRedisKey(keyPre, tagId);

        String str = JSON.toJSONString(coordinate);
        Map map = JSONObject.parseObject(str, Map.class);
        redisService.hashAddMap(key, map, ConstantUtil.REDIS_DEFAULT_TTL);
    }

}
