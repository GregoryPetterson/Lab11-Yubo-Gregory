class HFNode implements Comparable<HFNode> {
	char character;
	float frequence;
	HFNode left;
	HFNode right;
	String code;
 
	public char getCharacter() {
		return character;
	}
 
	public void setCharacter(char character) {
		this.character = character;
	}
 
	public float getFrequence() {
		return frequence;
	}
 
	public void setFrequence(float frequence) {
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
		} else if (this.frequence > o.frequence) {
			return 1;
		} else{
			return -1;
		}
	}
}