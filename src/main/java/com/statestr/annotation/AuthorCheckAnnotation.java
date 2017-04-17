package com.statestr.annotation;

import java.lang.annotation.*;

/**
 * Created by ruantianbo on 2017/4/16.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface  AuthorCheckAnnotation {

    // 是否进行权限检查
    boolean value() default false;
}
