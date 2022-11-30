import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Task4 {

    

    public static void main(String[] args) throws FileNotFoundException{
        if ((args.length != 3)) {
			System.out.println("Please input the name of a file containing Huffman encodings, " +
            "e (for encoding) or d (for decoding), and then the name of a text file.");
			System.exit(0);
		}

        String huffmanEncoding = args[0];
		String  eORd = args[1];
        String inputFileName = args[2];

        // Encoding or decoding?
        // Take the huffmanencoding file and turn it into hashmap so we can
        // look up the code of a character or vice versa.
        if(eORd.equals("e")){

        Map<Character, String> characterMap = new HashMap<>();
		Scanner in = new Scanner(new File(huffmanEncoding));

        File inputfile = new File(inputFileName);

		while (in.hasNext()) {
			String inLine = in.nextLine();
			char key = inLine.charAt(0);
			String code = inLine.substring(2);
			characterMap.put(key, code);
		}
		in.close();

            encode(characterMap, inputfile);
        } else if( eORd.equals("d")){

            Map<String, Character> codeMap = new HashMap<>();
            Scanner in = new Scanner(new File(huffmanEncoding));
    
            File inputFile = new File(inputFileName);
    
            while (in.hasNext()) {
                String inLine = in.nextLine();
                char letter = inLine.charAt(0);
                String keyCode = inLine.substring(2);
                codeMap.put(keyCode, letter);
            }
            in.close();
            decode(codeMap, inputFile);
        }    
    }

    public static void encode(Map<Character, String> characterMap, File inputFile) throws FileNotFoundException{
        Scanner in = new Scanner(inputFile);
        String fileString = "";
        

        while (in.hasNext()) {
			String inLine = in.nextLine();
            fileString = fileString + inLine;
		}
        in.close();

        fileString = fileString.toLowerCase();

        char[] fileCharArray = fileString.toCharArray();
        int charArrayLength = fileCharArray.length;

        PrintWriter outputFile = new PrintWriter(inputFile);

        for(int i=0; i<charArrayLength; i++){
        String value = String.valueOf(characterMap.get(fileCharArray[i]));
            
        if (value != "null") {
            outputFile.print(value);
            
            } else {
                outputFile.print("");
            }
        }
        outputFile.close();
    }

    public static void decode(Map<String, Character> codeMap, File inputFile) throws FileNotFoundException{
        Scanner in = new Scanner(inputFile);
        String fileString = "";

        while (in.hasNext()) {
			String inLine = in.nextLine();
            fileString = fileString + inLine;
            
		}
        in.close();

        //int replacedCount = 0;
        Set<String> keyCodes = codeMap.keySet();

        PrintWriter outputFile = new PrintWriter(inputFile);

        Integer keyCodeLength = 0;

        for(int replacedCount=0; replacedCount<=fileString.length();){     

            for (String keyCode : keyCodes) {
                Integer substringIndex = fileString.indexOf(keyCode, replacedCount);
                //System.out.println(substringIndex.toString());
                //System.out.println(replacedCount.toString());
                if(substringIndex.equals(replacedCount)){
                    char decodedCharacter = codeMap.get(keyCode);
                    outputFile.print(decodedCharacter);
                    keyCodeLength = keyCode.length();
                } 
            } 
            replacedCount = replacedCount + keyCodeLength;  
        }
        outputFile.close();
    }
}
