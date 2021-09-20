package com.ESSBG.app.Model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert.*;

import static junit.framework.TestCase.assertEquals;

public class CircularListTest {
    private CircularList<String> fruits;

    @Before
    public void start(){
         fruits = new CircularList<>();

         fruits.add("Apple");
         fruits.add("Banana");
         fruits.add("Cranberry");
         fruits.add("Dragon fruit");
    }

    @Test
    public void testAdd(){
        fruits.add("Eggplant");

        assertEquals("Eggplant", fruits.get(4));
        assertEquals( 5, fruits.size());
    }

    @Test
    public void testRemove(){
        fruits.remove("Apple");
        assertEquals( "Banana", fruits.get(0));
        assertEquals(3, fruits.size());
    }

    @Test
    public void testGetNext(){
        assertEquals("Cranberry", fruits.getNext("Banana"));
        assertEquals("Apple", fruits.getNext("Dragon fruit"));

        try {
            assertEquals("Blablabla", fruits.getNext("Pear"));
        }
        catch (IllegalArgumentException e){
            System.out.println("That fruit is not in the list");
        }
    }

    @Test
    public void testGetPrevious(){
        assertEquals("Cranberry", fruits.getPrevious("Dragon fruit"));
        assertEquals("Dragon fruit", fruits.getPrevious("Apple"));
    }
}
