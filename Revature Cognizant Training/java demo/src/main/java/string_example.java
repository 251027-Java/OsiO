
public class string_example {
    public static void main(string_example[] args) {

        String newString;
        newString = "Hello Everyone";
        IO.println(newString);
        // for (iterator, expression, increment)
        for (int i = 0; i < newString.length(); i++){
            IO.println(newString.charAt(i));
        }
    }
}

