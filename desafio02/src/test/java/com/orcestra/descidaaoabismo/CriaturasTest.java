package com.orcestra.descidaaoabismo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.orcestra.descidaaoabismo.DublesDeTeste.RandomRoteirizado;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Provas 2 (A Piranha) e 3 (O Leviatã). Não alterem. */
class CriaturasTest {

    // ---------- Prova 2: A Piranha ----------

    @Test
    void piranhaTemNomeEVidaCorretos() {
        PiranhaRapida piranha = new PiranhaRapida();
        assertEquals("Piranha Rápida", piranha.getNome(), "O nome da piranha está errado");
        assertEquals(20, piranha.getVida(), "A piranha deve começar com 20 de vida");
    }

    @Test
    void piranhaAtacaDuasVezesPorTurno() {
        PiranhaRapida piranha = new PiranhaRapida();
        RandomRoteirizado random = new RandomRoteirizado(3, 0);

        int dano = piranha.atacar(random);

        assertEquals(5, dano, "O turno da piranha deve somar dois ataques: (3+1) + (0+1)");
        assertEquals(List.of(4, 4), random.limites,
                "Cada ataque da piranha deve sortear com random.nextInt(4) + 1, duas vezes");
    }

    @Test
    void piranhaCausaEntre2e8PorTurno() {
        PiranhaRapida piranha = new PiranhaRapida();
        Random random = new Random(7);
        int menor = Integer.MAX_VALUE;
        int maior = Integer.MIN_VALUE;
        for (int i = 0; i < 1000; i++) {
            int dano = piranha.atacar(random);
            menor = Math.min(menor, dano);
            maior = Math.max(maior, dano);
        }
        assertEquals(2, menor, "O menor dano de um turno da piranha deve ser 2 (1 + 1)");
        assertEquals(8, maior, "O maior dano de um turno da piranha deve ser 8 (4 + 4)");
    }

    @Test
    void piranhaMorreQuandoAVidaZera() {
        PiranhaRapida piranha = new PiranhaRapida();
        piranha.receberDano(19);
        assertTrue(piranha.estaViva(), "Com 1 de vida, a piranha ainda está viva");
        piranha.receberDano(1);
        assertFalse(piranha.estaViva(), "Com a vida zerada, a piranha morreu");
        assertEquals(0, piranha.getVida(), "A vida zerada deve ser 0");
    }

    // ---------- Prova 3: O Leviatã ----------

    @Test
    void leviataTemNomeEVidaCorretos() {
        LeviataNulo leviata = new LeviataNulo();
        assertEquals("Leviatã Nulo", leviata.getNome(), "O nome do leviatã está errado");
        assertEquals(80, leviata.getVida(), "O leviatã deve começar com 80 de vida");
    }

    @Test
    void leviataAtacaUmaVezEntre15e25() {
        LeviataNulo leviata = new LeviataNulo();
        RandomRoteirizado random = new RandomRoteirizado(10);

        int dano = leviata.atacar(random);

        assertEquals(25, dano, "Com o sorteio 10, o dano deve ser 10 + 15");
        assertEquals(List.of(11), random.limites,
                "O leviatã deve fazer um único sorteio, com random.nextInt(11) + 15");

        Random sorteio = new Random(3);
        int menor = Integer.MAX_VALUE;
        int maior = Integer.MIN_VALUE;
        for (int i = 0; i < 1000; i++) {
            int d = leviata.atacar(sorteio);
            menor = Math.min(menor, d);
            maior = Math.max(maior, d);
        }
        assertEquals(15, menor, "O menor dano do leviatã deve ser 15");
        assertEquals(25, maior, "O maior dano do leviatã deve ser 25");
    }

    @Test
    void primeiroGolpeAtravessaOLeviata() {
        LeviataNulo leviata = new LeviataNulo();
        leviata.receberDano(50);
        assertEquals(80, leviata.getVida(), "O primeiro golpe deve atravessar o leviatã sem ferir");
        leviata.receberDano(50);
        assertEquals(30, leviata.getVida(), "O segundo golpe deve ferir normalmente");
    }

    @Test
    void primeiroGolpeContaMesmoSeForZero() {
        LeviataNulo leviata = new LeviataNulo();
        leviata.receberDano(0);
        leviata.receberDano(10);
        assertEquals(70, leviata.getVida(),
                "A primeira chamada de receberDano é a que atravessa, qualquer que seja o valor");
    }

    @Test
    void golpesSeguintesSaoAplicadosNormalmente() {
        LeviataNulo leviata = new LeviataNulo();
        leviata.receberDano(1);
        leviata.receberDano(10);
        leviata.receberDano(10);
        leviata.receberDano(10);
        assertEquals(50, leviata.getVida(), "Depois do primeiro golpe, todo dano deve ser aplicado");
    }

    @Test
    void leviataMorreSemVidaNegativa() {
        LeviataNulo leviata = new LeviataNulo();
        leviata.receberDano(1);
        assertTrue(leviata.estaViva(), "Depois de atravessar o primeiro golpe, o leviatã segue vivo");
        leviata.receberDano(500);
        assertFalse(leviata.estaViva(), "Com dano de sobra, o leviatã morre");
        assertEquals(0, leviata.getVida(), "A vida nunca fica negativa");
    }
}
