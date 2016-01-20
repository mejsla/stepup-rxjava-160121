package se.mejsla.stepup.rxjava;

import rx.Observable;
import rx.Subscriber;

public class Retry {

    public static void main(String[] args) {
        Observable<Object> observable = Observable.create(new Observable.OnSubscribe<Object>() {
            int countDown = 2;

            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                if (countDown > 0) {
                    countDown--;
                    subscriber.onError(new RuntimeException("BooHoo!"));
                    return;
                }
                subscriber.onNext(3);
                subscriber.onCompleted();
            }
        });

        observable.subscribe(System.out::println);
//        observable.retry(2).subscribe(System.out::println, System.err::println  );
    }
}
