package com.orcestra.descidaaoabismo;

import java.util.Random;

/**
 * O herói da descida. O oxigênio dele funciona como vida: nunca fica abaixo
 * de zero nem acima do máximo.
 *
 * <p>NÃO EDITE ESTE ARQUIVO.
 */
public class Mergulhador {

    private static final int OXIGENIO_MAXIMO = 100;

    private int oxigenio = OXIGENIO_MAXIMO;

    public int getOxigenio() {
        return oxigenio;
    }

    public int getOxigenioMaximo() {
        return OXIGENIO_MAXIMO;
    }

    public boolean estaVivo() {
        return oxigenio > 0;
    }

    /** Reduz o oxigênio, sem deixá-lo abaixo de zero. Valor negativo é ignorado. */
    public void receberDano(int dano) {
        oxigenio = Math.max(0, oxigenio - Math.max(0, dano));
    }

    /** Recupera oxigênio, sem passar do máximo. Valor negativo é ignorado. */
    public void recuperar(int quantidade) {
        oxigenio = Math.min(OXIGENIO_MAXIMO, oxigenio + Math.max(0, quantidade));
    }

    /** Dano do golpe do mergulhador: de 5 a 10, sorteado com o Random recebido. */
    public int atacar(Random random) {
        return random.nextInt(6) + 5;
    }
}
