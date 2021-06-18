package com.yinse.configcenter.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;

public class QueryWrapperUtils<T> {

    //根据条件 查询翻页数据
    public   QueryWrapper getQueryWrapper(HashMap<String, Object> dataFieldMap, T obj) throws IllegalAccessException {
        QueryWrapper queryWrapper=new QueryWrapper<>();

        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String name = declaredField.getName();
            String value = String.valueOf(declaredField.get(obj)).trim();
            //数据库字段
            String annName=(String)dataFieldMap.get(name);
            if(StringUtils.isNotBlank(annName)&&StringUtils.isNotBlank(value)&&!value.equals("null")){
                String mainId=declaredFields[1].getName();
                if(name.toLowerCase().equals(mainId.toLowerCase())||name.toLowerCase().contains("password")){
                    queryWrapper.eq(annName,value);
                }else {
                    queryWrapper.like(annName,value);
                }
            }
        }
        return queryWrapper;
    }
    //根据 id 更新 删除数据
    public  QueryWrapper getUpdateDeleteQueryWrapper(HashMap<String, Object> dataFieldMap, T obj) throws IllegalAccessException {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String name = declaredField.getName();
            String value = String.valueOf(declaredField.get(obj)).trim();
            //数据库字段
            String annName=(String)dataFieldMap.get(name);
            if(StringUtils.isNotBlank(annName)&&StringUtils.isNotBlank(value)&&!value.equals("null")){
                String mainId=declaredFields[1].getName();
                if(name.toLowerCase().equals(mainId.toLowerCase())){
                    queryWrapper.eq(annName,value);
                }
            }
        }
        return queryWrapper;
    }

}
