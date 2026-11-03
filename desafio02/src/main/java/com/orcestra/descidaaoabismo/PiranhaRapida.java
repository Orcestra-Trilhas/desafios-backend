package com.orcestra.descidaaoabismo;

import java.util.Random;

/**
 * A Piranha Rápida: ferroada fraca, mas ataca duas vezes por turno.
 *
 * <p>⭐ VOCÊS EDITAM ESTE ARQUIVO.
 *
 * <p>TODO:
 * <ul>
 *   <li>nome {@code "Piranha Rápida"} e vida inicial 20 (ajuste o {@code super});</li>
 *   <li>{@code atacar} devolve a SOMA de dois ataques no mesmo turno. Cada
 *       ataque causa de 1 a 4 de dano, sorteado com o {@code Random} recebido
 *       ({@code random.nextInt(4) + 1}).</li>
 * </ul>
 */
public class PiranhaRapida extends Criatura {

    public PiranhaRapida() {
        super("TODO: nome", 0); // TODO: nome e vida corretos
    }

    @Override
    public int atacar(Random random) {
        throw new UnsupportedOperationException("TODO: a piranha ataca duas vezes por turno");
    }
}
