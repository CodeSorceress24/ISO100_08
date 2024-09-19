package view;

import controller.Carro;

public class Main {
    public static void main(String[] args) {
        String[] sentidos = {"Norte", "Leste", "Sul", "Oeste"};

        for (String sentido : sentidos) {
            Thread thread = new Thread(new Carro(sentido));
            thread.start();
        }
    }
}