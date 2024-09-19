package controller;

import java.util.Random;

public class Carro implements Runnable {
    private final String nomePiloto;
    private final Equipe equipe; 
    private final Treino treino;
    private double melhorVolta = Double.MAX_VALUE;

    public Carro(String nomePiloto, Equipe equipe, Treino treino) {
        this.nomePiloto = nomePiloto;
        this.equipe = equipe;
        this.treino = treino;
    }

    public String getNomePiloto() {
        return nomePiloto;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public double getMelhorVolta() {
        return melhorVolta;
    }

    @Override
    public void run() {
        try {
            treino.entrarNaPista(this);
            Random random = new Random();

            for (int volta = 1; volta <= 3; volta++) {
                double tempoVolta = 60 + (30 * random.nextDouble());
                System.out.printf("%s (%s) completou a volta %d com tempo: %.2f segundos\n", 
                                  nomePiloto, equipe.getNome(), volta, tempoVolta);
                
                if (tempoVolta < melhorVolta) {
                    melhorVolta = tempoVolta;
                }

                Thread.sleep((long) tempoVolta * 10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            treino.sairDaPista(this);
        }
    }
}