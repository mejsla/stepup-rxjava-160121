package se.mejsla.stepup.rxjava.lab1;

import rx.Observable;

public class Accumulating {

    /**
     * @param integers an Observable emitting integers
     * @return an integer Observable where each value is multiplied with the preceding value/values.
     */
    public static Observable<Integer> multiply(Observable<Integer> integers) {
        return Observable.error(new RuntimeException("Not Implemented"));
    }
}
