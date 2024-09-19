package view;

import controller.Pessoa;

public class Main {
    public static void main(String[] args) {
        Thread pessoa1 = new Thread(new Pessoa("Pessoa 1"));
        Thread pessoa2 = new Thread(new Pessoa("Pessoa 2"));
        Thread pessoa3 = new Thread(new Pessoa("Pessoa 3"));
        Thread pessoa4 = new Thread(new Pessoa("Pessoa 4"));

        pessoa1.start();
        pessoa2.start();
        pessoa3.start();
        pessoa4.start();
    }
}