package se.mejsla.stepup.rxjava;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class HelloWorld {

    public static void main(String[] args) {
        Observable.just("World").subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
                System.out.println("Hello " + str);
            }
        });
        // Replace with lambda

//        Subscription subscription = Observable.just("World", "Kristofer")
//                .subscribe(str -> {
//                    System.out.println("Hello " + str);
//                }, throwable -> {
//                    System.out.println("Something went horribly wrong");
//                }, () -> {
//                    System.out.println("All done");
//                });
//        System.out.println("isUnsubscribed: " + subscription.isUnsubscribed());
    }
}
