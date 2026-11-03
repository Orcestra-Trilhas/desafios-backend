package com.orcestra.descidaaoabismo;

/**
 * Um item do colete do mergulhador.
 *
 * <p>⭐ VOCÊS EDITAM ESTE ARQUIVO.
 *
 * <p>TODO (Lei 3, o manifesto do Instituto): faça {@code Item} implementar
 * {@code Comparable<Item>} para que a ordem natural seja:
 * <ol>
 *   <li>o item de MAIOR valor vem primeiro;</li>
 *   <li>em empate de valor, vem primeiro o nome que vier antes na ordem de
 *       {@code String.compareTo}.</li>
 * </ol>
 */
public class Item {

    private final String nome;
    private final int valor;

    public Item(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return nome + " (valor " + valor + ")";
    }
}
