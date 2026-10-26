package com.orcestra.provadoselementos;

import java.util.List;

public class ArenaDoArquivista {

    /**
     * O SEU DESAFIO ESTÁ AQUI.
     * Altere este método para derrotar o Guardião.
     *
     * Regras do pergaminho:
     * 1. Vence o ingrediente de nome mais longo.
     * 2. Em caso de empate de tamanho, vence o que vem primeiro em ordem de dicionário.
     * 3. Lista vazia devolve "" (string vazia).
     */
    public static String encontrarIngredientePoderoso(List<String> ingredientes) {

        if (ingredientes.isEmpty()) {
            return "";
        }
        String resultado = "";

        for (int i = 0; i < ingredientes.size(); i++) {

            if (ingredientes.get(i).length() > resultado.length()) {
                resultado = ingredientes.get(i);
                continue;
            }

            else if (ingredientes.get(i).length() == resultado.length() && ingredientes.get(i).compareTo(resultado) < 0) {
                resultado = ingredientes.get(i);
            }
        }
        return resultado;
    }
}
