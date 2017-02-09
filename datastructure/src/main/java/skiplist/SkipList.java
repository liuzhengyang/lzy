package skiplist;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-29
 */
public class SkipList {
	private SkipListNode header;
	private SkipListNode tail;
	private long length;
	private int level;

	public SkipListNode getHeader() {
		return header;
	}

	public void setHeader(SkipListNode header) {
		this.header = header;
	}

	public SkipListNode getTail() {
		return tail;
	}

	public void setTail(SkipListNode tail) {
		this.tail = tail;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("SkipList{");
		sb.append("header=").append(header);
		sb.append(", tail=").append(tail);
		sb.append(", length=").append(length);
		sb.append(", level=").append(level);
		sb.append('}');
		return sb.toString();
	}
}
