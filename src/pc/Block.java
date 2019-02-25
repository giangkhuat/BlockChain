package pc;
import java.security.MessageDigest;
import java.util.Random;
import java.security.NoSuchAlgorithmException;

public class Block {
	int num;
	int amount;
	Hash prevHash;
	long nonce;
	Hash hash;
	
	public Block(int num, int amount, Hash prevHash) {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		// loop repeating generating value of nonce
		// if this nonce can be used to produce a valid Hash, then break out of the loop
		// else keep iterating
		Random nonceNum = new Random();
		this.nonce = nonceNum.nextLong();
	}
	
	public Block(int num, int amount, Hash prevHash, long nonce) {
	}
	
	public int getNum() {
		return this.num;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public long getNonce() {
		return this.nonce;
	}
	
	public Hash getPrevHash() {
		return this.prevHash;
	}
	
	public Hash getHash() {
		return this.hash;
	}
	
	public String toString() {
	}
	public static byte[] calculateHash(String msg) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("sha-256");
		md.update(msg.getBytes());
		byte[] hash = md.digest();
		//this.hash  = hash;
		return hash;
	} // calculateHash(String)
}
