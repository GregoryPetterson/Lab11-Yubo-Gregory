import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void main(String[] args) {
        System.out.println("how many alphabet:");
		Map<Character, Float> characterMap = new HashMap<>();
		for(int i=0; i<5; i++){
			Scanner in = new Scanner(System.in);
			String inLine = in.nextLine();
			System.out.println(inLine);
			char c = inLine.charAt(0);
			float f = getNumber(inLine);
			characterMap.put(c, f);
		}

    
        PriorityQueue<HFNode> pQueue = new PriorityQueue<>();
        Set<Character> key = characterMap.keySet();
        for (char c : key) {
            HFNode hfNode = new HFNode();
            hfNode.setCharacter(c);
            hfNode.setFrequence(characterMap.get(c));
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
		for(Map.Entry<Character,Float> e:characterMap.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }
		for(Map.Entry<Character,String> e:dict.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }
    }
}
