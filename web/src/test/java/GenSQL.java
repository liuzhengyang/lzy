import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-11-22
 */
public class GenSQL {
	@Test
	public void testGen() {
		String[] ids = new String[100];
		String countSql = "select ";
		for (int i = 11112200; i <= 11112299; i++) {
			String sql = "delete from trade_order_" + i%100 + " where merchant_id=" + i + ";sleep(0.01);";
			String sql2 = "select count(*) from trade_order_" + i%100 + " where merchant_id=" + i + "; ";
			System.out.println(sql);

			countSql += "(SELECT COUNT(*) FROM trade_order_" + i%100 + " where order_status = 8 and  merchant_id=" + i + ")";
			if (i < 11112299) {
				countSql += " +";
			}
		}
		countSql +=" as totalcount";
		System.out.println(countSql);

	}

	@Test
	public void testUnicode() {
		char a = '1';
//		Character c = new Character(a);
		System.out.println("你".getBytes(Charset.forName("UTF-8")).length);
		System.out.println("你好".getBytes(Charset.forName("UTF-8")).length);
		System.out.println("你好".getBytes(Charset.forName("UTF-16")).length);
		System.out.println("你".getBytes(Charset.forName("UTF-16")).length);
		System.out.println("a".getBytes(Charset.forName("UTF-16")).length);
		System.out.println(Arrays.toString("a".getBytes(Charset.forName("UTF-16"))));
		System.out.println(Arrays.toString("a".getBytes(Charset.forName("UTF-8"))));
		System.out.println("a".getBytes(Charset.forName("UTF-8")).length);
		System.out.println("a".getBytes(Charset.forName("UTF-16")).length);
		System.out.println("a2b".getBytes(Charset.forName("UTF-8")).length);
		System.out.println();
	}
}
