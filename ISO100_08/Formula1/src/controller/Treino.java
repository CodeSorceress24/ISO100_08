package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Treino {
    private final Semaphore semaforoPista = new Semaphore(5); 
    private final List<Carro> gridLargada = new ArrayList<>();

    public void entrarNaPista(Carro carro) throws InterruptedException {
        semaforoPista.acquire();
        carro.getEquipe().entrar();
        System.out.printf("%s da equipe %s entrou na pista.\n", carro.getNomePiloto(), carro.getEquipe().getNome());
    }

    public void sairDaPista(Carro carro) {
        System.out.printf("%s da equipe %s saiu da pista.\n", carro.getNomePiloto(), carro.getEquipe().getNome());
        carro.getEquipe().sair(); 
        semaforoPista.release();

        synchronized (gridLargada) {
            gridLargada.add(carro);
        }
    }

    public void exibirGridLargada() {
        gridLargada.sort((carro1, carro2) -> Double.compare(carro1.getMelhorVolta(), carro2.getMelhorVolta()));
        System.out.println("\n--- Grid de Largada ---");
        for (int i = 0; i < gridLargada.size(); i++) {
            Carro carro = gridLargada.get(i);
            System.out.printf("%dº - %s (%s) com tempo: %.2f segundos\n", 
                              i + 1, carro.getNomePiloto(), carro.getEquipe().getNome(), carro.getMelhorVolta());
        }
    }
}