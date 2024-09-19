package controller;

public class Transacao implements Runnable {
    private final Conta conta;
    private final boolean isSaque;
    private final double valor;

    public Transacao(Conta conta, boolean isSaque, double valor) {
        this.conta = conta;
        this.isSaque = isSaque;
        this.valor = valor;
    }

    @Override
    public void run() {
        if (isSaque) {
            conta.sacar(valor);
        } else {
            conta.depositar(valor);
        }
    }
}