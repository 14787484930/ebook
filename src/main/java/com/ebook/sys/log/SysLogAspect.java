package com.ebook.sys.log;

import com.ebook.beans.log.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * zxl
 * 2018-12-05
 * 日志切点类
 */

@Aspect
@Component
public class SysLogAspect {


    @Autowired
    private RedisTemplate<String,String> template;

    //Controller层切点
    @Pointcut("@annotation(com.ebook.sys.log.SysLog)")
    public void controllerAspect() { }

    @Before("controllerAspect()")
    //@AfterReturning(value = "controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        try {
            //具体业务逻辑  根据业务需求进行更改
            Log log = new Log();

            Map<String,Object> map = getControllerMethodDescription(joinPoint);

            /*做判断
            *根据访问的方法名判断是R还是CUD
            * 如果是R
            *   更新统计浏览量的表
            * 如果是CUD
            *   将操作插入到日志表
            *
            * */

            String methodName = map.get("methodName").toString();
            if(methodName.startsWith("getById")){

                /**
                 *  写入队列的各类型浏览量的键值
                 *  bookViewNumber  图书
                 *  electronicsViewNumber   电子
                 *  otherViewNumber 其他
                 *  tutoringViewNumber  辅导
                 * */

                /**
                 * 方法名结尾判断
                 * Book
                 * Electronics
                 * Other
                 * Tutoring
                 */

                /*此处做浏览量的统计(写入缓存队列)*/

                //获取操作的产品的id,此id用来做队列中的键
                Object[] args = (Object[])map.get("args");
                String field = args[0].toString();

                //通过操作的方法名判断当前操作的是那种产品
                if(methodName.endsWith("Book")){
                    //浏览图书
                    writeDataToRedis("bookViewNumber",field);

                }else if(methodName.endsWith("Electronics")){
                    //浏览电子
                    writeDataToRedis("electronicsViewNumber",field);

                }else if(methodName.endsWith("Other")){
                    //浏览其他
                    writeDataToRedis("otherViewNumber",field);

                }else if(methodName.endsWith("Tutoring")){
                    //浏览辅导
                    writeDataToRedis("tutoringViewNumber",field);

                }

            }else{
                //此处做增删改的日志(调用service)

            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  static Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName(); //访问的控制器名称
        String methodName = joinPoint.getSignature().getName(); //访问的方法名
        Object[] arguments = joinPoint.getArgs(); //访问时传递的参数
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        /*此map用于存返回结果*/
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("controllerName",targetName);
        map.put("methodName",methodName);
        map.put("args",arguments);

        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    map.put("moduleName",method.getAnnotation(SysLog.class).moduleName());
                    break;
                }
            }
        }
        return map;
    }

    public void writeDataToRedis(String key,String field){

        if(template.opsForHash().hasKey(key,field)){
            //表示存在，先取出来，累加，再写入
            int number = (Integer) template.opsForHash().get(key,field);
            template.opsForHash().put(key,field,++number);
        }else{
            //表示不存在，直接写入
            template.opsForHash().put(key,field,1);
        }
    }

}
