package com.example.usuario.proyectofinal;

import android.hardware.SensorEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class SentadillasActivityTest {
SensorEvent s;
    @Test
    @After
    public void sentadillasTest() throws Exception {
        SentadillasActivity a=new SentadillasActivity();
        int cantidad=a.sentadillas;
        //a.onSensorChanged(s);
        assertEquals(cantidad, a.sentadillas);

    }

}