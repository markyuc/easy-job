package com.easy.job.core.handler.annotation;

import java.lang.annotation.*;

/**
 * @author hkal_mark
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface EasyJob {
    String value() default "";
}
