package pc;

public class BlockChainDriver{
	public static void main(String[] args) throws Exception{
	System.out.println("Giang1");
	BlockChain Bl = new BlockChain(500);
	System.out.println("Giang2");
	Block newBl = Bl.mine(-150);
	System.out.println("Giang3");
	Bl.append(newBl);
	System.out.println("Giang4");
	System.out.println(" giang " + 6 + "hello" + "\n" + (long) 7);
	System.out.println(Bl.toString());
	System.out.println("Giang5");
	}
}
