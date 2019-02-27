package pc;

public class Hash {
	protected byte[] hash;

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
		// method to return the string representation of the Hash
	public String toString() {
	  String hashStr = ""; 
	// a loop that goes through the length of the hash
      for(int i = 0; i < this.hash.length; i++){ 
           hashStr+= String.format("%x", Byte.toUnsignedInt(this.hash[i]));
      }
      return hashStr;
	}


	public boolean equals(Object other) {
		if (other instanceof Hash) {
			Hash o = (Hash) other;
			return this.hash.equals(o);
		} else
			return false;

	}
}
