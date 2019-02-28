package pc;

import java.security.MessageDigest;
import java.util.Random;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;

public class Block {
	// num field that stores the block number
	int num;
	// amount field that represents the amount of the trasaction
	int amount;
	// preHash field that stores the previous hash
	Hash prevHash;
	// nonce field that contains the nonce
	long nonce;
	// hash field that contains hash
	Hash hash;

	// 1st constructor that takes in num, amount, and prevHash
	// The block will be created even if it is invalid.
    // Produce: block, a Block object
	// Pre-conditions: 
	//   num is a nonnegative integer
	//   amount is a nonnegative integer
	//   prevHash is Hash object and not null
	//   nonce is a long integer
	// Post-conditions:
	//   A block object is created with a Hash value
	//   The hash value must be valid
	public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		// loop repeating generating value of nonce
		// if this nonce can be used to produce a valid Hash, then break out of the loop
		// else keep iterating
		this.nonce = 0;
		// this.nonce = nonceNum.nextLong(5000);
		this.hash = calculateHash(this.num, this.amount, this.prevHash, this.nonce);
		if (this.prevHash != null) {
			// keep incrementing nonce and check if it produces a valid hash.
			while (!this.hash.isValid() && this.hash.hash != this.prevHash.getData()) {
				this.nonce++;
				this.hash = calculateHash(this.num, this.amount, this.prevHash, this.nonce);
			}
		} // when prevHash is null
		else {
			while (!this.hash.isValid()) {
				this.nonce++;
				this.hash = calculateHash(this.num, this.amount, this.prevHash, this.nonce);
			}
		}
	}

	// 2nd constructor that takes in num, amount, prevHash, nonce.
	// The block will be created even if it is invalid.
    // Produce: block, a Block object
	// Pre-conditions: 
	//   num is a nonnegative integer
	//   amount is a nonnegative integer
	//   prevHash is Hash object and not null
	//   nonce is a long integer
	// Post-conditions:
	//   A block object is created with a Hash value 
	//   If nonce is given as an arbitrary value and produces an invalid hash, Block is still appended
	//   but will be notified as invalid block chain when check command is called
	public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;
		this.hash = calculateHash(this.num, this.amount, this.prevHash, this.nonce);
	}

	// returns num
	public int getNum() {
		return this.num;
	}

	// returns amount
	public int getAmount() {
		return this.amount;
	}

	// returns nonce
	public long getNonce() {
		return this.nonce;
	}

	// returns prevHash
	public Hash getPrevHash() {
		return this.prevHash;
	}

	// returns hash
	public Hash getHash() {
		return this.hash;
	}

	// returns a string representing the information in following format:
	// Block <num> (Amount: <amt>, Nonce: <nonce>, prevHash: <prevHash>, hash:
	// <hash>
	public String toString() {
		String blockStr = "Block " + this.num + " (Amount: " + this.amount + ", Nonce: " + Long.toString(this.nonce);
		// if previous Hash is not null
		if (this.prevHash != null) {
			blockStr += ", prevHash: " + this.prevHash.toString();
		}
		// else print out null value for previous Hash
		else {
			blockStr += ", prevHash: " + "null";
		}
		blockStr += ", hash: " + this.hash.toString() + ")";
		return blockStr;
	}

	// Calculates hash and returns the hash found
	public static Hash calculateHash(int num, int amount, Hash prev, long nonce) throws NoSuchAlgorithmException {
		// create the MessageDigest object with algorithm sha-256
		MessageDigest md = MessageDigest.getInstance("sha-256");
		// initialize and allocate s;aces for bytebuffer
		ByteBuffer B = ByteBuffer.allocate(16);
		// put data in the Bytebuffer
		B.putInt(num);
		B.putInt(amount);
		B.putLong(nonce);
		// initialize your byteArray
		byte[] byteArray = B.array();
		// if previous Hash is null
		if (prev != null) {
			// put data in the MessageDigest
			md.update(prev.getData());
		}
		md.update(byteArray);
		// creating a hash as an array of bytes
		byte[] hash = md.digest();
		return new Hash(hash);
	}
}
