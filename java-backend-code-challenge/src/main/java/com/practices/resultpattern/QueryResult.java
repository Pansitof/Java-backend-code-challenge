package com.practices.resultpattern;

public class QueryResult<T> extends Result{
    private T data;

    public QueryResult(T data){
        this.data = data;
    }

    public T getData(){
        return this.data;
    }

}
