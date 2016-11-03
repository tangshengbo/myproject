package com.tangshengbo.io;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class EncodeTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		EncodeTest test = new EncodeTest();
		test.testCharset();
	}

	public void testCharset() {
		String str = "唐声波";
		Charset charset = Charset.forName("UTF-8");
        System.out.println("tangshengbo");
        ByteBuffer bf = charset.encode(str);
		CharBuffer cb = charset.decode(bf);
		System.out.println(cb.toString());

	}
   private void encodeType() throws UnsupportedEncodingException{
	    //System.out.println(Arrays.toString(args));
		String txt = "慕课abc";
		byte[] bs = txt.getBytes();
		for (byte b : bs) {
			System.out.print(Integer.toHexString(b & 0xff) + "\t");

		}
		System.out.println();
		System.out.println("--------------------------------------");
		bs = txt.getBytes("gbk");
		for (byte b : bs) {
			System.out.print(Integer.toHexString(b & 0xff) + "\t");

		}
		System.out.println();
		System.out.println("--------------------------------------");
		bs = txt.getBytes("UTF-8");
		for (byte b : bs) {
			System.out.print(Integer.toHexString(b & 0xff) + "\t");

		}
		System.out.println();
		System.out.println("--------------------------------------");
		bs = txt.getBytes("UTF-16be");
		for (byte b : bs) {
			System.out.print(Integer.toHexString(b & 0xff) + "\t");

		}
		System.out.println();
		System.out.println("--------------------------------------");
		String charTxt = new String(bs, "UTF-16be");
		System.out.println(charTxt);
	   
   }

}
