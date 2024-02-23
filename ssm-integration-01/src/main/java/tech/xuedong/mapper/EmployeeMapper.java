package tech.xuedong.mapper;

import org.apache.ibatis.annotations.Mapper;
import tech.xuedong.pojo.Employee;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> queryList();
}
