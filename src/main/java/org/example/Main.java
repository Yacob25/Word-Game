package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int WORD_LENGTH = 5;
    private static final int MAX_GUESS = 6;


    private static final String [] words = {" chair", "crate", "train", "allow", "study"};

    private String word;

    public static void main(String[] args) {
        Main wordGame = new Main();
        wordGame.run();

    }

    public Main() {
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        word = words[randomIndex];

    }

    public void run (){
        System.out.println("Welcome to Word!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("My word is " + word);

        int guessCount = 1;
        boolean win = false;

        while (guessCount< MAX_GUESS && !win){

            System.out.print("Please enter guess #" + guessCount+ ": ");
            String guess = scanner.nextLine();
            guess = guess.toLowerCase();



            ++guessCount;

            win = guess.equals(word);
        }
        if (win){
            System.out.println("You won!");
        }else {
            System.out.println( "Sorry you didn't win. The word is "+ word + ". ");
        }
    }
}