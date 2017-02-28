package com.lzy.ds;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-02-21
 */
public class BloomFilterTest {
	public static void main(String[] args) {
		BloomFilter<Integer> bloomFilter = BloomFilter.create(new Funnel<Integer>() {
			public void funnel(Integer from, PrimitiveSink into) {
				into.putInt(from);
			}
		}, 100, 0.001);
		bloomFilter.put(100);
		bloomFilter.put(101);
		bloomFilter.put(102);
		bloomFilter.put(103);
		bloomFilter.put(104);
		System.out.println(bloomFilter.mightContain(1));
		System.out.println(bloomFilter.mightContain(100));
		System.out.println(bloomFilter.mightContain(103));
		System.out.println(bloomFilter.mightContain(105));
		System.out.println(bloomFilter.mightContain(105));
		HashCode hello = Hashing.murmur3_32().hashString("hello", Charsets.UTF_8);
		HashCode hello2 = Hashing.murmur3_32().hashString("hell0", Charsets.UTF_8);
		System.out.println(hello.asInt());
		System.out.println(hello2.asInt());
	}
}
