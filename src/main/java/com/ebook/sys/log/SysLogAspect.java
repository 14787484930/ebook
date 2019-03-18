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

            if(map.get("methodName").equals("getById")){
                //此处做浏览量的统计（调用service）
                /*Object[] args = (Object[])map.get("args");
                String id = args[0].toString();

                if(template.boundHashOps("viewNumber").hasKey("viewNumber")){
                    template.boundHashOps("viewNumber").put(id,1);
                }else{
                    template.boundHashOps("viewNumber");
                    template.opsForHash();

                }


                System.out.println("===========================");
                System.out.println(args[0].toString());*/
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

}
