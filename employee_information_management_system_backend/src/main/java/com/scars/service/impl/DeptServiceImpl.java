package com.scars.service.impl;

import com.scars.mapper.DeptMapper;
import com.scars.pojo.Dept;
import com.scars.pojo.DeptLog;
import com.scars.service.DeptLogService;
import com.scars.service.DeptService;
import com.scars.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpService empService;
    @Autowired
    private DeptLogServiceImpl deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class)  // 将当前方法交给Spring进行事务管理，并确保所有异常发生时都进行事务的回滚
    @Override
    public void delete(Integer id) throws Exception {
        boolean success = false;

        try {
            deptMapper.deleteByID(id);

//        int i = 1 / 0;  // 默认情况下只有运行时异常才会回滚

//        if (true)   // 若不指定rollbackFor属性，RunTimeException之外的所有类型的异常，例如下面的异常发生时，就不会进行事务回滚操作
//        {
//            throw new Exception("出现了异常");
//        }

            empService.deleteByDeptId(id);

            success = true;
        } finally { // 记录部门删除的日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("删除" + id + "号部门" + (success ? "成功" : "失败"));
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        // 补全数据
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        // 补全数据
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }
}
