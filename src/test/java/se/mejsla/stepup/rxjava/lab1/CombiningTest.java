package se.mejsla.stepup.rxjava.lab1;

import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CombiningTest {

    final Random rand = new Random(1337);

    TestSubscriber<Object> testSubscriber;

    @Before
    public void before() {
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void concatTest() throws Exception {
        Observable<String> a = getDataWithInterval(Arrays.asList("One", "Two"), 100L);
        Observable<String> b = getDataWithInterval(Arrays.asList("Three", "Four"), 10L);

        Observable<String> observable = Combining.concatObservables(a, b);

        observable.toBlocking().subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList("One", "Two", "Three", "Four"));
    }

    @Test
    public void mergeTest() throws Exception {
        Observable<String> a = getDataWithInterval(Arrays.asList("One", "Two"), 10L);
        Observable<String> b = getDataWithInterval(Arrays.asList("Three", "Four"), 15L);

        Observable<String> observable = Combining.mergeObservables(a, b);

        observable.toBlocking().subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList("One", "Three", "Two", "Four"));
    }

    @Test
    public void zipItTest() throws Exception {
        Observable<String> observable = Combining.zipIt(
                Observable.just("Zero", "One", "Two", "Three"),
                Observable.interval(10L, TimeUnit.MILLISECONDS));

        observable.toBlocking().subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList("0 - Zero", "1 - One", "2 - Two", "3 - Three"));
    }

    @Test
    public void combineLatestTest() throws Exception {
        Observable<String> observable = Combining.combineLatest(
                getDataWithInterval(Arrays.asList("Zero", "One"), 35L),
                Observable.interval(20L, TimeUnit.MILLISECONDS).take(3));

        observable.toBlocking().subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList("Zero - 0", "Zero - 1", "Zero - 2", "One - 2"));
    }

    @Test
    public void getFastestSource() throws Exception {
        Observable<String> slowSource = getDataWithInterval(Arrays.asList("One", "Two"), 100L);
        Observable<String> fastSource = getDataWithInterval(Arrays.asList("Three", "Four"), 10L);

        Observable<String> observable = Combining.getFromFastestSource(slowSource, fastSource);

        observable.toBlocking().subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList("Three", "Four"));
    }

    @Test
    public void testSumPairs() throws Exception {
        Observable result = Combining.sumPairs(Observable.just(0,2,3,5), Observable.just(0,4,3,77));

        result.subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(0,6,6,82));
        testSubscriber.assertCompleted();
    }

    @Test
    public void sortIntegers() throws Exception {
        List<Integer> ints = createListOfIntegers(10);
        Observable result = Combining.sortIntegers(Observable.from(ints.subList(0,5)), Observable.from(ints.subList(5,10)));

        TestSubscriber<Integer> intTestSubscriber = new TestSubscriber();
        result.subscribe(intTestSubscriber);
        intTestSubscriber.awaitTerminalEvent();

        Collections.sort(ints);
        intTestSubscriber.assertReceivedOnNext(ints);
        intTestSubscriber.assertCompleted();
    }

    private List<Integer> createListOfIntegers(int len) {
        return rand.ints(len).boxed().collect(Collectors.toList());
    }

    private Observable<String> getDataWithInterval(List<String> data, long interval) {
        return Observable.zip(Observable.from(data), Observable.interval(interval, TimeUnit.MILLISECONDS), (s, aLong) -> s);
    }
}