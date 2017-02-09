import com.google.common.base.Splitter;

import java.util.List;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-26
 */
public class ReplaceString {
	public static void main(String[] args) {
		String all = "898102707800001 \n" +
				"898580000000002 \n" +
				"898580000000001 \n" +
				"898580007420001 \n" +
				"898881100000001 \n" +
				"898332207420001 \n" +
				"898394207800001 \n" +
				"898102758140001 \n" +
				"898397407630002 \n" +
				"898397407630001";
		List<String> strings = Splitter.onPattern("\\W").trimResults().splitToList(all);
		String str = "update age_lev1_mcht_base_inf_tmp set acq_inst_id='Z0020001' where mcht_no='MERCHANT';\n" +
				"update tbl_mcht_base_inf set acq_inst_id='Z0020001' where mcht_no='MERCHANT';";
		for (String s : strings) {
			System.out.println(str.replaceAll("MERCHANT", s));
		}

	}
}
