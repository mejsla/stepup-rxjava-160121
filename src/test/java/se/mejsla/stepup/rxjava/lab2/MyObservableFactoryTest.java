package se.mejsla.stepup.rxjava.lab2;

import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.Arrays;


public class MyObservableFactoryTest {

    TestSubscriber testSubscriber;

    @Before
    public void before() {
        testSubscriber = new TestSubscriber();
    }


    /**
     * Assert that the subscriber receives:
     * 1. the value returned by the supplier.
     * 2. exactly one completion event(and will not send any further events).
     * 3. no error notifications.
     */
    @Test
    public void expectOnNextEventAndCompletedEvent() {

        final Observable observable = MyObservableFactory.fromSuppliers(() -> 5);
        observable.unsafeSubscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(5));
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
    }


    /**
     * Assert that the subscriber receives:
     * 1. two values, one for each supplier.
     * 2. exactly one completion event(and will not send any further events).
     * 3. no error notifications.
     */
    @Test
    public void expectTwoOnNextEventsAndCompletedEvent()  {
        final Observable observable = MyObservableFactory.fromSuppliers(() -> 2, () -> 4);
        observable.unsafeSubscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(2, 4));
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();

    }

    /**
     * When the event source (the supplier) throws an exception, the Observable should notify its
     * subscribers that an error occured. This Observable does this by wrapping the initial exception
     * in a {@link VerySpecialException}
     *
     * Assert that the subscriber receives:
     * 1. no values.
     * 2. no completion event.
     * 3. on error notification of VerySpecialException.
     */
    @Test
    public void expectVerySpecialExceptionOnErrorEvent() {
        final Observable observable = MyObservableFactory.fromSuppliers(() -> {
            throw new RuntimeException();
        });
        observable.unsafeSubscribe(testSubscriber);
        testSubscriber.assertNotCompleted();
        testSubscriber.assertNoValues();
        testSubscriber.assertError(VerySpecialException.class);
    }

    /**
     * If the event source emits events and thereafter throws an exception, the events should be received
     * by the subscriber, and then it should be notified about the exception.
     *
     * Assert that the subscriber receives:
     * 1. the value returned by the first supplier.
     * 2. no completion event.
     * 3. on error notification of VerySpecialException.
     */
    @Test
    public void expectOnNextEventAndVerySpecialExcpetionOnErrorEvent() {
        final Observable observable = MyObservableFactory.fromSuppliers(() -> 2, () -> {
            throw new RuntimeException();
        });
        observable.unsafeSubscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(2));
        testSubscriber.assertNotCompleted();
        testSubscriber.assertError(VerySpecialException.class);


    }

    /**
     * Even if the event source emits more events, the Observable must not pass them along
     * if and error occured.
     *
     * Assert that the subscriber receives:
     * 1. no values.
     * 2. no completion event.
     * 3. on error notification of VerySpecialException.
     */
    @Test
    public void assertNoNextEventAfterError() {
        final Observable observable = MyObservableFactory.fromSuppliers(() -> {
            throw new RuntimeException();
        }, () -> 2);
        observable.unsafeSubscribe(testSubscriber);
        testSubscriber.assertNoValues();
        testSubscriber.assertNotCompleted();
        testSubscriber.assertError(VerySpecialException.class);
    }

    /**
     * En error thrown by the subscriber should not be caught or wrapped.
     * The Observable could not possibly know how to handle an error arising in one of its subscribers.
     * Therefore the error should really be handled by the Observer itself.
     *
     * Assert that an error thrown by the onNext handler is properly propagated.
     */
    @Test(expected = RuntimeException.class)
    public void testSubscriberThrowsShouldPropagate() {
        final Observable observable = MyObservableFactory.fromSuppliers(() -> 2);
        observable.subscribe(o -> {throw new RuntimeException();});

    }

}