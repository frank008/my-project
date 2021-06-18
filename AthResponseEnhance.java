package com.yinse.configcenter.entity;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AthResponseEnhance<T>{

    @ApiModelProperty(value = "状态码")
    private Integer  code;
    @ApiModelProperty(value = "响应消息")
    private String message;

    @ApiModelProperty(value = "每页显示条数")
    private long pageSize;

    @ApiModelProperty(value = "当前页")
    private long currentPage;

    @ApiModelProperty(value = "总条数")
    private long count;


    @ApiModelProperty(value = "响应JSON数组")
    private List<T> records;

    @ApiModelProperty(value = "例子")
    private Map<String,Object> data=new HashMap<>();

    private AthResponseEnhance(){ };
    public static AthResponseEnhance ok(){
        AthResponseEnhance r = new AthResponseEnhance();
        r.setCode(HttpStatus.OK.value());
        r.setMessage("成功!");
        return r; }
    public static AthResponseEnhance error(){
        AthResponseEnhance r = new AthResponseEnhance();
        r.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.setMessage("失败!");
        return r;
    }

    public Integer getCode() {
        return this.code;
    }

    public AthResponseEnhance setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public AthResponseEnhance setMessage(String message) {
        this.message = message;
        return this;
    }

    public long getPageSize() {
        return this.pageSize;
    }

    public AthResponseEnhance setPageSize(long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public long getCurrentPage() {
        return this.currentPage;
    }

    public AthResponseEnhance setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public long getCount() {
        return this.count;
    }

    public AthResponseEnhance setCount(long count) {
        this.count = count;
        return this;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public AthResponseEnhance setData(Object data) {
        if(data instanceof List){
            this.data.put("records",data);
        }else {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add(data);
            this.data.put("records",objects);
        }
        return this;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
