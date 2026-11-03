package com.orcestra.descidaaoabismo;

import java.util.List;
import java.util.Random;

/**
 * O Sonar da cápsula: conduz o combate entre o mergulhador e as criaturas.
 *
 * <p>⭐ VOCÊS EDITAM ESTE ARQUIVO.
 *
 * <p>LEI DO SONAR: aqui, e em qualquer outra classe de vocês, é proibido usar
 * {@code instanceof}, {@code getClass()}, cast de {@code Criatura} ou comparar
 * {@code getNome()} para decidir comportamento. O Sonar só pode usar
 * {@code estaViva()}, {@code atacar(random)} e {@code receberDano(int)}.
 *
 * <p>TODO: implemente {@link #descer(Mergulhador, List)} com as regras:
 * <ol>
 *   <li>Se nenhuma criatura da lista estiver viva no início, devolva
 *       {@code SOBREVIVEU}.</li>
 *   <li>Enquanto o mergulhador estiver vivo e houver criatura viva, repita o turno:
 *     <ol type="a">
 *       <li>o mergulhador golpeia a PRIMEIRA criatura viva da lista:
 *           {@code criatura.receberDano(mergulhador.atacar(random))};</li>
 *       <li>se ainda houver criatura viva, cada criatura viva ataca, NA ORDEM
 *           DA LISTA: {@code mergulhador.receberDano(criatura.atacar(random))}.
 *           Se o mergulhador morrer, o turno é interrompido e as criaturas
 *           restantes não atacam.</li>
 *     </ol>
 *   </li>
 *   <li>Ao final, devolva {@code SOBREVIVEU} se não restou criatura viva, ou
 *       {@code AFOGADO} se o oxigênio do mergulhador chegou a zero.</li>
 * </ol>
 *
 * <p>Regras extras: use SEMPRE o {@code Random} recebido no construtor (nunca
 * crie um {@code new Random()}), respeite a ordem dos sorteios (primeiro o
 * mergulhador, depois cada criatura na ordem da lista) e NÃO altere a lista
 * recebida. A lista nunca é {@code null}.
 */
public class CapsulaDeDescida {

    private final Random random;

    public CapsulaDeDescida(Random random) {
        this.random = random;
    }

    public ResultadoMergulho descer(Mergulhador mergulhador, List<Criatura> criaturas) {
        throw new UnsupportedOperationException("TODO: conduza o combate seguindo as regras do Sonar");
    }
}
