import java.util.*;

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

        String huffmanencoding = args[0];
		String  eORd = args[1];
        String inputfilename = args[2];

        // Encoding or decoding?
        // Take the huffmanencoding file and turn it into hashmap so we can
        // look up the code of a character or vice versa.
        if(eORd.equals("e")){

        Map<Character, String> characterMap = new HashMap<>();
		Scanner in = new Scanner(new File(huffmanencoding));

        File inputfile = new File(inputfilename);

		while (in.hasNext()) {
			String inLine = in.nextLine();
			char key = inLine.charAt(0);
			String code = inLine.substring(2);
			characterMap.put(key, code);
		}
		in.close();

            encode(characterMap, inputfile);
        } else if( eORd.equals("d")){

            Map<Character, String> codeMap = new HashMap<>();
            Scanner in = new Scanner(new File(huffmanencoding));
    
            File inputfile = new File(inputfilename);
    
            while (in.hasNext()) {
                String inLine = in.nextLine();
                char key = inLine.charAt(0);
                String code = inLine.substring(2);
                codeMap.put(key, code);
            }
            in.close();
            
            decode(codeMap, inputfile);
        }    
    }

    public static void encode(Map<Character, String> huffmanmap, File inputfile) throws FileNotFoundException{
        Scanner in = new Scanner(inputfile);
        String filestring = "";
        

        while (in.hasNext()) {
			String inline = in.nextLine();
            filestring = filestring + inline;
		}
        in.close();

        filestring = filestring.toLowerCase();

        char[] filechararray = filestring.toCharArray();
        int chararraylength = filechararray.length;

        PrintWriter outputfile = new PrintWriter(inputfile);

        for(int i=0; i<chararraylength; i++){
        String value = String.valueOf(huffmanmap.get(filechararray[i]));
            
        if (value != "null") {
            outputfile.print(value);
            
            } else {
                outputfile.print("");
            }
        }
        outputfile.close();
    }

    public static void decode(Map<Character, String> huffmanmap, File inputfilename){
        
    }

   
}
