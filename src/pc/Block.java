package pc;

import java.security.MessageDigest;
import java.util.Random;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;

public class Block {
	int num;
	int amount;
	Hash prevHash;
	long nonce;
	Hash hash;

	public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		// loop repeating generating value of nonce
		// if this nonce can be used to produce a valid Hash, then break out of the loop
		// else keep iterating
		Random nonceNum = new Random();
		this.nonce = nonceNum.nextLong();
		this.hash.hash = calculateHash(this.num, this.amount, this.prevHash, this.nonce);
		while (!this.hash.isValid() && this.hash.hash != this.prevHash.getData()) {
			this.hash.hash = calculateHash(this.num, this.amount, this.prevHash, this.nonce);
			// this nonce++ ?
		}
	}

	public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		// loop repeating generating value of nonce
		// if this nonce can be used to produce a valid Hash, then break out of the loop
		// else keep iterating
		this.nonce = nonce;
		this.hash.hash = calculateHash(this.num, this.amount, this.prevHash, this.nonce);
		while (!this.hash.isValid() && this.hash.hash != this.prevHash.getData()) {
			this.hash.hash = calculateHash(this.num, this.amount, this.prevHash, this.nonce);
		}
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
		String str = "";
		str = str + "Block " + this.num + " (Amount: " + this.amount + ", Nonce: " + this.nonce + ", prevHash: " + this.prevHash + ", hash:" + this.hash;
		return str;
	}

	public static byte[] calculateHash(int num, int amount, Hash prev, long nonce) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("sha-256");
		ByteBuffer B = ByteBuffer.allocate(16);
		B.putInt(num);
		B.putInt(amount);
		B.putLong(nonce);
		byte[] byteArray = B.array();
		if (prev != null) {
			md.update(prev.getData());
		}
		md.update(byteArray);
		byte[] hash = md.digest();
		return hash;
	} // calculateHash(String)
}
