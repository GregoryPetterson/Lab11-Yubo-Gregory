import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Output is not in short-lex
public class Task2{

	public static float getNumber(String str){
		String regex = "[0-9]+(\\.[0-9]+)?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return Float.parseFloat(m.group());
        } else {
			return 0;
		}
	}

    public static void main(String[] args) throws FileNotFoundException{

		if (args.length < 2) {
			System.out.println("Please input the name of the file containing the alphabet frequencies and the output file name.");
			System.exit(0);
		}

		String inputfilename = args[0];
		String outputfilename = args[1];

		Map<Character, Float> characterMap = new HashMap<>();

		Scanner in = new Scanner(new File(inputfilename)) ;

		while (in.hasNext()) {
			String inLine = in.nextLine();
			char c = inLine.charAt(0);
			float f = getNumber(inLine);
			characterMap.put(c, f);
		}

		in.close();

    
        PriorityQueue<HFNode> pQueue = new PriorityQueue<>();
        Set<Character> keys = characterMap.keySet();
        for (char key : keys) {
            HFNode hfNode = new HFNode();
            hfNode.setCharacter(key);
            hfNode.setFrequence(characterMap.get(key));
            pQueue.add(hfNode);
        }
        
        // Building Huffman Tree(Doesn't need to make change)
        while (pQueue.size() > 1) {
			HFNode newNode = new HFNode();
			HFNode leftNode = pQueue.remove();
			HFNode rightNode = pQueue.remove();
			newNode.setFrequence(rightNode.getFrequence() + leftNode.getFrequence());
			newNode.setLeft(leftNode);
			newNode.setRight(rightNode);
			newNode.setCode("");
			newNode.setCharacter(newNode.getLeft().getCharacter());
			pQueue.add(newNode);
		}
 
		HFNode finalTree = pQueue.remove();
		Queue<HFNode> queue = new ArrayDeque<>();
		if (finalTree != null) {
			queue.add(finalTree);
			finalTree.getLeft().setCode("0");
			finalTree.getRight().setCode("1");
		}

        Map<Character, String> dict = new HashMap<>();
 
		while (!queue.isEmpty()) {
			HFNode node = queue.remove();
			if (node.getLeft() != null)
				node.getLeft().setCode(node.getCode() + "0");
			if (node.getRight() != null)
				node.getRight().setCode(node.getCode() + "1");
 
			if (node.getLeft() != null) {
				queue.add(node.getLeft());
			}
 
			if (node.getRight() != null) {
				queue.add(node.getRight());
			}
 
			if (node.getLeft() == null && node.getRight() == null) {
				dict.put(node.character, node.code);
			}
		}

        //Output
		PrintWriter out = new PrintWriter(outputfilename);
		for(Map.Entry<Character,String> e:dict.entrySet()){
            out.println(e.getKey()+" "+e.getValue());
        }
		out.close();

		
    }
}
