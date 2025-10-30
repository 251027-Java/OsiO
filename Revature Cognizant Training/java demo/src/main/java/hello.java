import java.util.Scanner;

public class hello {

//    public static void main(String[] args){
//
//    }

    public static void main(string_example[] args) {
        // we have to begin with a hello world, it's a necessity not a choice
        System.out.println("Hello World");
        IO.println("Hello Again");

        // numerical types
        int myInt = 34; //integer values
        double myDouble; //decimals
        myDouble = 34.5;

        myInt = 35;

        //operators (mathematics) : +, -, *, /, %
        myInt = 34 - 35;


        // logical/comparison operators: >, <, ==, >=, <=, !=
        boolean myBool = myDouble > myInt;

        //boolean can store true, false
        myBool = true;
        myBool = false;


        /*
        float; //floating point decimals
        long; //BIG integer values
        short; //small integer values
        byte; //REALLY small integer values

        //non numerical types
        String; //an array of characters (words, sentences, etc)
        char; //characters (letters, numbers, punctuation, etc.)
        void; //return type; denotes a nothing
        boolean; //ture or false values
        null; //not a zero, absence of value

        //control flow covers all of the keywords and functionality that allow an explanation
        // to make a decision and act on it without us providing additional input

        // if, else if, else
        // switch, case
        // for, while, do-while
        // "exception handling" try, catch
         */

        // pseudocode: describe the logic of your plan, dont worry about exact syntax
        //////psuedocode Example/////
        // initialize a boolean
        myBool = false;
        // make a choice based on value
        // if true, do a
        if (myBool == true) {
            IO.println("myBool was true");
        } else {
            // if false, do b
            IO.println("myBool was false");
        }


        //user inputs grade
        Scanner scanner = new Scanner(System.in);
        IO.println("Enter your numerical grade value: ");
        double myGrade = -1;
        boolean invalidInput = true;

        while (invalidInput) {
            try {
                IO.println("please enter a score between 0 and 100: ");
                myGrade = scanner.nextDouble();
                if ((myGrade <= 100) && (myGrade >= 0)){
                    invalidInput = false;
                } else{
                    IO.println("value was out of range, try again plz: ");
                }
            } catch (Exception e) {
                IO.println("Invalid entry try again");
                scanner.next();
            }
        }
        scanner.close();
        IO.println(myGrade);

        if (myGrade >= 90) {
            IO.println("A");
        } else if (myGrade >= 80) {
            IO.println("B");
        } else if (myGrade >= 70) {
            IO.println("C");
        } else if (myGrade >= 60) {
            IO.println("D");
        } else if (myGrade <= 60) {
            IO.println("F");
        }
    }
}

