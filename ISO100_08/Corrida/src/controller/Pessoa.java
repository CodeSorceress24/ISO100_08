package controller;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Pessoa implements Runnable {
    private static final ReentrantLock porta = new ReentrantLock();
    private final String nome;
    private final int velocidade; 
    private final int tempoAbrirPorta; 
    private static final int distanciaCorredor = 200;
    private final Random random = new Random();

    public Pessoa(String nome) {
        this.nome = nome;
        this.velocidade = 4 + random.nextInt(3); 
        this.tempoAbrirPorta = 1 + random.nextInt(2); 
    }

    @Override
    public void run() {
        try {
            int tempoParaCaminhar = distanciaCorredor / velocidade;
            System.out.println(nome + " está caminhando a " + velocidade + " m/s e levará " + tempoParaCaminhar + " segundos para chegar à porta.");
            Thread.sleep(tempoParaCaminhar * 1000L); 

            porta.lock();
            try {
                System.out.println(nome + " está abrindo e cruzando a porta...");
                Thread.sleep(tempoAbrirPorta * 1000L);
                System.out.println(nome + " cruzou a porta.");
            } finally {
                porta.unlock(); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}