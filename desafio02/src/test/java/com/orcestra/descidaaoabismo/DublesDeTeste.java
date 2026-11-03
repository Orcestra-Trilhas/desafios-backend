package com.orcestra.descidaaoabismo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Apoio dos testes: sorteios e criaturas de mentira para cenários controlados.
 * Não altere.
 */
final class DublesDeTeste {

    private DublesDeTeste() {
    }

    /**
     * Random que devolve valores combinados, em sequência (volta ao início quando acaba).
     * Registra o limite (bound) de cada chamada a nextInt(bound).
     */
    static class RandomRoteirizado extends Random {
        private final int[] valores;
        private int proximo = 0;
        final List<Integer> limites = new ArrayList<>();

        RandomRoteirizado(int... valores) {
            this.valores = valores;
        }

        @Override
        public int nextInt(int bound) {
            limites.add(bound);
            int valor = valores[proximo % valores.length];
            proximo++;
            if (valor < 0 || valor >= bound) {
                throw new IllegalStateException("valor roteirizado " + valor + " fora de [0, " + bound + ")");
            }
            return valor;
        }
    }

    /**
     * Random que anota "M" no registro sempre que o mergulhador sorteia o próprio golpe
     * (nextInt(6)) e sempre devolve 0 (golpe de dano 5).
     */
    static class RandomRegistrador extends Random {
        private final List<String> registro;

        RandomRegistrador(List<String> registro) {
            this.registro = registro;
        }

        @Override
        public int nextInt(int bound) {
            if (bound == 6) {
                registro.add("M");
            }
            return 0;
        }
    }

    /**
     * Criatura de mentira: causa sempre o mesmo dano por turno e anota o próprio nome
     * no registro toda vez que ataca.
     */
    static class CriaturaFalsa extends Criatura {
        private final int danoPorTurno;
        private final List<String> registro;

        CriaturaFalsa(String nome, int vida, int danoPorTurno, List<String> registro) {
            super(nome, vida);
            this.danoPorTurno = danoPorTurno;
            this.registro = registro;
        }

        @Override
        public int atacar(Random random) {
            registro.add(getNome());
            return danoPorTurno;
        }
    }
}
