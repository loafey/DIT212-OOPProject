package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.MonumentFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class MonumentFactoryTest {
    List<Monument> monuments;

    @Before
    public void init(){
        monuments = MonumentFactory.getMonuments();
    }

    @Test
    public void testSizeOfMonumentList(){
        assertEquals(7, monuments.size());
    }

    @Test
    public void testAllMonumentsAreDifferent(){
        List<String> tmp = new ArrayList<>();
        boolean noDuplicates = true;

        for (Monument m : monuments){
            if (tmp.contains(m.getName())){
                noDuplicates = false;
                break;
            }
            tmp.add(m.getName());
        }

        assertTrue(noDuplicates);
    }

    @Test
    public void testShuffle(){
        List<Monument> tmp1 = MonumentFactory.getMonuments();
        List<Monument> tmp2 = MonumentFactory.getMonuments();

        int elementsThatDiffer = 0;

        for (int i=0; i<tmp1.size(); i++){
            if (!tmp1.get(i).getName().equals(tmp2.get(i).getName())){
                elementsThatDiffer++;
            }
        }

        assertTrue(elementsThatDiffer > 3);
    }
}
