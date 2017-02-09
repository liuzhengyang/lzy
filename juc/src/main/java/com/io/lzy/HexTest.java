package com.io.lzy;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.util.Arrays;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-24
 */
public class HexTest {

	public static void main(String[] args) throws DecoderException {
		String hexString = "1243";
		System.out.println(Arrays.toString(Hex.decodeHex(hexString.toCharArray())));
	}
}
