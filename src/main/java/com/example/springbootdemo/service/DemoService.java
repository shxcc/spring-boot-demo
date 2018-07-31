package com.example.springbootdemo.service;

import java.util.List;

import cn.lz.cloud.common.service.ReqObject;
import cn.lz.cloud.common.service.ReqQuery;
import cn.lz.cloud.common.service.ResList;

import com.example.springbootdemo.model.Demo;
import com.example.springbootdemo.param.DemoFilter;

public interface DemoService {

	Demo save(ReqObject<Demo> data);

	int delete(ReqObject<List<String>> data);

	Demo update(ReqObject<Demo> data);

	ResList<Demo> selectByName(ReqObject<ReqQuery<DemoFilter>> filter);

	Demo selectByUuid(ReqObject<String> data);

}
