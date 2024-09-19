package controller;

import java.util.concurrent.Semaphore;

public class Equipe {
    private final String nome;
    private final Semaphore semaforoEquipe;

    public Equipe(String nome) {
        this.nome = nome;
        this.semaforoEquipe = new Semaphore(1); 
    }

    public String getNome() {
        return nome;
    }

    public void entrar() throws InterruptedException {
        semaforoEquipe.acquire();
    }

    public void sair() {
        semaforoEquipe.release();
    }
}