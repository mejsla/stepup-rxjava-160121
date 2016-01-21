package se.mejsla.stepup.rxjava.lab1;

import rx.Observable;

public class Combining {

    /**
     *
     * @param s1 an Observable emitting strings
     * @param s2 an Observable emitting strings
     * @return an Observable containing the values from {@code s1} and {@code s2} concatenated
     */
    public static Observable<String> concatObservables(Observable<String> s1, Observable<String> s2) {
        return  Observable.concat(s1, s2);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param s1 an Observable emitting strings
     * @param s2 an Observable emitting strings
     * @return merge the two Observables {@code s1} and {@code s2}
     */
    public static Observable<String> mergeObservables(Observable<String> s1, Observable<String> s2) {
        return  Observable.merge(s1, s2);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param strings an Observable emitting strings
     * @param interval an Observable emitting long values
     * @return an Observable combining the values from {@code strings} and {@code interval} like this "[intervalValue] - [stringValue]"
     */
    public static Observable<String> zipIt(Observable<String> strings, Observable<Long> interval) {
        return strings.zipWith(interval, (string, time) -> time + " - " + string);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param strings an Observable emitting strings
     * @param interval an Observable emitting Long values
     * @return an Observable combining the latest values from {@code strings} and {@code interval} like this "[stringValue] - [intervalValue]"
     */
    public static Observable<String> combineLatest(Observable<String> strings, Observable<Long> interval) {
        return Observable.combineLatest(strings, interval, (string, counter) -> string + " - " + counter);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param s1 an Observable emitting strings
     * @param s2 an Observable emitting strings
     * @return the data from the fastest source
     */
    public static Observable<String> getFromFastestSource(Observable<String> s1, Observable<String> s2) {
        return Observable.amb(s1, s2);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param o1
     * @param o2
     * @return an Observable where the the the values from each observable have been a
     */
    public static Observable<Integer> sumPairs(Observable<Integer> o1, Observable<Integer> o2) {
        return o1.zipWith(o2, (i1, i2) -> i1 + i2);
    }

    /**
     *
     * @param o1
     * @param o2
     * @return an Observable where the items from both o1 and o2 have been flatted to a single Observable and sorted
     */
    public static Observable<Integer> sortIntegers(Observable<Integer> o1, Observable<Integer> o2) {
        return o1.mergeWith(o2).toSortedList().flatMap(Observable::from);
    }

    /**
     *
     * @param o1
     * @param o2
     * @return an Observable where the even values from o1 and o2 are emitted.
     */
    public static Observable<Integer> getEvens(Observable<Integer> o1, Observable<Integer> o2) {
        return o1.mergeWith(o2).filter((i) -> i % 2 == 0);
    }
}
