import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  肖济
 *  2018-11-14
 *  时间和日期的相互转换
 *  方法是静态的，类名调用
 */
public class DateTimeTest {
    private final static int DAY =  24 * 60 * 60 * 1000;
    //日期转字符串
    static String dateToString(Date date) {
        String str = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(str);
        String dateFormat = format.format(date);
        return dateFormat;
    }
    //字符串转日期
    static Date stringToDate(String string) {
        String str = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(str);
        Date date = new Date();
        try {
            date = format.parse(string);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return date;
    }
    //获得两个日期之间的天数
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
// 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date mydate1 = null;
        Date mydate2 = null;
        try {
            mydate1 = myFormatter.parse(date1);
            mydate2 = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (mydate1.getTime() - mydate2.getTime()) / (DAY);
        return Math.abs(day);

    }
    // 获得时间戳
    public static long getTime(){
        return  System.currentTimeMillis();
    }
    // 时间前(-2)后(2)几天
    public static String addDate( Date d, int days){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String addDate = sdf.format((d.getTime() + (long)days * DAY));
        return (addDate);
    }

    public static boolean isSameDay(Date date1,Date date2){

        if (date1.getDay()==date2.getDay()){
            return true;
        }
        return false;
    }
}
