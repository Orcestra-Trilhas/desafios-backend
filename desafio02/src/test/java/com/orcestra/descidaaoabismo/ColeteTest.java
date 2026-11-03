package com.orcestra.descidaaoabismo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Provas 4 (O Colete) e 5 (O Manifesto). Não alterem. */
class ColeteTest {

    private static List<String> nomes(List<Item> itens) {
        List<String> nomes = new ArrayList<>();
        for (Item item : itens) {
            nomes.add(item.getNome());
        }
        return nomes;
    }

    // ---------- Prova 4: O Colete ----------

    @Test
    void carregaCincoItens() {
        Colete colete = new Colete();
        for (int i = 1; i <= 5; i++) {
            colete.adicionar(new Item("Item " + i, i));
        }
        assertEquals(5, colete.quantidade(), "O colete deve carregar 5 itens sem rasgar");
    }

    @Test
    void carregaMuitosItens() {
        Colete colete = new Colete();
        for (int i = 1; i <= 60; i++) {
            colete.adicionar(new Item("Item " + i, i));
        }
        assertEquals(60, colete.quantidade(), "O colete não deve ter limite de itens");
    }

    @Test
    void listarNaoAlteraOColete() {
        Colete colete = new Colete();
        colete.adicionar(new Item("Corda", 10));
        colete.adicionar(new Item("Lanterna", 50));
        colete.adicionar(new Item("Arpao", 30));
        colete.adicionar(new Item("Boia", 5));
        colete.adicionar(new Item("Cinzel", 20));

        List<Item> primeira = colete.listarPorValor();
        List<Item> segunda = colete.listarPorValor();

        assertEquals(5, colete.quantidade(), "Listar não pode mudar a quantidade de itens");
        assertNotSame(primeira, segunda, "Cada chamada deve devolver uma NOVA lista");
        assertEquals(nomes(primeira), nomes(segunda), "Duas listagens seguidas devem ser iguais");
    }

    // ---------- Prova 5: O Manifesto ----------

    @Test
    void listaOMaiorValorPrimeiro() {
        Colete colete = new Colete();
        colete.adicionar(new Item("Corda", 10));
        colete.adicionar(new Item("Lanterna", 50));
        colete.adicionar(new Item("Arpao", 30));

        assertEquals(List.of("Lanterna", "Arpao", "Corda"), nomes(colete.listarPorValor()),
                "O manifesto lista do item mais valioso para o menos valioso");
    }

    @Test
    void empateDeValorUsaOrdemDeDicionario() {
        Colete colete = new Colete();
        colete.adicionar(new Item("Cinzel", 10));
        colete.adicionar(new Item("Zinco", 20));
        colete.adicionar(new Item("Boia", 10));
        colete.adicionar(new Item("Arpao", 10));

        assertEquals(List.of("Zinco", "Arpao", "Boia", "Cinzel"), nomes(colete.listarPorValor()),
                "Em empate de valor, vale o nome que vem primeiro na ordem de dicionário");
    }

    @Test
    void ordenaTodosOsItensDeUmColeteCheio() {
        Colete colete = new Colete();
        for (int i = 1; i <= 20; i++) {
            colete.adicionar(new Item("Item " + i, (i * 7) % 20));
        }
        List<Item> lista = colete.listarPorValor();

        assertEquals(20, lista.size(), "A listagem deve ter todos os itens do colete");
        for (int i = 1; i < lista.size(); i++) {
            int anterior = lista.get(i - 1).getValor();
            int atual = lista.get(i).getValor();
            assertTrue(anterior >= atual,
                    "Fora de ordem na posição " + i + ": " + lista.get(i - 1) + " antes de " + lista.get(i));
        }
    }
}
