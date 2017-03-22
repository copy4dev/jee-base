package com.cn.jee.tools;

import java.util.UUID;

public class SnGen {

	public static void main(String[] args) {
		long startT = System.currentTimeMillis();

		// 通过UUID的哈希值产生SN
		getSnByUUIdTest();

		long endT = System.currentTimeMillis();
		System.out.println("耗时：" + (endT - startT));
	}

	/** 通过UUID的哈希值产生SN - 大于10000有可能重复 */
	public synchronized static String getSnByUUId() {
		int machineId = 1;// 最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {
			// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 15 代表长度为15
		// d 代表参数为正数型
		return machineId + String.format("%015d", hashCodeV);
	}

	/** 通过UUID的哈希值产生SN - 测试 */
	private final static void getSnByUUIdTest() {
		int s = 10000;
		long[] ary = new long[s];
		for (int i = 0; i < s; i++) {
			ary[i] = Long.parseLong(getSnByUUId());
		}

		// 升序排序
		// Arrays.sort(ary);

		TAG_1: for (int i = 0; i < s; i++) {
			for (int j = i + 1; j < s; j++) {
				if (ary[i] == ary[j]) {
					System.out.println(ary[i] + " - " + ary[j]);
					break TAG_1;
				}
			}
			if (i == s - 1) {
				System.out.println("没有重复");
			}
		}
	}
}