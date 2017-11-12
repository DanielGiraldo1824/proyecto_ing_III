package com.example.usuario.proyectofinal;

import android.hardware.SensorEvent;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class FlexionesActivityTest {
SensorEvent s;
    @Test
    @After
    public void flexionesTest() throws Exception {
        ContabilizadorActivity a=new ContabilizadorActivity();
        int cantidad=1;
        assertEquals(cantidad, Float.parseFloat(String.valueOf(s.values[0])));

    }
    @Test
    @After
    public void NoFlexionesTest() throws Exception {
        ContabilizadorActivity a=new ContabilizadorActivity();
        int cantidad=0;
        assertEquals(cantidad, Float.parseFloat(String.valueOf(s.values[4])));
    }
}