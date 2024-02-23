package tech.xuedong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.xuedong.Utils.PageBean;
import tech.xuedong.Utils.R;
import tech.xuedong.mapper.ScheduleMapper;
import tech.xuedong.pojo.Schedule;
import tech.xuedong.service.ScheduleService;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public R page(int pageSize, int currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<Schedule> page = scheduleMapper.queryList();
        PageInfo<Schedule> pageInfo = new PageInfo<>(page);
        PageBean<Schedule> pageBean = new PageBean<>(currentPage, pageSize, pageInfo.getTotal(), pageInfo.getList());
        R ok = R.ok(pageBean);
        return ok ;
    }

    @Override
    public R remove(int id) {
        int rows = scheduleMapper.deleteById(id);
        if (rows > 0) {
            return R.ok(null);
        }
        return R.fail(null);
    }

    @Override
    public R save(Schedule schedule) {
        int rows = scheduleMapper.insert(schedule);
        if (rows > 0) {
            return R.ok(schedule);
        }
        return R.fail(null);
    }

    @Override
    public R update(Schedule schedule) {
        // 判断id
        if (schedule.getId() == null) {
            return R.fail("id 为空");
        }
        int rows = scheduleMapper.update(schedule);
        if (rows > 0) {
            return R.ok(null);
        }
        return R.fail(null);
    }
}
