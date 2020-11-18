package com.squad.parker.processor;

public interface Processor<T, V> {

    public V process(T input);

}