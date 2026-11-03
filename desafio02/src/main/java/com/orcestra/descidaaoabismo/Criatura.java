package com.orcestra.descidaaoabismo;

import java.util.Random;

/**
 * Contrato de toda criatura do abismo.
 *
 * <p>NÃO EDITE ESTE ARQUIVO.
 *
 * <p>O Sonar da cápsula só conhece estas três operações: {@link #estaViva()},
 * {@link #atacar(Random)} e {@link #receberDano(int)}. É a Lei do Sonar.
 */
public abstract class Criatura {

    private final String nome;
    private int vida;

    protected Criatura(String nome, int vida) {
        this.nome = nome;
        this.vida = Math.max(0, vida);
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public boolean estaViva() {
        return vida > 0;
    }

    /**
     * Reduz a vida da criatura. A vida nunca fica abaixo de zero.
     * Dano negativo é tratado como zero.
     */
    public void receberDano(int dano) {
        vida = Math.max(0, vida - Math.max(0, dano));
    }

    /**
     * Devolve o dano total que a criatura causa no mergulhador em UM turno.
     * Use apenas o {@code Random} recebido para sortear valores.
     */
    public abstract int atacar(Random random);
}
