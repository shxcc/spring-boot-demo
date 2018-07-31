package com.example.springbootdemo.dao;

import java.util.List;

import com.example.springbootdemo.model.Demo;

public interface DemoMapper {

	Integer save(Demo demo);

	Integer delete(List<String> list);

	Integer update(Demo demo);

	List<Demo> selectAll();

	List<Demo> selectByName(String hobby);

	Demo selectByUuid(String uuid);

}
