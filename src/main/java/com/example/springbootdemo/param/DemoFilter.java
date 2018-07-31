package com.example.springbootdemo.param;


import cn.lz.cloud.common.service.FieldNote;
import cn.lz.cloud.common.service.PojoNote;
import com.example.springbootdemo.util.reqErrCodes;

@PojoNote(desc = "案例输入参数", resource = "demoFilter")
public class DemoFilter {

	@FieldNote(desc = "爱好", length = 24, notNull = true)
	private String hobby;
	
	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public void isValidFilter() {
		if ((hobby == null || hobby.isEmpty())) {
			throw reqErrCodes.FILTER_ERROR_CORP.exception("必须输入条件hobby");
		}
	}
	
}
