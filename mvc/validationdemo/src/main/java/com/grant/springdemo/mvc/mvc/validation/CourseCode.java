package com.grant.springdemo.mvc.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD}) // nerde kullanabiliriz field yani variable veya metod
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    public String value() default "LUV";

    public String message() default "must start with LUV";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {}; // provide custom details about validation failure
}


/*
* @CourseCode anotasyonu, özel bir doğrulama sağlamak için kullanılır. @Constraint(validatedBy = CourseCodeConstraintValidator.class) ile
* hangi sınıfın doğrulama yapacağını belirtir. @Target({ElementType.METHOD, ElementType.FIELD}), bu anotasyonun metodlar ve alanlar üzerinde
* kullanılabileceğini belirtir. @Retention(RetentionPolicy.RUNTIME) ise anotasyonun çalışma zamanı sırasında erişilebilir olmasını sağlar.
* */