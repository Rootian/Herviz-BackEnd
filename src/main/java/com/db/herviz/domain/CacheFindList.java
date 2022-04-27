package com.db.herviz.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Description cache annotation, use cache for the method
 * @Author Rootian
 * @Date 2022-04-23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheFindList {
    String preKey() default "";
    int seconds() default 0;
}
