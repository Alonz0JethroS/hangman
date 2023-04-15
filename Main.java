import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
// Link https://www.youtube.com/watch?v=vN6YXUbcw54
public class Main {
    public static void main(String[] args) throws Exception {

        File text = new File("secret_message.txt");
        Scanner textScanner = new Scanner(text);    // We need to throw exception because we're reading a text file
                                                    // and then there could be an error that comes out
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<>();        // words of the dictionary

        //this program puts the dictionary to the array -words-
        while(textScanner.hasNextLine()){           // keep adding the word
            words.add(textScanner.nextLine());
        }

        String hidden_text = words.get((int)(Math.random() * words.size()));
        char[] textArray = hidden_text.toCharArray();

        char[] myAnswers = new char[textArray.length];
        for(int i = 0; i < textArray.length; i++){
            myAnswers[i] = '?';
        }

        boolean finished = false;
        int lives = 6;

        while(finished == false){
            System.out.println("************************");

            String input = scanner.next();
            //checks for valid input
            while(input.length() != 1 || Character.isDigit(input.charAt(0))){
                System.out.println("Error Input - Try Again");
                input = scanner.next();
            }

            //checks if letter is in the word
            boolean found = false;
            for(int i = 0; i < textArray.length; i++){
                if(input.charAt(0) == textArray[i]){
                    myAnswers[i] = textArray[i];
                    found = true;
                }
            }

            if(!found){
                lives--;
                System.out.println("Wrong Letter");
            }
            boolean done = true;
            for(int i = 0; i < myAnswers.length; i++){
                if(myAnswers[i] == '?') {
                    System.out.print(" _");
                    done = false;
                }
                else {
                    System.out.print(" " + myAnswers[i]);
                }
            }
            System.out.println("\n" + "Lives Left: " + lives);

            //check if the game ends
            if(done) {
                System.out.println("Congrats You Found the Word");
                finished = true;
            }

            if(lives <= 0){
                System.out.println("You are dead! Study Your English");
                finished = true;
            }
        }
        System.out.println(hidden_text);
    }
}