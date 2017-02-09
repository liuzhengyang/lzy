package skiplist;

import java.util.List;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-29
 */
public class SkipListNode {
	private double score;
	private Object obj;
	private SkipListNode backwardk;
	private List<SkipListLevel> level;


	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public SkipListNode getBackwardk() {
		return backwardk;
	}

	public void setBackwardk(SkipListNode backwardk) {
		this.backwardk = backwardk;
	}

	public List<SkipListLevel> getLevel() {
		return level;
	}

	public void setLevel(List<SkipListLevel> level) {
		this.level = level;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("SkipListNode{");
		sb.append("score=").append(score);
		sb.append(", obj=").append(obj);
//		sb.append(", backwardk=").append(backwardk);
		sb.append(", level=").append(level);
		sb.append('}');
		return sb.toString();
	}
}
