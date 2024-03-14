package efs.task.syntax;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class GuessNumberGame {
    private final int numberToGuess;
    private final int M;
    private final int L;
    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) throws IllegalArgumentException {
        //TODO: Implement the constructor
        try {
            this.M = Integer.parseInt(argument);
        } catch (IllegalArgumentException e) {
            System.out.println(argument +" to " + UsefulConstants.WRONG_ARGUMENT + " - to nie liczba");
            throw new IllegalArgumentException("Invalid argument", e);
        }
        if(M<1 || M>UsefulConstants.MAX_UPPER_BOUND){
            System.out.println(argument + " to " + UsefulConstants.WRONG_ARGUMENT+  " - jest spoza zakresu <1,400>");
            throw new IllegalArgumentException();
        }

        this.numberToGuess=new Random().nextInt(M)+1;
        this.L=(int)(Math.abs(Math.floor(Math.log(M)/Math.log(2)))+1);
    }
    public void play() {
        //TODO: Implement the method that executes the game session
        Scanner scanner = new Scanner(System.in);

        int countOfAttempt = 0;
        int userInt;

        String guess;
        String[] tab = new String[L];
        Arrays.fill(tab, ".");

        System.out.println("Zagrajmy. Zgadnij liczbe z zakresu <1," + M + ">");

        while (true) {
            if (countOfAttempt == L) {
                System.out.println(UsefulConstants.UNFORTUNATELY + ", wyczerpałeś limit prób (" + L+")");
                break;
            }
            tab[countOfAttempt] = "*";
            printAttempts(tab);
            System.out.println(UsefulConstants.GIVE_ME + " liczbę :");
            guess = scanner.next();

            try {
                userInt = Integer.parseInt(guess);
            } catch (NumberFormatException e) {
                System.out.println("Hmm, "+ guess + " to "+UsefulConstants.NOT_A_NUMBER);
                scanner.nextLine();
                countOfAttempt++;
                continue;
            }

            if (userInt > numberToGuess) {
                System.out.println("To" + UsefulConstants.TO_MUCH);
            } else if (userInt < numberToGuess) {
                System.out.println("To" + UsefulConstants.TO_LESS);
            } else {
                System.out.println(UsefulConstants.YES + "!");
                System.out.println(UsefulConstants.CONGRATULATIONS + ", " + (countOfAttempt + 1) + " - tyle prób zajęło Ci odgadnięcie liczby"+ numberToGuess);
                break;
            }
            countOfAttempt++;
        }
    }
    private void printAttempts(String[] arr) {
        System.out.print("Twoje próby: [");
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k]);
        }
        System.out.println("] ");
    }

}




