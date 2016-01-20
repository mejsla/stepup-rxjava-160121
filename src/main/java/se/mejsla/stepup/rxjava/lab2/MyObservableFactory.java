package se.mejsla.stepup.rxjava.lab2;

import rx.Observable;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

public class MyObservableFactory {

    public static <T> Observable<T> fromSuppliers(Supplier<T>... s) {
        return fromSuppliers(Arrays.asList(s));
    }


    /**
     * This method takes an {@link Iterable<Supplier<T>>} and returns an {@link Observable<T>} that emits the values
     * provided by the suppliers. Make sure it fulfils The Observable Contract.
     * See: http://reactivex.io/documentation/contract.html
     *
     * @param suppliers - the sources of values for this Observable to emit to its subscribers.
     * @param <T>       type of values emitted by the returned Observable
     * @return an {@link Observable<T>} that emits the values
     * provided by the suppliers.
     */
    public static <T> Observable<T> fromSuppliers(final Collection<Supplier<T>> suppliers) {
        // TODO: Implement me. Maybe using Observable.create()....
        return Observable.empty();


    }
}
