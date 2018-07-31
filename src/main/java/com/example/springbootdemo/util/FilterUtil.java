/**
 *
 * @(#) Test.java
 * @Package com.spring.boot.util
 * 
 * Copyright  Icerno Corporation. All rights reserved.
 *
 */
package com.example.springbootdemo.util;

import java.lang.reflect.Field;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import cn.lz.cloud.common.exception.LzErrException;
/**
 * 
 * @author 王晓祥
 * @description 此工具类为Filter类字段作为查询条件全部为is时使用
 * @param <T>
 */
public class FilterUtil<T> {

	public Query getFieldValueStr(T param) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		if (param != null) {
			Class<? extends Object> objectClazz = param.getClass();
			Field[] fields = objectClazz.getDeclaredFields();
			int fieldNum = fields.length;
			for (int i = 0; i < fieldNum; i++) {
				Field attribute = fields[i];
				attribute.setAccessible(true);
				try {
					String key = String.valueOf(attribute.getName());
					String value = String.valueOf(attribute.get(param));
					// 判断属性值是否非空，非空则添加成为条件;
					if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
						criteria = Criteria.where(key).is(value);
						query.addCriteria(criteria);
					}
				} catch (Exception e) {
					throw new LzErrException("Fil001", "系统异常！");
				}
			}

		}
		return query;
	}

}
