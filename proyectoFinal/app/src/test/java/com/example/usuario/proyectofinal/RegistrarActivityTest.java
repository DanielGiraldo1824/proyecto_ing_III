package com.example.usuario.proyectofinal;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RegistrarActivityTest {

    @Test
    @Before
    public void registrarCliente() throws Exception {
        RegistrarActivity a=new RegistrarActivity();
        String cedula="2323";
        String nombre="daniel";
        String telefono="233212";
        String pass="asdf";
        String idGym="s32";
        assertEquals(true,a!=null);

    }
}