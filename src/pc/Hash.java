package pc;

public class Hash {
	// hash field is an array of bytes
	protected byte[] hash;

	// Constructor
	public Hash(byte[] data) {
		this.hash = data;
	}

	// get the hash
	public byte[] getData() {
		return this.hash;
	}

	// isValid check if the hash's first three bytes are all 0
	public boolean isValid() {
		return (this.hash[0] == 0 && this.hash[1] == 0 && this.hash[2] == 0);
	}

	// method to return the string representation of the Hash
	public String toString() {
		String hashStr = "";
		// a loop that goes through the length of the hash
		for (int i = 0; i < this.hash.length; i++) {
			hashStr += String.format("%x", Byte.toUnsignedInt(this.hash[i]));
		}
		return hashStr;
	}

	// Checks whether the two hashes are equal.
	public boolean equals(Object other) {
		// If other is an instance of hash
		if (other instanceof Hash) {
			Hash o = (Hash) other;
			return this.hash.equals(o.hash);
		} // If not, return false
		else
			return false;
	}
}
