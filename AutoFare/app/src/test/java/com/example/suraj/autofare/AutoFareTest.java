package com.example.suraj.autofare;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Suraj on 7/23/2017.
 */

public class AutoFareTest {

    @Test
    public void calcFare () throws Exception{

        assertEquals("Fares do not match",41f,MainActivity.calcFare(4,0),0.001f);
      //  assertEquals("Fares do not match",82f,MainActivity.calcFare(8,24),0.001f);

    }
}
