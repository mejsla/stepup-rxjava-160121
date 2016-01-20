package se.mejsla.stepup.rxjava;

import rx.Observable;

public class Errors {
    public static void main(String[] args) {
        Observable<Integer> numbers = Observable
                .just("1", "2", "three", "4", "5")
                .map(Integer::parseInt);
//                .onErrorReturn(throwable -> -1)
//                .onErrorResumeNext(throwable -> Observable.just(-2, -1, 0));
        
//        numbers.subscribe(System.out::println);
        numbers.subscribe(
                System.out::println,
                throwable -> System.out.println("oops"),
                () -> System.out.println("Done"));
    }
}
