package view;

import controller.Conta;
import controller.Transacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Conta conta = new Conta("12345-6", 1000.0);

        List<Thread> threads = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            boolean isSaque = random.nextBoolean();
            double valor = 50 + random.nextInt(251);

            Transacao transacao = new Transacao(conta, isSaque, valor);
            Thread thread = new Thread(transacao);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Saldo final da conta: R$" + conta.getSaldo());
    }
}