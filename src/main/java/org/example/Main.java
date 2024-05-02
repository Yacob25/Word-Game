package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int WORD_LENGTH = 5;
    private static final int MAX_GUESS = 6;

    private static final int NO_MATCH = 0;
    private static final int EXACT_MATCH = 1;
    private static final int WRONG_LOCATION = 2;



    private static final String [] words = {" chair", "crate", "train", "allow", "study"};

    public static final String COLOR_RESET =  "\u001b[0m";
    public static final String BLACK =  "\u001b[30m";

    public static final String GREEN =  "\u001b[42m";
    public static final String YELLOW =  "\u001b[43m";
    public static final String GRAY =  "\u001b[47m";




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

            int [] results  = checkGuess(guess);
             printGuessResults(guess, results);

           ++guessCount;

            win = guess.equals(word);
        }
        if (win){
            System.out.println("You won!");
        }else {
            System.out.println( "Sorry you didn't win. The word is "+ word + ". ");
        }
    }

    private  int [] checkGuess(String guess){

        String missedWordChars = "";
        for (int i = 0; i<WORD_LENGTH; i++){
            char wordChar = word.charAt(i);
            char guessChar = guess.charAt(i);
            if (wordChar != guessChar){
                missedWordChars += wordChar;
            }
        }


        int [] resultsCodes = new int[WORD_LENGTH];
        for (int i = 0; i<WORD_LENGTH; i++){
            char wordChar = word.charAt(i);
            char guessChar = guess.charAt(i);
            if (wordChar == guessChar){
                resultsCodes[i] = EXACT_MATCH;
            } else{
                if (missedWordChars.contains(Character.toString(guessChar))){
                    resultsCodes [i] = WRONG_LOCATION;
                    missedWordChars= missedWordChars.replace(Character.toString(guessChar), "");
                } else{
                    resultsCodes[i] = NO_MATCH;
                }
            }

        }

        return resultsCodes;

    }

    private void printGuessResults  (String guess, int [] results){
        for (int i = 0; i<WORD_LENGTH; i++){
            char ch = guess.charAt(i);
            int resultCode =results[i];
            String background = GRAY;
            if (resultCode == EXACT_MATCH){
                background =  GREEN;
            }else if (resultCode == WRONG_LOCATION) {
                background = YELLOW;
            }
            printGuessCHar(ch,background );

        }
    }

    private void printGuessCHar(char guessChar, String background) {
        String upperCaseGuessChar = Character.toString(guessChar).toUpperCase();

        String formattedGuessCH =
                String.format("%s%s %s %s", background, BLACK, upperCaseGuessChar, COLOR_RESET);
        System.out.println( formattedGuessCH);



    }


}