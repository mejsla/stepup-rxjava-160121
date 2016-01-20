package se.mejsla.stepup.rxjava;

import rx.Observable;
import rx.Subscription;

import java.util.concurrent.TimeUnit;

public class Unsubscribing {

    public static void main(String[] args) throws Exception {
        Observable<Long> interval = Observable.interval(500L, TimeUnit.MILLISECONDS);
//        Subscription subscribe = interval.subscribe(System.out::println);
        interval.subscribe(System.out::println);
        interval.subscribe(number -> System.err.println("--> " + number));
        Thread.sleep(1700);
//        subscribe.unsubscribe();
//        System.out.println("unsubscribing");
//        Thread.sleep(4000);
        System.out.println("Done");    }

}
