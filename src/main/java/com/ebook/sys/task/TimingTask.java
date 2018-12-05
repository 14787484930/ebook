package com.ebook.sys.task;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zxl
 * @date 2018/12/5 16:58
 * @describe 定时任务处理器
 */

/**
 * Seconds Minutes Hours DayofMonth Month DayofWeek Year
 * 秒 分钟 小时 月月 月份 星期 年
 *
 * Seconds:可出现", - * /"四个字符，有效范围为0-59的整数
 * Minutes:可出现", - * /"四个字符，有效范围为0-59的整数
 * Hours:可出现", - * /"四个字符，有效范围为0-23的整数
 * DayofMonth:可出现", - * / ? L W C"八个字符，有效范围为0-31的整数
 * Month:可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc
 * DayofWeek:可出现", - * / ? L C #"四个字符，有效范围为1-7的整数或SUN-SAT两个范围。1表示星期天，2表示星期一， 依次类推
 * Year:可出现", - * /"四个字符，有效范围为1970-2099年
 */

@Component
@Lazy(false)
public class TimingTask {

    @Scheduled(cron = "0 30 23 * * *")
    public void testdemo(){
       /*编写定时任务*/
        System.out.println("==============================定时任务执行成功");
    }
}
