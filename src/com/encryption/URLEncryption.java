package com.encryption;

import java.io.UnsupportedEncodingException;

import com.encryption.RC4Lite;

public class URLEncryption {
	private static final String ENCODING_KEY = "nuProactiveGradsBest";
	public byte[] encodeURL(String userDetails) throws UnsupportedEncodingException {		
	    RC4Lite rc4 = getRC4Lite();
	
	    byte[] in = userDetails.toString().getBytes("UTF8");
	    byte[] out = new byte[in.length];
	
	    rc4.encrypt(in, 0, out, 0, in.length);
	
	    return out;
	}

	public RC4Lite getRC4Lite() throws UnsupportedEncodingException {
	    RC4Lite rc4 = new RC4Lite();
	    rc4.setKey(ENCODING_KEY.getBytes("UTF8"));
	    return rc4;
	}
	
	public String decodeURL(byte[] encodedBytes)
		      throws UnsupportedEncodingException {

		    RC4Lite rc4 = getRC4Lite();

		    byte[] out = new byte[encodedBytes.length];
		    rc4.decrypt(encodedBytes, 0, out, 0, encodedBytes.length);

		    return new String(out);
	}

}
