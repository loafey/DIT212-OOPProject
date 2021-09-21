package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.List;

public class CircularList <T>{
    private List<T> list;

    public CircularList(){
        list = new ArrayList<>();
    }


    public void add (T t){
        list.add(t);
    }

    public void remove(T t){
        list.remove(t);
    }

    public T get(int i){
        return list.get(i);
    }

    public int size(){
        return list.size();
    }


    public T getNext(T t){
        int i = list.indexOf(t);

        if (i == -1){
            throw new IllegalArgumentException("Element cannot be found in list");
        }
        if (i == list.size() - 1 ){
            return list.get(0);
        }
        else{
            return list.get(i+1);
        }
    }

    public T getPrevious(T t){
        int i = list.indexOf(t);

        if (i == 0){
            return list.get(list.size()-1);
        }
        else{
            return list.get(i-1);
        }
    }
}
