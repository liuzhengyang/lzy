package skiplist;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-29
 */
public class SkipListLevel {
	private SkipListNode forward;
	private int span;

	public SkipListNode getForward() {
		return forward;
	}

	public void setForward(SkipListNode forward) {
		this.forward = forward;
	}

	public int getSpan() {
		return span;
	}

	public void setSpan(int span) {
		this.span = span;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("SkipListLevel{");
//		sb.append("forward=").append(forward);
		sb.append(", span=").append(span);
		sb.append('}');
		return sb.toString();
	}
}
