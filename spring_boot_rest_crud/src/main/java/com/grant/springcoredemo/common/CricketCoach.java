package com.grant.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CricketCoach implements ICoach {
    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes !";
    }
}

/*
* bu sekilde istenildigi kadar CricketCoach bean uretilebilir artık kızmaz spring
* @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
* @Component kullanıldığında, Spring bu sınıfı otomatik olarak algılar ve uygulama başlatılırken bir örneğini oluşturur.
* Spring'e, anotasyonun kullanıldığı sınıfın bir bean olduğunu ve Spring IoC Container (Inversion of Control Container) tarafından yönetileceğini belirtir.
* @Component, bir sınıfı manuel olarak ApplicationContext'e eklemek zorunda kalmadan Spring tarafından otomatik olarak yönetilebilir hale getirir.
* */