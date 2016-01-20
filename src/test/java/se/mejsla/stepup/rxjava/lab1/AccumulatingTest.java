package se.mejsla.stepup.rxjava.lab1;

import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.Arrays;

public class AccumulatingTest {

    TestSubscriber<Object> testSubscriber;

    @Before
    public void before() {
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void scan() {
        Observable<Integer> scanning = Accumulating.multiply(Observable.just(1, 2, 3, 4, 5));
        scanning.subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(1, 2, 6, 24, 120));
        testSubscriber.assertCompleted();
    }
}