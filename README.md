# Stepup Reactive Extension for Java

## Förberedelser
För att komma igång med labbandet nu på torsdag (21/1) så vore det bra om alla ser till att ha gjort följande tre steg.
Det som behövs är: Git, Java 8 och Maven

1. Klona detta repo 
   `git clone git@github.com:mejsla/stepup-rxjava-160121.git`
2. Ställ dig i roten på repot och kör: 
   `mvn clean install`
3. Kör däreter: 
   `java -cp target/rx-java-stepup-1.0-SNAPSHOT-jar-with-dependencies.jar se.mejsla.stepup.rxjava.HelloWorld`



## Intro
Intro till reaktiv programmering och RxJava.

### Vad
Reaktiv programmering är en paradigm som kretsar kring fortplantingen av förändring.
Dvs.
Om ett program fortplantar alla förändingar som modifierar dess data till berörda parter kan programmet kallas reaktivt.

`a = b + c`

Vad händer när `b` uppdateras? Skillnaden mellan imperativ och reaktiv programmering.

### Varför

Reaktiv programmering möjligggör, gör det lättare att XXX.


#### Reactive system
- Modular/Dynamic
- Scalable
- Fault-tolerent
- Responsive

Ni kanske har hört om [The Reactive Manifesto](http://www.reactivemanifesto.org) som definierar dessa fyra principer.

## RxJava
RxJava är ett Java-bibliotek som tillhandahåller komponenter för att skriva reaktiv kod i Java.
Vid hantering av mer än en händelse eller asynkront beräkning blir det lätt rörigt för att hålla koll på vad som har skett och ordningen.


- Synkront
    - ett värde: T getData()
    - flera värden: Iterable<T> getData()
- Asynkront
    - ett värde: Future<T> getData()
    - flera värden: __Observable<T> getData()__ (rx.Observable)

Asynkront accessa en sekvens av värden.

### Iterator pattern vs Observable
Vid andvändning av en iterator drar (pull) du värden från iteratorn och blockerar på hasNext()/next().
En Observable ger trycks (push) värden ut när de finns tillgängliga.


### Hot & Cold Observables
XXX


### Transformations
- flatMap vs concatMap
- switchMap
- groupBy

### Accumulating
- scan


### combining
- combineLatest
- concat
- amb
- zipWith
- mergeWith
- (takeUntil)
- (takeWhile)
- (skipUntil)
- (skipWhile)

## Error Handling
Errors terminate the _Observable_ chain of actions.
_return*_, _retry*_ and _resume*_ will allow you to  execute some logic isntead of failing the program.


### What else is there in RxJava
- Schedulers
- Parallelism
- Buffering, Throttling, Debouncing
- Resource Management
- Observable.cache
- Custom Operators
