package com.orcestra.descidaaoabismo;

import java.util.List;
import java.util.Random;

/**
 * Campo de treino: edite à vontade para testar os seus próprios cenários.
 *
 * <p>Para rodar (dentro da pasta desafio02/):
 * <pre>
 * ./mvnw compile
 * java -cp target/classes com.orcestra.descidaaoabismo.App
 * </pre>
 *
 * <p>Enquanto vocês não implementarem as classes ⭐, este programa termina com
 * uma exceção {@code UnsupportedOperationException}. Isso é esperado.
 */
public class App {

    public static void main(String[] args) {
        Random random = new Random(42); // semente fixa: o mesmo mergulho se repete
        CapsulaDeDescida capsula = new CapsulaDeDescida(random);
        Mergulhador mergulhador = new Mergulhador();

        List<Criatura> criaturas = List.of(new PiranhaRapida(), new LeviataNulo());

        ResultadoMergulho resultado = capsula.descer(mergulhador, criaturas);

        System.out.println("Resultado: " + resultado);
        System.out.println("Oxigênio restante: " + mergulhador.getOxigenio()
                + "/" + mergulhador.getOxigenioMaximo());
        for (Criatura criatura : criaturas) {
            System.out.println("- " + criatura.getNome() + ": vida " + criatura.getVida());
        }

        // TODO: invente cenários difíceis. Uma lista vazia, uma lista só com piranhas,
        // um mergulhador quase sem oxigênio (mergulhador.receberDano(95)), um colete
        // com muitos itens...
    }
}
