package com.grant.springcoredemo.rest;

// Annotation
import com.grant.springcoredemo.common.ICoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// cok kirli ancak anlamak amacli

@RestController
public class DemoController {

    // won't be change anywhere so final
    private final ICoach myCoach;

    /* For setter can not be final
    private ICoach myCoach; -> For setter injection commented out
    */

    /* SETTER
    @Autowired
    public void doSomeStuff(ICoach theCoach) {
        myCoach = theCoach;
    }
    */

    // define a constructor for dependency injection -- means inject a dependency (OPTIONAL) // INJECTION // Changed to qualifier
    @Autowired
    /*public DemoController(@Qualifier("CricketCoach") ICoach myCoach,
                            @Qualifier("CricketCoach") ICoach theMyCoach) {*/
    //public DemoController(@Qualifier("baseballCoach") ICoach myCoach) {
    public DemoController(@Qualifier("aquatic") ICoach myCoach) {
    //public DemoController(ICoach myCoach) {
        // @Qualifier --> which bean
        System.out.println("In constructor: " + getClass().getSimpleName());
        this.myCoach = myCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}

/*
* @Autowired, Spring'e, DemoController sınıfındaki ICoach türündeki bağımlılığı otomatik olarak çözmesi gerektiğini söyler.
* Spring, uygulama başlatıldığında bir bean arar. ICoach bir interface olduğundan, Spring @Component ile işaretlenmiş bir sınıf (ör. CricketCoach) bulur ve bunu enjekte eder.
* */

/* All beans loads anyway we don't want that
In constructor: BaseballCoach
In constructor: CricketCoach
In constructor: TennisCoach
In constructor: TrackCoach
In constructor: DemoController

@Lazy ekledik bu sekilde gelmedi ilgili olanlar @Component altına :D

spring.main.lazy-initialization=true -- ekledik properties'e bu sekilde done kaldırdım
* */