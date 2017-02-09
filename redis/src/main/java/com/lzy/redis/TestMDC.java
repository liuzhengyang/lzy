package com.lzy.redis;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-12-15
 */
public class TestMDC {
	private class Node {
		private Node prev;
		private Node next;
		private int value;
		private Lock lock = new ReentrantLock();
		public Node() {}
		public Node(int value, Node prev, Node next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}

	public class SortedList {
		private Node head;
		private Node tail;
		public SortedList() {
			head = new Node();
			tail = new Node();
			head.next = tail;
			tail.prev = head;
		}

		public void insert(int value) {
			Node current = head;
			current.lock.lock();
			Node next = current.next;
			try {
				while(true) {
					next.lock.lock();
					try {
						if (next == tail || next.value > value) {
							Node node = new Node(value, current, next);
							current.next = node;
							next.prev = node;
							return;
						}

					} finally {
						current.lock.unlock();
					}
					current = current.next;
					next = current.next;
				}
			} finally {
				next.lock.unlock();
			}

		}

		public void print() {
			Node current = head.next;
			while(current != tail) {
				System.out.print(current.value + ", ");
				current = current.next;
			}
		}
	}

	@org.junit.Test
	public void testInsert() {
		SortedList sortedList = new SortedList();
		sortedList.insert(3);
		sortedList.insert(2);
		sortedList.insert(4);
		sortedList.print();
	}
}
