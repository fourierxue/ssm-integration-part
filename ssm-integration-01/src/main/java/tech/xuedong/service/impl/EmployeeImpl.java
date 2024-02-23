package tech.xuedong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.xuedong.mapper.EmployeeMapper;
import tech.xuedong.pojo.Employee;
import tech.xuedong.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findAll() {
        return employeeMapper.queryList();
    }
}
