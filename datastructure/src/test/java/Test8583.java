import org.apache.commons.codec2.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-10-27
 */
public class Test8583 {
	@org.junit.Test
	public void testDecode() {
		String str = "AKZgCQAAAGAykDIAAjgzMTQ1Mjc1ODEyMDAwNDMwMjM1MDYyMDEyNZh9fOmXSZ4npvIRFClZLAQS\\nbm5owoUeCpTljjcCmgg9ug7vvzKwYoXve3IkfssaMSJsVfTQSexdDbNAJQzJG234u5M4yMmuiXTY\\nr4bVu5hn0btRYTeMaHYr4U8RXbUN5+HHugBZxXDh2psYy4wnkkIJ6kOd4JF4V0qRZnui1J7F";
		System.out.println(Arrays.toString(str.getBytes(StandardCharsets.UTF_8)));
		System.out.println(Hex.encodeHexString(str.getBytes()));
	}
}
