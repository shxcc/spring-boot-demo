/**
 *
 * @(#) getTimeSlotUtil.java
 * @Package com.spring.boot.util
 * 
 * Copyright  Icerno Corporation. All rights reserved.
 *
 */

package com.example.springbootdemo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

import cn.lz.cloud.common.exception.LzErrException;

/**
 *  Description : 获取近三个月、近半年、近一年日期
 * 
 *  @author:  王晓祥
 *
 * Create Date：   2017年11月29日 下午5:20:59
 * History:  2017年11月29日 下午5:20:59   王晓祥   Created.
 *           
 */
public class getTimeSlotUtil {

	public String getTime(String nowTime) {
		if (!StringUtils.isEmpty(nowTime)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			// 过去三个月
			if (nowTime.equals("3")) {
				c.setTime(new Date());
				c.add(Calendar.MONTH, -3);
				Date m3 = c.getTime();
				String mon3 = format.format(m3);
				System.out.println("过去三个月：" + mon3);
				return mon3;
				// 过去半年
			} else if (nowTime.equals("6")) {
				c.setTime(new Date());
				c.add(Calendar.MONTH, -6);
				Date m6 = c.getTime();
				String mon6 = format.format(m6);
				System.out.println("过去半年：" + mon6);
				return mon6;
				// 过去一年
			} else {
				c.setTime(new Date());
				c.add(Calendar.YEAR, -1);
				Date y = c.getTime();
				String year = format.format(y);
				System.out.println("过去一年：" + year);
				return year;
			}
		} else {
			throw new LzErrException("时间段为空！");
		}
	}
}
