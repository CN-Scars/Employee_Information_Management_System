package com.scars.service.impl;

import com.scars.mapper.DeptLogMapper;
import com.scars.pojo.DeptLog;
import com.scars.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)  // 另起一个新事务，确保在记录日志时无论上游操作是否出现异常都可以正常执行日志的记录，除非记录日志出现了异常
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
