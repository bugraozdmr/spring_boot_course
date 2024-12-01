package com.grant.springcoredemo.config;

import com.grant.springcoredemo.common.ICoach;
import com.grant.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean("aquatic")
    public ICoach SwimCoach() {
        return new SwimCoach();
    }
}

/*
* bu bize istedigimizi verecektir artık @Bean oldugunuz soyledik @Component kullanmadan
* id 'de verebiliriz bu sekilde - @Qualifier("aquatic") ICoach myCoach - kullanılabilir
*/