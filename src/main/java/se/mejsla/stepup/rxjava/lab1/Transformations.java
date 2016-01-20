package se.mejsla.stepup.rxjava.lab1;

import rx.Observable;
import rx.observables.GroupedObservable;

public class Transformations {

    public static Observable<String> helloMap(Observable<String> name) {
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param source an Observable emitting integers
     * @param range the size of the range
     * @return For each value of the incoming integer observable create an integer range
     * with the {@code source} as staring point and {@code range} as the size of the integer range.
     */
    public static Observable<Integer> mapToRange(Observable<Integer> source, int range) {
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param strings an Observable emitting strings
     * @return strings grouped by length (words are separated by a space)
     */
    public static Observable<GroupedObservable<Integer, String>> groupBySentenceLength(Observable<String> strings) {
        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param integers an Observable emitting integers
     * @return and Observable
     */
    public static Observable<Integer> sumOfAllEvenNumbers(Observable<Integer> integers) {
        return Observable.error(new RuntimeException("Not Implemented"));
    }


}
