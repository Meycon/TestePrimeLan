package com.meycon.primecep;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de Teste Unitário;
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void verificaCepEstaCorreto(){
        String cep = "29026450";
        assertNotNull(cep);
        assertEquals(8, cep.length());
    }

    @Test
    public void verificaCepEstaErrado(){
        String cep = "290260";
        assertNotNull(cep);
        assertEquals(8, cep.length());
    }
}