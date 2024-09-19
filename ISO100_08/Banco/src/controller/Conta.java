package controller;

import java.util.concurrent.Semaphore;

public class Conta {
    private final String codigoConta;
    private double saldo;
    private static final Semaphore saqueSemaforo = new Semaphore(1);
    private static final Semaphore depositoSemaforo = new Semaphore(1);

    public Conta(String codigoConta, double saldoInicial) {
        this.codigoConta = codigoConta;
        this.saldo = saldoInicial;
    }

    public void sacar(double valor) {
        try {
            saqueSemaforo.acquire();
            if (saldo >= valor) {
                System.out.println("Conta " + codigoConta + ": Saque de R$" + valor + " realizado.");
                saldo -= valor;
            } else {
                System.out.println("Conta " + codigoConta + ": Saldo insuficiente para saque de R$" + valor + ".");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            saqueSemaforo.release(); 
        }
    }

    public void depositar(double valor) {
        try {
            depositoSemaforo.acquire();
            System.out.println("Conta " + codigoConta + ": Depósito de R$" + valor + " realizado.");
            saldo += valor;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public double getSaldo() {
        return saldo;
    }
}