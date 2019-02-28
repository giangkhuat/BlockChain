package pc;
public class BlockChain {
	// num field that represents the num of the last block on the chain
	int num;
	// remainingBal that stores the balance remaining
	int remainingBal;
	// initial stores the initial value or total value existing in the economic system
	int initial;
	// Pointers to the first and last elements of the structure
	Node first;
	Node last;

	// Constructor
	public BlockChain(int initial) throws Exception{
		// Doesn't allow the blockchain to initialize with a value smaller than zero
		if (initial < 0){
			throw new Exception("Initial value can't be smaller than zero.");
		}
		this.num = 0;
		// Initialize the first/head block
		Block b = new Block(num, initial, null);
		this.first = new Node(b, null);
		this.last = this.first;
		this.remainingBal = initial;
		this.initial = initial;
	}
	
	// Class and Constructor for a node
	static class Node {
		Block b; 
		Node next;
		public Node(Block newB, Node nextNode) {
			this.b = newB;
			this.next = nextNode;
		}
	}
	
	// mine procedure that creates a valid block
	public Block mine(int amount) throws Exception{
			return new Block(this.num + 1, amount, this.last.b.getHash());
	}
	
	// return the size of the BlockChain
	public int getSize() {
		return this.num;
	}
	
	// appends the block onto the BlockChain
	public void append(Block blk) throws Exception{
		// Checks whether the prevhash of the next block and hash of the block are equal 
		// and checks whether the amounts doesn't exceed the total amount in the economic system
		if (remainingBal < -blk.amount || (remainingBal + blk.amount) > initial) {
			throw new Exception ("No money.");
		}
		this.last.next = new Node (blk, null);
		this.last = this.last.next;
		num++;
		remainingBal += blk.amount;
	}
	
	// removes the last block on the chain
	public Boolean removeLast() {
		// cannot remove block when there is only one block
		if (num <= 0) {
			return false;
		}
		// removes last node
		else {
			Node temp = this.first;
			// decrements num
			num--;
			// iterate through the whole chain to get second to the last element
			while (temp.next.next != null) {
				temp = temp.next;
			}
			this.last = temp;
			// updating balance
			remainingBal -= temp.next.b.amount;
			this.last.next = null;
			return true;
		}
	}
	
	// gets the hash of the last block
	public Hash getHash() {
		return last.b.getHash();
	}
	
	// if nonce is invalid, it allows to append but when being checked
	// the chain is invalid
	// Procedure: isValidBlockChain()
	// Return: valid?, a boolean value
	public boolean isValidBlockChain() {
		Node temp = this.first;
		// iterating through the whole chain until the next Node is null
		while (temp.next != null){
			// if current hash is not equal to the the previous Hash field in the next node
			// of if the current node's hash is invalid
			if (!temp.b.hash.equals(temp.next.b.prevHash) || !temp.next.b.hash.isValid()){
				return false;
			}
			// else keep iterating
			temp = temp.next;
		}
		return true;
	}
	
	// Prints the balance of Alice and Bob
	public void PrintBalances() {
		int bobBalance = this.initial - this.remainingBal;
		System.out.println("Alice: " + this.remainingBal + ", Bob: " + bobBalance);
	}
	
	// Combines the string representation of all the blocks into a long string and return it
	public String toString() {
		String str = "";
		Node temp = this.first;
		while (temp != null){
			str = str + temp.b.toString() +"\n";
			temp = temp.next;
		}
	return str;
	}
}

