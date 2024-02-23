package tech.xuedong.service;

import tech.xuedong.Utils.R;
import tech.xuedong.pojo.Schedule;

public interface ScheduleService {
    R page(int pageSize, int currentPage);

     R remove(int id);

    R save(Schedule schedule);

    R update(Schedule schedule);
}
