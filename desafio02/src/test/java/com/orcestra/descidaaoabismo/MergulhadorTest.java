package com.orcestra.descidaaoabismo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

/** Prova 1: O Fôlego. Já passa desde o começo: é a trava de segurança. Não alterem. */
class MergulhadorTest {

    @Test
    void comecaComOxigenioCheio() {
        Mergulhador mergulhador = new Mergulhador();
        assertEquals(100, mergulhador.getOxigenio(), "O mergulhador deve começar com 100 de oxigênio");
        assertEquals(100, mergulhador.getOxigenioMaximo(), "O máximo de oxigênio deve ser 100");
        assertTrue(mergulhador.estaVivo(), "O mergulhador deve começar vivo");
    }

    @Test
    void danoReduzOxigenio() {
        Mergulhador mergulhador = new Mergulhador();
        mergulhador.receberDano(30);
        assertEquals(70, mergulhador.getOxigenio(), "O dano deve reduzir o oxigênio");
    }

    @Test
    void oxigenioNuncaFicaAbaixoDeZero() {
        Mergulhador mergulhador = new Mergulhador();
        mergulhador.receberDano(500);
        assertEquals(0, mergulhador.getOxigenio(), "O oxigênio nunca pode ficar negativo");
        assertFalse(mergulhador.estaVivo(), "Sem oxigênio, o mergulhador está afogado");
    }

    @Test
    void recuperarNaoPassaDoMaximo() {
        Mergulhador mergulhador = new Mergulhador();
        mergulhador.receberDano(10);
        mergulhador.recuperar(50);
        assertEquals(100, mergulhador.getOxigenio(), "O oxigênio nunca pode passar do máximo");
    }

    @Test
    void recuperarDevolveOxigenioPerdido() {
        Mergulhador mergulhador = new Mergulhador();
        mergulhador.receberDano(30);
        mergulhador.recuperar(10);
        assertEquals(80, mergulhador.getOxigenio(), "Recuperar deve somar ao oxigênio atual");
    }

    @Test
    void valoresNegativosSaoIgnorados() {
        Mergulhador mergulhador = new Mergulhador();
        mergulhador.receberDano(20);
        mergulhador.receberDano(-50);
        mergulhador.recuperar(-50);
        assertEquals(80, mergulhador.getOxigenio(), "Dano e recuperação negativos devem ser ignorados");
    }

    @Test
    void golpeDoMergulhadorFicaEntre5e10() {
        Mergulhador mergulhador = new Mergulhador();
        Random random = new Random(1);
        int menor = Integer.MAX_VALUE;
        int maior = Integer.MIN_VALUE;
        for (int i = 0; i < 500; i++) {
            int dano = mergulhador.atacar(random);
            menor = Math.min(menor, dano);
            maior = Math.max(maior, dano);
        }
        assertEquals(5, menor, "O menor golpe do mergulhador deve ser 5");
        assertEquals(10, maior, "O maior golpe do mergulhador deve ser 10");
    }
}
