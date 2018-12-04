package com.model.utills.log;

import com.ebook.beans.log.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * zxl
 * 2018-12-03
 * 日志切点类
 */

@Aspect
@Component
public class SysLogAspect {

    //Controller层切点
    @Pointcut("@annotation(com.model.utills.log.SysLog)")
    public void controllerAspect() {
        System.out.println("=====================切入点...");
    }

    //@Before("controllerAspect()")
    @AfterReturning(value = "controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        try {
            //具体业务逻辑  根据业务需求进行更改
            Log log = new Log();

            getControllerMethodDescription(joinPoint);
            //保存数据库
            System.out.println("保存到数据库");

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  static String[] getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        /*String[] annos = {""};
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    annos[0] = method.getAnnotation(UploadCountLogs.class).moduleName();
                    //具体的业务逻辑  根据业务需求进行更改
                    if(annos[0].equals("新闻")){
                        Random random = new Random();
                        int ran = random.nextInt(30);  //[0,30)
                        if (ran<10) {
                            annos[0]="政务公示";
                        }else if (ran>=10 && ran<20) {
                            annos[0]="政府舆情";
                        }else {
                            annos[0]="新闻数据";
                        }
                    }
                    break;
                }
            }
        }*/
        return null;
    }

}
