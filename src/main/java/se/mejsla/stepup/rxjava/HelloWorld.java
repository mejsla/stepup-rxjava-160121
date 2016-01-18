package se.mejsla.stepup.rxjava;

import rx.Observable;

public class HelloWorld {

    public static void main(String[] args) {
        Observable.just("World").subscribe((str) -> System.out.println("Hello " + str));
    }
}
