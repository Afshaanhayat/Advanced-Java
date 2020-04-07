#Lambdas Continued...
The second example will walk you through  the concept of Lambdas with Generics.The purpose of this example Sequence Aggregator is to implement  an aggregation on a list.
##  Sequence Aggregator
The first implementation is [`SequenceAggregator #sum`] which takes a list returns  the sum (aggregation).

```java

public static int sum(Collection<Integer> list){
        int result = 0;
        for(int item : list)
            result = result + item;
        return result;
    }

```

The second implementation is [`SequenceAggregator #product`] which takes a list returns  the product (aggregation).

```java

public static int product(Collection<Integer> list){
        int result = 1;
        for(int item: list)
            result = result * item;
        return result;
    }
```

The third implementation is [`SequenceAggregator #removeNegatives`] which takes a list returns  a list with only positive elements(aggregation).

```java

public static Collection<Integer> removeNegatives(Collection<Integer> list){
        Collection<Integer> result = new ArrayList<>();
        for(Integer item: list)
            if(item >= 0)
                result.add(item);

        return result;
    }    
```
The fourth implementation is [`SequenceAggregator#reduce`] which takes generic initValue , a generic interface, and a generic list and returns a generic type T.    
    
```java

 public static <T> T reduce(T initValue, Aggregator<T> aggregator, Collection<T> list){
        T result = initValue;

        for(T item: list)
            result = aggregator.op(result, item);
        return result;
    }    
```
    
  The fifth implementation is [`SequenceAggregator#betterReduce`] which takes a generic initValue, interface BetterAggregator which can take two generic values, a list
and returns a generic type(ie it can take two generic values and return one of them so its more flexible as compared to reduce) 

```java

  public static <U, T> U betterReduce(U initValue, BetterAggregator<U, T> aggregator, Collection<T> list){
        U result = initValue;

        for(T item: list)
            result = aggregator.op(result, item);
        return result;
    }  
    ```
   