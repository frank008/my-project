package com.yinse.datacenter.mysqldata.controller;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinse.configcenter.entity.AthResponseEnhance;
import com.yinse.configcenter.entity.AthRequest;
import com.yinse.configcenter.entity.QueryWrapperUtils;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.lang.*;

import com.yinse.datacenter.mysqldata.service.IPermissionService;
import com.yinse.datacenter.mysqldata.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author frank
 * @since 2021-06-17
 */
@Api(tags = {"权限设置接口"})
@RestController
@RequestMapping("/yinseApi")
public class PermissionController {
private final Logger logger=LoggerFactory.getLogger(PermissionController.class);

@Autowired
public IPermissionService iPermissionService;


//获取数据库字段名
private static final HashMap<String, Object> dataFieldMap=new HashMap<>();
static {
        Field[]fields=Permission.class.getDeclaredFields();
        for(Field field:fields){
        field.setAccessible(true);
        String keyName=field.getName();
        try{
            String annName=null;
            if(keyName.toLowerCase().contains("id")){
                try{
                    annName=field.getDeclaredAnnotation(TableId.class).value();
                }catch(Exception e){
                    annName=field.getDeclaredAnnotation(TableField.class).value();
                }
                }else{
                    annName=field.getDeclaredAnnotation(TableField.class).value();
                }
                 dataFieldMap.put(keyName,annName);
        }catch(Exception e){
                }
        }
}

/**
 * 分页查询数据
 *
 * @param  权限设置  分页信息
 * @param AthRequest<Permission> queryParams 查询条件
 * @return
 */
@ApiOperation(value = "权限设置 - 查询列表")
@ResponseBody
@PostMapping("/queryPermissionPageList")
public AthResponseEnhance<Permission> getPermissionList(@RequestBody AthRequest<Permission> queryParams){
        try{
            Permission queryParamPermission = queryParams.getData();
            long currentPage = queryParams.getCurrentPage();
            long pageSize = queryParams.getPageSize();
            //查询条件
            QueryWrapper queryWrapperPermission=new QueryWrapperUtils<Permission>().getQueryWrapper(dataFieldMap,queryParamPermission);

            //创建翻页条件
            Page<Permission> page=new Page<>(currentPage,pageSize);
            Page<Permission> pagePermission=iPermissionService.page(page,queryWrapperPermission);

            AthResponseEnhance athResponse=AthResponseEnhance.ok();
            athResponse.setMessage("查询列表成功!");
            athResponse.setPageSize(pagePermission.getSize());
            athResponse.setCurrentPage(pagePermission.getCurrent());
            athResponse.setCount(pagePermission.getTotal());
            athResponse.setData(pagePermission.getRecords());
            return athResponse;
        }catch(Exception e){
            logger.error("查询列表异常："+e.toString());
            return AthResponseEnhance.error().setMessage("查询列表失败！");
        }
}

/**
 * 权限设置 更新数据
 * @param AthRequest<Permission> queryParams 更新数据
 * @return
 */
@ApiOperation(value = "权限设置 - 更新数据" ,notes = "必输字段: id")

@PostMapping("/updatePermission")
public AthResponseEnhance<Permission> updatePermission(@RequestBody AthRequest<Permission> queryParams){
        try{
            Permission queryParamPermission = queryParams.getData();
            QueryWrapper queryWrapperPermission=new QueryWrapperUtils<Permission>().getUpdateDeleteQueryWrapper(dataFieldMap,queryParamPermission);
            boolean update =iPermissionService.update(queryParamPermission,queryWrapperPermission);
            if(update){
                return AthResponseEnhance.ok().setMessage("更新数据成功!");
            }
        }catch(Exception e){
            logger.error("更新数据异常："+e.toString());
        }
        return AthResponseEnhance.error().setMessage("更新数据失败！");
}

/**
 * 权限设置 新增数据
 * @param AthRequest<Permission> queryParams 新增数据
 * @return
 */
@ApiOperation(value = "权限设置 - 新增数据")
@PostMapping("/addPermission")
public AthResponseEnhance<Permission>  addPermission(@RequestBody AthRequest<Permission> queryParams){
        try{
            Permission queryParamPermission = queryParams.getData();
            boolean save=iPermissionService.save(queryParamPermission);
            if(save){
                return AthResponseEnhance.ok().setMessage("数据保存成功!");
            }
        }catch(Exception e){
            logger.error("数据保存异常："+e.toString());
        }
        return AthResponseEnhance.error().setMessage("数据保存失败！");
 }

/**
 * 权限设置 删除数据
 * @param AthRequest<Permission> queryParams 删除数据
 * @return
 */
@ApiOperation(value = "权限设置 - 删除数据",notes = "必输字段: id")
@PostMapping("/deletePermission")
public AthResponseEnhance<Permission> deletePermission(@RequestBody AthRequest<Permission> queryParams){
    try {
        Permission queryParamPermission = queryParams.getData();
        QueryWrapper queryWrapperPermission=new QueryWrapperUtils<Permission>().getUpdateDeleteQueryWrapper(dataFieldMap,queryParamPermission);
        boolean remove=iPermissionService.remove(queryWrapperPermission);
        if(remove){
            return AthResponseEnhance.ok().setMessage("数据删除成功!");
        }
        } catch (Exception e) {
        logger.error("数据删除失败!");
        e.printStackTrace();
    }
    return AthResponseEnhance.error().setMessage("数据删除失败!");
}

}
