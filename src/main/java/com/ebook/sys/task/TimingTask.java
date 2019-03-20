package com.ebook.sys.task;

import com.ebook.beans.book.Book;
import com.ebook.beans.book.BookQuery;
import com.ebook.beans.electronics.Electronics;
import com.ebook.beans.electronics.ElectronicsQuery;
import com.ebook.beans.other.Other;
import com.ebook.beans.other.OtherQuery;
import com.ebook.beans.sensitivewords.SensitiveWords;
import com.ebook.beans.sensitivewords.SensitiveWordsQuery;
import com.ebook.beans.tutoring.Tutoring;
import com.ebook.beans.tutoring.TutoringQuery;
import com.ebook.daos.BookDao;
import com.ebook.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.management.Query;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

/*@Component
@Lazy(false)*/
public class TimingTask {

    @Autowired
    SensitiveWordsService sensitiveWordsService;

    @Autowired
    BookService bookService;

    @Autowired
    ElectronicsService electronicsService;

    @Autowired
    TutoringService tutoringService;

    @Autowired
    OtherService otherService;

    @Autowired
    private RedisTemplate<String,String> template;

    //@Scheduled(cron = "0 * 0 * * *")
    public void testdemo(){

        System.out.println(123);

       /*编写定时任务*/
        List<SensitiveWords> list = sensitiveWordsService.getSensitiveWords(new SensitiveWordsQuery());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-1);

        Date startTime = c.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(startTime));


        /**
         * 1.图书过滤
         * 2.电子过滤
         * 3.辅导过滤
         * 4.其他过滤
         */

        //查询出前一天新增的所有图书
        BookQuery bookQuery = new BookQuery();
        bookQuery.setStartTime(startTime);
        List<Book> books = bookService.getBooks(bookQuery);

        //查询出前一天新增的所有电子产品
        ElectronicsQuery electronicsQuery = new ElectronicsQuery();
        electronicsQuery.setStartTime(startTime);
        List<Electronics> electronicses = electronicsService.getElectronics(electronicsQuery);


        //查询出前一天新增的所有辅导
        TutoringQuery tutoringQuery = new TutoringQuery();
        tutoringQuery.setStartTime(startTime);
        List<Tutoring> tutorings = tutoringService.getTutorings(tutoringQuery);

        //查询出前一天新增的所有其他
        OtherQuery otherQuery = new OtherQuery();
        otherQuery.setStartTime(startTime);
        List<Other> others = otherService.getOthers(otherQuery);


        //进行过滤
        for(SensitiveWords sensitiveWords : list){

            String word = sensitiveWords.getWord();

            //对图书名称和图书描述进行过滤
            List<String> idList = new ArrayList<String>();
            for(Book book:books){
                String str = book.getBookName()+ " " + book.getDes();
                if(str.contains(word)){
                    idList.add(book.getId());
                }
            }
            //修改该数据的数据状态为101
            bookQuery.setIds(idList.toArray(bookQuery.getIds()));
            bookService.updateWarning(bookQuery);

            //初始化id集合
            idList.clear();

            //对电子名称和电子描述进行过滤
            for(Electronics electronics:electronicses){
                String str = electronics.getElectronicsName() + " " + electronics.getDes();
                if(str.contains(word)){
                    idList.add(electronics.getId());
                }
            }
            //修改该数据的数据状态101
            electronicsQuery.setIds(idList.toArray(electronicsQuery.getIds()));
            electronicsService.updateWarning(electronicsQuery);

            //初始化id集合
            idList.clear();

            //对辅导名称和描述进行过滤
            for(Tutoring tutoring:tutorings){
                String str = tutoring.getName() + " " + tutoring.getDes();
                if(str.contains(word)){
                    idList.add(tutoring.getId());
                }
            }
            //修改该数据的数据状态101
            tutoringQuery.setIds(idList.toArray(tutoringQuery.getIds()));
            tutoringService.updateWarning(tutoringQuery);

            //初始化id集合
            idList.clear();

            //对其他产品的名称和辅导进行过滤
            for(Other other:others){
                String str = other.getOtherName() + " " + other.getDes();
                if(str.contains(word)){
                    idList.add(other.getId());
                }
            }
            //修改该数据的数据状态101
            otherQuery.setIds(idList.toArray(otherQuery.getIds()));
            otherService.updateWarning(otherQuery);

        }


    }

    /**
     * 将redis队列中的浏览量写入数据库并刷新队列
     */
    @Scheduled(cron = "0 22 14 * * *")
    public void readAndWrite(){

        //从缓存中取出数据，清空缓存

        writeToDatabase("bookViewNumber",1); //图书

        writeToDatabase("electronicsViewNumber",2); //电子

        writeToDatabase("otherViewNumber",3); //其他

        writeToDatabase("tutoringViewNumber",4); //辅导

    }

    public void writeToDatabase(String typeKey,int type){

        Map<Object ,Object> map = template.opsForHash().entries(typeKey);
        template.delete(typeKey);

        if(type == 1) {
            //图书
            //bookService
            BookQuery query = new BookQuery();
            for(Map.Entry<Object, Object> entry: map.entrySet())
            {
                query.setId(entry.getKey().toString());
                query.setViewTimes((Integer)entry.getValue());
                bookService.updateViews(query);
            }

        }else if(type == 2) {
            //电子
            //electronicsService
            ElectronicsQuery query = new ElectronicsQuery();
            for(Map.Entry<Object, Object> entry: map.entrySet())
            {
                query.setId(entry.getKey().toString());
                query.setViewTimes((Integer)entry.getValue());
                electronicsService.updateViews(query);

            }


        }else if(type == 3) {
            //其他
            //otherService
            OtherQuery query = new OtherQuery();
            for(Map.Entry<Object, Object> entry: map.entrySet())
            {
                query.setId(entry.getKey().toString());
                query.setViewTimes((Integer)entry.getValue());
                otherService.updateViews(query);

            }

        }else if(type == 4) {
            //辅导
            //tutoringService
            TutoringQuery query = new TutoringQuery();
            for(Map.Entry<Object, Object> entry: map.entrySet())
            {
                query.setId(entry.getKey().toString());
                query.setViewTimes((Integer)entry.getValue());
                tutoringService.updateViews(query);
            }

        }

    }

}
