package se.mejsla.stepup.rxjava;

import rx.Observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SwitchMap {
    public static void main(String[] args) throws Exception {
        final CountDownLatch latch = new CountDownLatch(8);
        Observable<String> stringObservable = Observable.interval(0, 300L, TimeUnit.MILLISECONDS)
                .switchMap(outerCounter ->
                        Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                                .map(innerCounter ->
                                        String.format(
                                                "Observable <%s> : %s",
                                                (outerCounter), (innerCounter))));
        stringObservable.subscribe(s -> {
            System.out.println(s);
            latch.countDown();
        });
        latch.await();
    }
}
