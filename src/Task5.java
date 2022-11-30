import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;

public class Task5 {
    public static void main(String[] args) throws FileNotFoundException{
        if ((args.length != 2)) {
			System.out.println("Please input the name of a file containing an alphabet with frequencies and a character to get the average encoded letter length.");
			System.exit(0);
		}

        String alphabet = args[0];
        String character = args[1];

        Map<String, String> characterMap = new HashMap<>();
		Scanner in = new Scanner(new File(alphabet));


		while (in.hasNext()) {
			String inLine = in.nextLine();
			String key = inLine.substring(0, 1);
			String frequencies = inLine.substring(2);
			characterMap.put(key, frequencies);
		}
		in.close();

        avgLetterLength(characterMap, character);

    }

    public static void avgLetterLength(Map<String, String> characterMap, String character) {
        int encodedLength = characterMap.get(character).length();
        Double frequency = Double.valueOf(characterMap.get(character));

        double avgEncodedLetterLength = encodedLength / frequency;

        System.out.print(String.valueOf(avgEncodedLetterLength));
        
    }
}
