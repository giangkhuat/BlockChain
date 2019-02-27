package pc;

public class BlockChain {
	//Block current
	int num;
	int remainingBal;
	int initial;
	// pointers to the first and last elements of the structure
	Node first;
	Node last;


	public BlockChain(int initial) throws Exception{
		this.num = 1;
		Block b = new Block(0, initial, null);
		this.first = new Node(b, null);
		this.last = this.first;
		this.remainingBal = initial;
		this.initial = initial;
	}
	
	static class Node {
		Block b; 
		Node next;
		public Node(Block newB, Node nextNode) {
			this.b = newB;
			this.next = nextNode;
		}
	}
	
	
	public Block mine(int amount) throws Exception{
			return new Block(this.num + 1, amount, this.last.b.getHash());
	}
	
	public int getSize() {
		return this.num;
	}
	
	public void append(Block blk) throws Exception{
		if (remainingBal < blk.amount) {
			throw new Exception ("No money.");
		}
		this.last.next = new Node (blk, null);
		this.last = this.last.next;
		num++;
		remainingBal += blk.amount;
	}
	
	public Boolean removeLast() {
		if (num <= 1) {
			return false;
		}
		else {
			Node temp = this.first;
			this.last = null;
			num--;
			for (int i = 0; i < num; i++){
				temp = temp.next;
			}
			this.last = temp;
			return true;
		}
	}
	
	public Hash getHash() {
		return last.b.getHash();
	}
	
	public boolean isValidBlockChain() {
		Node temp = this.first;
		while (temp.next != null){
			if (temp.b.getHash() != temp.next.b.getPrevHash()){
				return false;
			}
			temp = temp.next;
		}
		return true;
	}
	
	public void PrintBalances() {
		int bobBalance = this.initial - this.remainingBal;
		System.out.println("Alice: " + this.remainingBal + ", Bob: " + bobBalance);
	}
	
	public String toString() {
		String str = "";
		Node temp = this.first;
		while (temp.next != null){
			str = str + temp.b.toString() +"\n";
			temp = temp.next;
		}
	return str;
	}
}
