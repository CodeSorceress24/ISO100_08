package view;

import controller.Carro;
import controller.Equipe;
import controller.Treino;

public class Main {
    public static void main(String[] args) {
        Treino treino = new Treino();

        Equipe[] equipes = {
            new Equipe("Ferrari"), new Equipe("Mercedes"), new Equipe("Red Bull"),
            new Equipe("McLaren"), new Equipe("Alpine"), new Equipe("Aston Martin"),
            new Equipe("Williams")
        };

        Thread[] threads = new Thread[14];
        int index = 0;
        for (Equipe equipe : equipes) {
            threads[index] = new Thread(new Carro("Piloto 1", equipe, treino)); 
            threads[index + 1] = new Thread(new Carro("Piloto 2", equipe, treino)); 
            index += 2;
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        treino.exibirGridLargada();
    }
}