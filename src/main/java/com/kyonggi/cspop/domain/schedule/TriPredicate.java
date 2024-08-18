package com.kyonggi.cspop.domain.schedule;

@FunctionalInterface
public interface TriPredicate<T, U, V> {

    boolean test(T t, U u, V v);
}
