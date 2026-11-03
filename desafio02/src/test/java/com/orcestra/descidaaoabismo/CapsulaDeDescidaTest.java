package com.orcestra.descidaaoabismo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.orcestra.descidaaoabismo.DublesDeTeste.CriaturaFalsa;
import com.orcestra.descidaaoabismo.DublesDeTeste.RandomRegistrador;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Prova 6: A Cápsula. Não alterem. */
class CapsulaDeDescidaTest {

    @Test
    void sonarConduzCombate() {
        for (long semente = 0; semente < 50; semente++) {
            Mergulhador mergulhador = new Mergulhador();
            List<Criatura> criaturas = List.of(new PiranhaRapida(), new LeviataNulo());

            ResultadoMergulho resultado =
                    new CapsulaDeDescida(new Random(semente)).descer(mergulhador, criaturas);

            if (resultado == ResultadoMergulho.SOBREVIVEU) {
                assertTrue(mergulhador.estaVivo(),
                        "Semente " + semente + ": SOBREVIVEU exige o mergulhador vivo");
                for (Criatura criatura : criaturas) {
                    assertFalse(criatura.estaViva(),
                            "Semente " + semente + ": SOBREVIVEU exige todas as criaturas mortas");
                }
            } else {
                assertEquals(ResultadoMergulho.AFOGADO, resultado, "Resultado inesperado");
                assertFalse(mergulhador.estaVivo(),
                        "Semente " + semente + ": AFOGADO exige o oxigênio zerado");
            }
        }
    }

    @Test
    void mesmoRandomGeraMesmoMergulho() {
        Mergulhador primeiro = new Mergulhador();
        Mergulhador segundo = new Mergulhador();

        ResultadoMergulho a = new CapsulaDeDescida(new Random(123))
                .descer(primeiro, List.of(new PiranhaRapida(), new LeviataNulo()));
        ResultadoMergulho b = new CapsulaDeDescida(new Random(123))
                .descer(segundo, List.of(new PiranhaRapida(), new LeviataNulo()));

        assertEquals(a, b, "Com a mesma semente, o resultado deve ser o mesmo");
        assertEquals(primeiro.getOxigenio(), segundo.getOxigenio(),
                "Com a mesma semente, o oxigênio restante deve ser o mesmo");
    }

    @Test
    void semCriaturasOMergulhadorSobrevive() {
        Mergulhador mergulhador = new Mergulhador();
        ResultadoMergulho resultado =
                new CapsulaDeDescida(new Random(1)).descer(mergulhador, List.of());

        assertEquals(ResultadoMergulho.SOBREVIVEU, resultado, "Sem criaturas, não há o que enfrentar");
        assertEquals(100, mergulhador.getOxigenio(), "Sem combate, o oxigênio não muda");
    }

    @Test
    void criaturaJaMortaEIgnorada() {
        List<String> registro = new ArrayList<>();
        Mergulhador mergulhador = new Mergulhador();
        List<Criatura> criaturas = List.of(new CriaturaFalsa("Morta", 0, 50, registro));

        ResultadoMergulho resultado =
                new CapsulaDeDescida(new Random(1)).descer(mergulhador, criaturas);

        assertEquals(ResultadoMergulho.SOBREVIVEU, resultado, "Uma criatura morta não luta");
        assertTrue(registro.isEmpty(), "Uma criatura morta não pode atacar");
        assertEquals(100, mergulhador.getOxigenio(), "O oxigênio não pode mudar");
    }

    @Test
    void criaturaFracaEDerrotada() {
        List<String> registro = new ArrayList<>();
        Mergulhador mergulhador = new Mergulhador();
        List<Criatura> criaturas = List.of(new CriaturaFalsa("Fraca", 1, 0, registro));

        ResultadoMergulho resultado =
                new CapsulaDeDescida(new Random(1)).descer(mergulhador, criaturas);

        assertEquals(ResultadoMergulho.SOBREVIVEU, resultado, "O golpe do mergulhador deve derrotar a criatura");
        assertFalse(criaturas.get(0).estaViva(), "A criatura deve estar morta");
        assertTrue(registro.isEmpty(), "Quem morre no golpe do mergulhador não ataca de volta");
    }

    @Test
    void criaturaLetalAfogaOMergulhador() {
        List<String> registro = new ArrayList<>();
        Mergulhador mergulhador = new Mergulhador();
        List<Criatura> criaturas = List.of(new CriaturaFalsa("Letal", 10_000, 999, registro));

        ResultadoMergulho resultado =
                new CapsulaDeDescida(new Random(1)).descer(mergulhador, criaturas);

        assertEquals(ResultadoMergulho.AFOGADO, resultado, "999 de dano por turno afoga qualquer um");
        assertFalse(mergulhador.estaVivo(), "O mergulhador deve estar sem oxigênio");
    }

    @Test
    void ordemDosSorteiosEDosAtaques() {
        List<String> registro = new ArrayList<>();
        Mergulhador mergulhador = new Mergulhador();
        List<Criatura> criaturas = List.of(
                new CriaturaFalsa("A", 10, 0, registro),
                new CriaturaFalsa("B", 10, 0, registro));

        ResultadoMergulho resultado =
                new CapsulaDeDescida(new RandomRegistrador(registro)).descer(mergulhador, criaturas);

        // M = golpe do mergulhador (dano 5). Ele sempre golpeia a primeira criatura viva.
        // Turno 1: M fere A; A e B atacam. Turno 2: M mata A; só B ataca.
        // Turno 3: M fere B; B ataca. Turno 4: M mata B e a descida termina sem ataque.
        assertEquals(List.of("M", "A", "B", "M", "B", "M", "B", "M"), registro,
                "Ordem errada: o mergulhador golpeia primeiro, depois cada criatura viva ataca na ordem da lista");
        assertEquals(ResultadoMergulho.SOBREVIVEU, resultado, "As duas criaturas deveriam ter morrido");
    }

    @Test
    void mergulhadorMorreNoMeioDoTurnoEAsDemaisNaoAtacam() {
        List<String> registro = new ArrayList<>();
        Mergulhador mergulhador = new Mergulhador();
        List<Criatura> criaturas = List.of(
                new CriaturaFalsa("A", 100, 999, registro),
                new CriaturaFalsa("B", 100, 1, registro));

        ResultadoMergulho resultado =
                new CapsulaDeDescida(new RandomRegistrador(registro)).descer(mergulhador, criaturas);

        assertEquals(List.of("M", "A"), registro,
                "Se o mergulhador morre, as criaturas seguintes do turno não atacam");
        assertEquals(ResultadoMergulho.AFOGADO, resultado, "O mergulhador deveria ter se afogado");
    }

    @Test
    void naoAlteraAListaRecebida() {
        List<String> registro = new ArrayList<>();
        Criatura a = new CriaturaFalsa("A", 5, 0, registro);
        Criatura b = new CriaturaFalsa("B", 5, 0, registro);
        // List.of devolve uma lista imutável: qualquer tentativa de alterá-la lança exceção.
        List<Criatura> criaturas = List.of(a, b);

        new CapsulaDeDescida(new Random(1)).descer(new Mergulhador(), criaturas);

        assertEquals(2, criaturas.size(), "A lista recebida não pode perder criaturas");
        assertTrue(criaturas.get(0) == a && criaturas.get(1) == b,
                "A lista recebida não pode ser reordenada");
    }
}
