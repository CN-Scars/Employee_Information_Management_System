package com.scars.service;

import com.scars.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    List<Dept> list();

    void delete(Integer id) throws Exception;

    void add(Dept dept);

    void update(Dept dept);

    Dept getById(Integer id);
}
