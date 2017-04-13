package disruptor;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-04-09
 */
public class LongEvent {
	private long value;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("LongEvent{");
		sb.append("value=").append(value);
		sb.append('}');
		return sb.toString();
	}
}
