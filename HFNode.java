import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

class HFNode implements Comparable<HFNode> {
	char character;
	int frequence;
	private HFNode left;
	private HFNode right;
 
	private String code;
 
	public char getCharacter() {
		return character;
	}
 
	public void setCharacter(char character) {
		this.character = character;
	}
 
	public int getFrequence() {
		return frequence;
	}
 
	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}
 
	public HFNode getLeft() {
		return left;
	}
 
	public void setLeft(HFNode left) {
		this.left = left;
	}
 
	public HFNode getRight() {
		return right;
	}
 
	public void setRight(HFNode right) {
		this.right = right;
	}
 
	public String getCode() {
		return code;
	}
 
	public void setCode(String code) {
		this.code = code;
	}
 
	@Override
	public String toString() {
		return this.character + " " + this.frequence + " ";
 
	}
 
	@Override
	public int compareTo(HFNode o) {
		if (this.frequence == o.frequence) {
			return this.getCharacter() - o.getCharacter();
		}
		return this.frequence - o.frequence;
	}


    public static void main(String[] args) {
        System.out.println("please input string:");
        Scanner in = new Scanner(System.in);
        String inLine = in.next();
        in.close();
        char[] chars = inLine.toCharArray();
        Map<Character, Integer> characterMap = new HashMap<>();
    
        for (int i = 0; i < chars.length; i++) {
            int count = characterMap.containsKey(chars[i]) ? characterMap.get(chars[i]) : 0;
            characterMap.put(chars[i], count + 1);
        }
    
        PriorityQueue<HFNode> pQueue = new PriorityQueue<>();
        Set<Character> key = characterMap.keySet();
        for (char c : key) {
            HFNode hfNode = new HFNode();
            hfNode.setCharacter(c);
            hfNode.setFrequence(characterMap.get(c));
            pQueue.add(hfNode);
        }
        
        // Building Huffman Tree
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
        StringBuilder string = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			string.append(dict.get(chars[i]));
		}
		System.out.println(string);
    }
}