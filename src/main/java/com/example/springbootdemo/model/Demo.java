package com.example.springbootdemo.model;

import cn.lz.cloud.common.service.FieldNote;
import cn.lz.cloud.common.service.PojoNote;

@PojoNote(desc = "案例", resource = "demo")
public class Demo {

	@FieldNote(desc = "案例uuid", length = 50, notNull = true)
	private String uuid;

	@FieldNote(desc = "姓名", length = 24, notNull = false)
	private String name;

	@FieldNote(desc = "年纪", length = 24, notNull = false)
	private int age;

	@FieldNote(desc = "爱好", length = 50, notNull = false)
	private String hobby;

	@FieldNote(desc = "地址", length = 50, notNull = false)
	private String address;

	@FieldNote(desc = "电话", length = 24, notNull = false)
	private String phone;
	
	@FieldNote(desc = "创建时间", length = 50, notNull = false)
	private String createTime;
	
	@FieldNote(desc = "编辑时间", length = 50, notNull = false)
	private String editeTime;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEditeTime() {
		return editeTime;
	}

	public void setEditeTime(String editeTime) {
		this.editeTime = editeTime;
	}

}
