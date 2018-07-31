package com.example.springbootdemo.service.impl;

import java.util.List;

import cn.lz.cloud.tool.redis.RedisService;
import com.example.springbootdemo.dao.DemoMapper;
import com.example.springbootdemo.model.Demo;
import com.example.springbootdemo.param.DemoFilter;
import com.example.springbootdemo.service.DemoService;
import com.example.springbootdemo.util.reqErrCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.lz.cloud.common.exception.LzErrException;
import cn.lz.cloud.common.service.ReqObject;
import cn.lz.cloud.common.service.ReqQuery;
import cn.lz.cloud.common.service.ResList;
import cn.lz.cloud.common.util.DateUtil;
import cn.lz.cloud.common.util.UUID;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DemoServiceImpl implements DemoService {

	protected static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class.getName());

	@Autowired
	private DemoMapper demoDao;

	@Autowired
	private RedisService<Demo> redis;

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Demo save(ReqObject<Demo> data) {
		Demo demo = data.getObject();
		// 保存uuid(自动生成唯一uuid值)
		demo.setUuid(UUID.getUUID());
		// 保存创建时间
		demo.setCreateTime(DateUtil.longDateTime());
		Integer count = demoDao.save(demo);
		// 将数据保存到redis中
		if (count > 0) {
			// 更新缓存
			Demo d = data.getObject();
			redis.setObject(d.getUuid(), d, Demo.class);
		}
		log.info("保存成功");
		return demo;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public int delete(ReqObject<List<String>> data) {
		List<String> list = data.getObject();
		if (list != null && list.size() > 0) {
			Integer count = demoDao.delete(list);
			if (count > 0) {
				for (String uuid : list) {
					// 删除缓存
					redis.delObject(uuid);
				}
			}
			return count;
		} else {
			log.error("删除信息为空，请选择删除信息");
			throw reqErrCodes.NO_MESSAGE_DELETED.exception();
		}

	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Demo update(ReqObject<Demo> data) {
		Demo demo = data.getObject();
		// 保存修改时间
		demo.setEditeTime(DateUtil.longDateTime());
		Integer count = demoDao.update(demo);
		// 将数据保存到redis中
		if (count > 0) {
			// 更新缓存
			Demo d = data.getObject();
			redis.setObject(d.getUuid(), d, Demo.class);
		}
		log.info("保存成功");
		return demo;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public ResList<Demo> selectByName(ReqObject<ReqQuery<DemoFilter>> filter) {
		// 获取起始页
		int startPage = filter.getObject().getStartPage();
		// 获取每页显示多少条
		int pageRow = filter.getObject().getPageRow();
		// 判断输入参数是否为空
		filter.getObject().getObject().isValidFilter();
		String hobby = filter.getObject().getObject().getHobby();

		if (startPage == 0 && pageRow == 0) {
			List<Demo> list = demoDao.selectByName(hobby);
			ResList<Demo> resList = new ResList<Demo>(list);
			if (list.size() > ReqQuery.totalCount) {

				throw new LzErrException("900001", "数据过大，请分页");
			}
			return resList;
		}
		PageHelper.startPage(startPage, pageRow);
		PageInfo<Demo> page = null;
		List<Demo> list = demoDao.selectByName(hobby);
		page = new PageInfo<Demo>(list);
		ResList<Demo> resList = new ResList<Demo>(list);
		resList.setStartPage(startPage);
		resList.setPageRow(pageRow);
		resList.setTotalRow(page.getTotal());

		return resList;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Demo selectByUuid(ReqObject<String> data) {
		String uuid = data.getObject();
		// 先查找缓存
		Demo demo = redis.getObject(uuid, Demo.class);
		if (demo == null) {
			demo = demoDao.selectByUuid(uuid);
			// 保存到缓存
			if (demo != null) {
				redis.setObject(uuid, demo, Demo.class);
			} else {
				log.info("从redis中找到[" + uuid + "]");
			}
		}
		return demo;
	}

}
