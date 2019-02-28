package pc;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BlockChainDriver{
	public static void main(String[] args) throws Exception{
		PrintWriter pen = new PrintWriter(System.out, true);
		BufferedReader eyes = new BufferedReader (new InputStreamReader(System.in));
		String command;
		// Printing out information for audience
		helpInfo();
		int iniAmount = Integer.parseInt(args[0]);
		BlockChain BC = new BlockChain (iniAmount);
		//printing out the valid commands	
		do {
			pen.println(BC.toString());
			pen.print("Command?   ");
			pen.flush();
			command = eyes.readLine();
			if (command.equals("mine")){
				pen.print("Amount Transfered?  ");
				pen.flush();
				int amount = Integer.parseInt(eyes.readLine());
				Block Bl = BC.mine(amount);
				pen.println("amount = " + Bl.getAmount() + ", nonce = " + Bl.getNonce());
			}
			else if (command.equals("append")) {
				pen.print("Amount Transfered?   ");
				pen.flush();
				int amount = Integer.parseInt(eyes.readLine());
				pen.print("Nonce?   ");
				pen.flush();
				long nonce = Long.parseLong(eyes.readLine());
				BC.append(new Block (BC.num + 1, amount, BC.last.b.getHash(), nonce));
			}
			else if (command.equals("remove")) {
				BC.removeLast();
			}
			else if (command.equals("check")) {
				if (BC.isValidBlockChain()) {
					pen.println("The Chain is Valid!");
				}
				else {
					pen.println("The Chain is Invalid.");
				}
			}
			else if (command.equals("report")) {
				BC.PrintBalances();
			}
			else if (command.equals("help")) {
				helpInfo();
			}
			else if (command.equals("quit")) {
				pen.println("Quitting Program. Adios");
			}
			else {
				pen.println("Invalid Command.");
			}
			pen.print("\n");
		} while (!command.equals("quit"));
	}
	
	public static void helpInfo() {
		System.out.println("Valid commands:\n" + 
				"    mine: discovers the nonce for a given transaction\n" + 
				"    append: appends a new block onto the end of the chain\n" + 
				"    remove: removes the last block from the end of the chain\n" + 
				"    check: checks that the block chain is valid\n" + 
				"    report: reports the balances of Alice and Bob\n" + 
				"    help: prints this list of commands\n" + 
				"    quit: quits the program \n");
	}
}
