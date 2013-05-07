package com.encryption;

public final class RC4Lite {

	  private static int MAX_SIZE = 256;
	
	  private final byte[] state = new byte[MAX_SIZE];
	
	  public void encrypt(byte[] src, int srcOffset, byte[] dest, int destOffset,
	      int length) {
	
	    int end = srcOffset + length;
	
	    int i = 0;
	    int j = 0;
	    int l = destOffset;
	    for (int k = srcOffset; k < end; k++, l++) {
	      i = (i + 1) & 0xff;
	      j = (j + state[i]) & 0xff;
	      swap(i, j);
	      int m = (state[i] + state[j]) & 0xff;
	      dest[l] = (byte) (state[m] ^ src[k]);
	    }
	
	  }
	
	  private void swap(int i, int j) {
	
	    byte temp = state[i];
	    state[i] = state[j];
	    state[j] = temp;
	  }
	
	  public void decrypt(byte[] src, int srcOffset, byte[] dest, int destOffset, int length) {
	
	    encrypt(src, srcOffset, dest, destOffset, length);
	  }
	
	  public void setKey(byte[] key) {
	
	    initStateArray();
	    createKey(key);
	  }
	
	  private void initStateArray() {
	
	    for (int i = 0; i < MAX_SIZE; i++) {
	      state[i] = (byte) i;
	    }
	  }
	
	  private void createKey(byte[] key) {
	
	    int j = 0;
	    for (int i = 0; i < MAX_SIZE; i++) {
	      j = (j + state[i] + key[i % key.length]) & 0xff;
	      swap(i, j);
	    }
	  }
}
