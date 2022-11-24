import java.util.Scanner;

public class test {
    public static void main(String args[]){  
        Scanner in = new Scanner(System.in);
		String inLine = in.next();
        for(int i=0; i<inLine.length();i++){  
                char c = inLine.charAt(i);  
                System.out.println("char at "+i+" index is: "+c);  
        }   
    }
}
