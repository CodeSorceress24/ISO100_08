package controller;

import java.util.concurrent.locks.ReentrantLock;

public class Carro implements Runnable {

    private static String sentidoAtual = null;
    private static final ReentrantLock lock = new ReentrantLock();

    private final String sentido;

    public Carro(String sentido) {
        this.sentido = sentido;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (sentidoAtual == null || sentidoAtual.equals(sentido)) {
                    sentidoAtual = sentido;
                    System.out.println("Carro " + Thread.currentThread().getId() + " passando no sentido " + sentido);
                    Thread.sleep(1000); 
                    sentidoAtual = null;
                    break; 
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); 
            }
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}