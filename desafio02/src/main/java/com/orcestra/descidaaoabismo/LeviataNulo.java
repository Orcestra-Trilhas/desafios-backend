package com.orcestra.descidaaoabismo;

import java.util.Random;

/**
 * O Leviatã Nulo: sombra do próprio Lorde do abismo. Golpe devastador, e o
 * primeiro golpe que recebe simplesmente o atravessa.
 *
 * <p>⭐ VOCÊS EDITAM ESTE ARQUIVO.
 *
 * <p>TODO:
 * <ul>
 *   <li>nome {@code "Leviatã Nulo"} e vida inicial 80 (ajuste o {@code super});</li>
 *   <li>{@code atacar} causa de 15 a 25 de dano, em um único ataque por turno
 *       ({@code random.nextInt(11) + 15});</li>
 *   <li>sobrescreva {@code receberDano}: a PRIMEIRA chamada não altera a vida,
 *       qualquer que seja o valor. Da segunda em diante, o dano é aplicado
 *       normalmente.</li>
 * </ul>
 */
public class LeviataNulo extends Criatura {

    public LeviataNulo() {
        super("TODO: nome", 0); // TODO: nome e vida corretos
    }

    @Override
    public int atacar(Random random) {
        throw new UnsupportedOperationException("TODO: o leviatã ataca uma vez por turno");
    }
}
