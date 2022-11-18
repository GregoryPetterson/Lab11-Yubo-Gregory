import java.io.FileNotFoundException;
import java.util.Scanner;
public class Huffman {
    public static void main(String[] args){
        Scanner inputfilenamereader = new Scanner(System.in);
        System.out.println("Enter the file name of the alphabet and frequencies: ");
        String inputfilename = inputfilenamereader.next();
        inputfilenamereader.close();
     

        Scanner outputfilenamereader = new Scanner(System.in);
        System.out.println("enter the output file name: ");
        String outputfilename = outputfilenamereader.next();
        outputfilenamereader.close();
        
    }

    private static String[] readData(String inputfilename){

        
    }
    
}
