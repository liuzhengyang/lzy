package skiplist;

import java.util.Random;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-29
 */
public class ZSet {
	private static final int SKIP_LIST_MAX_LEVEL = 32;
	private static final double SKIP_LIST_P = 0.25;

	public SkipList createSkipList() {
		SkipList skipList = new SkipList();
		skipList.setLevel(1);
		skipList.setLength(0);
		skipList.setHeader(createSkipListNode(SKIP_LIST_MAX_LEVEL, 0, null));
		for (int i = 0; i < SKIP_LIST_MAX_LEVEL; i++) {
			skipList.getHeader().getLevel().get(i).setForward(null);
			skipList.getHeader().getLevel().get(i).setSpan(0);
		}
		skipList.getHeader().setBackwardk(null);
		skipList.setTail(null);
		return skipList;
	}

	public SkipListNode createSkipListNode(int level, double score, Object obj) {
		SkipListNode skipListNode = new SkipListNode();
		skipListNode.setScore(score);
		skipListNode.setObj(obj);
		return skipListNode;
	}

	public static int getRandomLevel() {
		int level = 1;
		Random random = new Random();
		while ((random.nextLong() & 0xFFFF) < (SKIP_LIST_P * 0xFFFF)) {
			level += 1;
		}
		return level < SKIP_LIST_MAX_LEVEL ? level : SKIP_LIST_MAX_LEVEL;
	}

	public SkipListNode insert(SkipList skipList, double score, Object obj) {
		return null;
	}

	public static void main(String[] args) {
	}
}
