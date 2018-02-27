package com.covet.diy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P>Description: UserAnnotation相关信息</P>
 * @ClassName: UserAnnotation   自定义注解
 * @author 胡良俊  2018年2月27日下午5:59:41
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAnnotation {

}
