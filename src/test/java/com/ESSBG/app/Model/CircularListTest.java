package com.ESSBG.app.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
            fruits.getNext("Pear");
            fail("Expected IllegalArgumentException when trying to getNext of an element that don't exist.");
        }
        catch (IllegalArgumentException ignored){}
    }

    @Test
    public void testGetPrevious(){
        assertEquals("Cranberry", fruits.getPrevious("Dragon fruit"));
        assertEquals("Dragon fruit", fruits.getPrevious("Apple"));
    }
}
