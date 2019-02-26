package pc;

public class BlockChain {
	Block current;
	Block[] Chain;
	int currentpos = 0;
	int remainingBal;
	public BlockChain(int initial) throws Exception{
		Chain[currentpos] = new Block(0, initial, null);
	}
	
	public Block mine(int amount) {
		if (remainingBal > amount) {
			
		}
	}
	
	public int getSize() {
		
	}
	
	public void append(Block blk) {
	}
	
	public Boolean removeLast() {
		
	}
	
	public Hash getHash() {
		
	}
	
	public boolean isValidBlockChain() {
		
	}
	
	public void PrintBalances() {
		
	}
	
	public String toString() {
		
	}
}
