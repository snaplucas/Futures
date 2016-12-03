package com.teste.app;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.concurrent.Executors.*;

public class Future_1 {

    public static void main(String args[]) throws InterruptedException, ExecutionException {

        Fatorial task = new Fatorial(20);

        ExecutorService executor = newFixedThreadPool(3);
        Future future = executor.submit(task);

        while (!future.isDone()) {
            Thread.sleep(1);
        }

        long factorial = (long)future.get();
        System.out.println("Fatorial de 20 é: " + factorial);

        executor.shutdown();

    }

    private static class Fatorial implements Callable {
        private final int number;

        Fatorial(int number) {
            this.number = number;
        }

        @Override
        public Long call() {
            long output = 0;
            try {
                output = factorial(number);
            } catch (InterruptedException ex) {
                Logger.getLogger(Future_1.class.getName()).log(Level.SEVERE, null, ex);
            }
            return output;
        }

        private long factorial(int number) throws InterruptedException {
            if (number < 0) {
                throw new IllegalArgumentException("O numero deve ser mairo que zero");
            }
            long result = 1;
            while (number > 0) {
                result *= number;
                number--;
            }
            return result;
        }
    }
}
