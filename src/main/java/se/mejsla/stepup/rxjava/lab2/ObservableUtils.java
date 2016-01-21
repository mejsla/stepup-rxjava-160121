package se.mejsla.stepup.rxjava.lab2;

import rx.Observable;
import rx.Observer;
import rx.observables.ConnectableObservable;


public class ObservableUtils {

    /**
     * Will recives a hot observable that has not yet started to emit items.
     *
     * @param longObservable the items that will be emitted are a consecutive series of integers, starting with 1.
     * @return an observable of strings that follows the pattern of https://en.wikipedia.org/wiki/Fizz_buzz
     */
    public static Observable<String> fizzBuzzObservable(ConnectableObservable<Long> longObservable) {
        return Observable.merge(
                longObservable.filter(l -> l % 3 == 0 && l % 5 == 0).map(l -> "FizzBuzz"),
                longObservable.filter(l -> l % 3 != 0 && l % 5 == 0).map(l -> "Buzz"),
                longObservable.filter(l -> l % 3 == 0 && l % 5 != 0).map(l -> "Fizz"),
                longObservable.filter(l -> l % 3 != 0 && l % 5 != 0).map(String::valueOf));


    }


    /**
     *
     * @param longObservable an observable which emits a series of integers.
     * @param odds should emit all odd integers that are emitted by the longObservable
     * @param evens should emit all even integers that are emitted by the longObservable
     */
    public static void oddsAndEvens(Observable<Long> longObservable, Observer<Long> odds, Observer<Long> evens) {
        longObservable.filter(l -> l % 2 == 1).subscribe(odds);
        longObservable.filter(l -> l % 2 == 0).subscribe(evens);


    }


}
