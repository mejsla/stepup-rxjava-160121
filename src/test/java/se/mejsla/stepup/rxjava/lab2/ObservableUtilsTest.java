package se.mejsla.stepup.rxjava.lab2;

import org.junit.Test;
import rx.Observable;
import rx.observables.ConnectableObservable;
import rx.observers.TestSubscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class ObservableUtilsTest {

    @Test
    public void testFizzBuzz() throws Exception {
        ConnectableObservable<Long> co = Observable.interval(1, TimeUnit.MILLISECONDS).skip(1).takeUntil(l -> l == 100).publish();
        TestSubscriber<String> fizzBuzzSubscriber = new TestSubscriber<>();
        ObservableUtils.fizzBuzzObservable(co).subscribe(fizzBuzzSubscriber);
        co.connect();
        fizzBuzzSubscriber.awaitTerminalEvent();
        final List<String> onNextEvents = fizzBuzzSubscriber.getOnNextEvents();
        for (int i = 1; i < onNextEvents.size(); i++) {
            String str = onNextEvents.get(i-1);
            if (i % 3 == 0 && i % 5 == 0) {
                assertEquals("FizzBuzz", str);
            } else if (i % 5 == 0) {
                assertEquals("Buzz", str);
            } else if (i % 3 == 0) {
                assertEquals("Fizz", str);
            } else {
                try {

                    assertEquals(i, Integer.parseInt(str));
                }catch (NumberFormatException nfe) {
                    fail(str + " is not equal to the expected " + i);
                }
            }
        }
//        System.out.println(fizzBuzzSubscriber.getOnNextEvents());
        assertEquals(100, onNextEvents.size());

    }

    @Test
    public void sortIntoSubscribers() throws Exception {
        ConnectableObservable<Long> co = Observable.interval(0, 1, TimeUnit.MILLISECONDS).takeUntil(l -> l == 99).publish();
        TestSubscriber<Long> odds = new TestSubscriber<>();
        TestSubscriber<Long> evens = new TestSubscriber<>();
        ObservableUtils.oddsAndEvens(co, odds, evens);
        co.connect();
        odds.awaitTerminalEvent(1, TimeUnit.SECONDS);
        evens.awaitTerminalEvent(1, TimeUnit.SECONDS);
        assertTrue(odds.getOnNextEvents().stream().allMatch(l -> l % 2 == 1));
        assertTrue(evens.getOnNextEvents().stream().allMatch(l -> l % 2 == 0));
        odds.assertValueCount(50);
        evens.assertValueCount(50);

        odds.assertCompleted();
        evens.assertCompleted();

    }
}