package com.scars.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scars.mapper.EmpMapper;
import com.scars.pojo.Emp;
import com.scars.pojo.PageBean;
import com.scars.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        //1.获取总记录数
//        Long count = empMapper.count();
//
////        2.获取分页查询列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.page(start, pageSize);
////        3.把查询结果封装到PageBean对象中
//
//        PageBean pageBean = new PageBean(count, empList);
//
//        return pageBean;
//    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);

        // 执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        Emp e = empMapper.getByUsernameAndPassword(emp);
        return e;
    }

    @Override
    public void deleteByDeptId(Integer id) {
        empMapper.deleteByDeptId(id);
    }
}