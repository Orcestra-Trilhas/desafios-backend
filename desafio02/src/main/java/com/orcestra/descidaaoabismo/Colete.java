package com.orcestra.descidaaoabismo;

import java.util.List;

/**
 * O colete do mergulhador.
 *
 * <p>⭐ VOCÊS EDITAM ESTE ARQUIVO.
 *
 * <p>TODO (Lei 2): hoje o colete tem três bolsos e rasga no quarto item.
 * Reajuste-o para carregar quantos itens vierem. No final, não pode sobrar
 * nenhum array dentro desta classe.
 *
 * <p>TODO (Lei 3): implemente {@link #listarPorValor()}. Ela devolve uma
 * NOVA lista, ordenada pela ordem natural de {@link Item}, e não pode alterar
 * a ordem interna do colete.
 *
 * <p>Os itens recebidos nunca são {@code null}.
 */
public class Colete {

    private final Item[] itens = new Item[3];
    private int quantidade = 0;

    /** Guarda o item no colete. Sem limite de quantidade. */
    public void adicionar(Item item) {
        itens[quantidade++] = item;
    }

    /** Quantos itens há no colete. */
    public int quantidade() {
        return quantidade;
    }

    /** Devolve uma nova lista com os itens em ordem de manifesto. */
    public List<Item> listarPorValor() {
        throw new UnsupportedOperationException("TODO: liste os itens em ordem de manifesto");
    }
}
