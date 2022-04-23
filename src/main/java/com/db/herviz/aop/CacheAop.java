package com.db.herviz.aop;

import com.db.herviz.domain.CacheFind;
import com.db.herviz.domain.CacheFindList;
import com.db.herviz.redis.RedisUtil;
import javafx.beans.binding.ObjectExpression;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-23
 */
@Aspect
@Component
public class CacheAop {

    @Autowired
    private RedisUtil redisUtil;

    @Around("@annotation(cacheFind)")
    public Object useCache(ProceedingJoinPoint joinPoint, CacheFind cacheFind) {
        Object result = null;

        try {
            // assemble cache key
            Object[] args = joinPoint.getArgs();
            String key = cacheFind.preKey() + "::" + Arrays.toString(args);

            if (redisUtil.hasKey(key)) {
                // key exists
                result = redisUtil.get(key);
            } else {
                // key not exists, proceed as user function
                result = joinPoint.proceed();

                // set up cache
                redisUtil.set(key, result);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description cache action for list return type
     * @Author Rootian
     * @Date 2022-04-23
     * @param: joinPoint
     * @param: cacheFindList
     * @return java.util.List<?>
     */
    @Around("@annotation(cacheFindList)")
    public List<?> useCacheList(ProceedingJoinPoint joinPoint, CacheFindList cacheFindList) {
        List<?> resultList = null;

        try {
            // assemble cache key
            Object[] args = joinPoint.getArgs();
            String key = cacheFindList.preKey() + "::" + Arrays.toString(args);

            if (redisUtil.hasKey(key)) {
                // key exists
                resultList = redisUtil.getList(key);
            } else {
                // key not exists, proceed as user function
                resultList = (List<?>) joinPoint.proceed();

                // set up cache
                redisUtil.saveList(key, resultList);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return resultList;
    }


}
