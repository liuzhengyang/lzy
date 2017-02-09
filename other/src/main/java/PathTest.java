import java.nio.file.Paths;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-11-03
 */
public class PathTest {
	public static void main(String[] args) {
		System.out.println(Paths.get("file").toFile().getAbsoluteFile());
	}
}
