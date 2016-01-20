package se.mejsla.stepup.rxjava.lab1;

import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.observables.GroupedObservable;
import rx.observers.TestSubscriber;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TransformationsTest {

    TestSubscriber<Object> testSubscriber;

    @Before
    public void before() {
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void helloMapTest() {
        Observable<String> observable = Transformations.helloMap(Observable.just("Jane Doe"));

        observable.subscribe(testSubscriber);
        testSubscriber.assertValue("Hello Jane Doe");
        testSubscriber.assertCompleted();
    }

    @Test
    public void flatMapTest() {
        int range = 2;
        Observable<Integer> observable = Transformations.mapToRange(Observable.just(1, 3, 5), range);

        observable.subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(1, 2, 3, 4, 5, 6));
        testSubscriber.assertCompleted();
    }

    @Test
    public void testSumEqual() {
        Transformations.sumOfAllEvenNumbers(Observable.just(2,7,13,4,8,44,5,7))
                .subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(58));
    }

    @Test
    public void groupingTest() {
        Observable<GroupedObservable<Integer, String>> group =
                Transformations.groupBySentenceLength(Observable.just(
                        "Love You Till Tuesday", "The Prettiest Star", "Hang On to Yourself", "Holy Holy", "Life on Mars?"));

        TestSubscriber<GroupedObservable> testSubscriber = new TestSubscriber<>();

        group.subscribe(testSubscriber);
        testSubscriber.assertValueCount(3);

        List<GroupedObservable> onNextEvents = testSubscriber.getOnNextEvents();
        TestSubscriber testGroupSubscriber = new TestSubscriber();
        onNextEvents.get(0).subscribe(testGroupSubscriber);
        assertEquals("Incorrect key", 4, onNextEvents.get(0).getKey());
        testGroupSubscriber.assertReceivedOnNext(Arrays.asList("Love You Till Tuesday", "Hang On to Yourself"));
    }
}