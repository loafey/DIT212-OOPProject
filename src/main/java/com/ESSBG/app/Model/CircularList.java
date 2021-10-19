package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emmie berger, Gabriel Hagström
 */
public class CircularList<T> extends ArrayList<T>{

    public CircularList(){
        super();
    }



    public T getNext(T t){
        int i = super.indexOf(t);

        if (i == -1){
            throw new IllegalArgumentException("Element cannot be found in list");
        }
        if (i == super.size() - 1 ){
            return super.get(0);
        }
        else{
            return super.get(i+1);
        }
    }

    public T getPrevious(T t){
        int i = super.indexOf(t);

        if (i == 0){
            return super.get(super.size()-1);
        }
        else{
            return super.get(i-1);
        }
    }
}
