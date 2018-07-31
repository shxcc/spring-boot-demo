package com.example.springbootdemo.controller;

import java.util.List;

import com.example.springbootdemo.model.Demo;
import com.example.springbootdemo.param.DemoFilter;
import com.example.springbootdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lz.cloud.common.service.ClazzNote;
import cn.lz.cloud.common.service.ReqObject;
import cn.lz.cloud.common.service.ReqQuery;
import cn.lz.cloud.common.service.ResList;
import cn.lz.cloud.common.service.ResObject;
import cn.lz.cloud.common.service.ServiceNote;


@RestController
@RequestMapping("/demo")
@ClazzNote(desc = "案例", resource = "demo", modName = "案例")
public class DemoController {

	@Autowired
	private DemoService demoService;

	// 新增案例
	@RequestMapping("/save")
	@ServiceNote(desc = "新增", auth = ServiceNote.AUTH.CHECK)
	public ResObject<Demo> save(@RequestBody ReqObject<Demo> data) {
		try {
			Demo demo = demoService.save(data);
			return new ResObject<Demo>(data, demo);
		} catch (Exception e) {
			return new ResObject<Demo>(data, e);
		}
	}

	// 修改
	@RequestMapping("/update")
	@ServiceNote(desc = "修改", auth = ServiceNote.AUTH.CHECK)
	public ResObject<Demo> update(@RequestBody ReqObject<Demo> data) {
		try {
			Demo demo = demoService.update(data);
			return new ResObject<Demo>(data, demo);
		} catch (Exception e) {
			return new ResObject<Demo>(data, e);
		}
	}

	// 根据hobby查询
	@RequestMapping("/selectByName")
	@ServiceNote(desc = "根据hobby查询", auth = ServiceNote.AUTH.CHECK)
	public ResObject<ResList<Demo>> selectByName(@RequestBody ReqObject<ReqQuery<DemoFilter>> filter) {
		try {
			ResList<Demo> demo = demoService.selectByName(filter);
			return new ResObject<ResList<Demo>>(filter, demo);
		} catch (Exception e) {
			return new ResObject<ResList<Demo>>(filter, e);
		}
	}

	// 批量删除
	@RequestMapping("/delete")
	@ServiceNote(desc = "批量删除", auth = ServiceNote.AUTH.CHECK)
	public ResObject<Integer> delete(@RequestBody ReqObject<List<String>> data) {
		try {
			Integer row = demoService.delete(data);
			return new ResObject<Integer>(data, row);
		} catch (Exception e) {
			return new ResObject<Integer>(data, e);
		}
	}
	
	// 根据Uuid查询
	@RequestMapping("/selectByUuid")
	@ServiceNote(desc = "根据Uuid查询", auth = ServiceNote.AUTH.CHECK)
	public ResObject<Demo> selectByUuid(@RequestBody ReqObject<String> data) {
		try {
			Demo demo = demoService.selectByUuid(data);
			return new ResObject<Demo>(data, demo);
		} catch (Exception e) {
			return new ResObject<Demo>(data, e);
		}
	}
}
