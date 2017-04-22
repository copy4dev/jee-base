package com.cn.jee.tools;

import java.util.UUID;

/**
 * 编码产生与校验工具
 * 
 * @author 1002360
 * @version 2017年4月7日上午11:10:49 1002360 TODO
 */
public class SnGen {

    public static void main(String[] args) {
        long startT = System.currentTimeMillis();

        // 通过UUID的哈希值产生16位SN
        getSnByUUIdTest(1);

        // 条码校验
        barCodeVerificationTest("6911989251236");

        long endT = System.currentTimeMillis();
        System.out.println("耗时：" + (endT - startT));
    }

    /** 通过UUID的哈希值产生16位SN - 大于20000有可能重复 */
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

    /**
     * 条码校验
     * 
     * @param barCode
     * @return
     */
    public final static boolean barCodeVerification(String barCode) {
        if (barCode.matches("^\\d{13}$")) {
            int c1 = Integer.parseInt(barCode.charAt(0) + "") + Integer.parseInt(barCode.charAt(2) + "") + Integer.parseInt(barCode.charAt(4) + "") + Integer.parseInt(barCode.charAt(6) + "") + Integer.parseInt(barCode.charAt(8) + "") + Integer.parseInt(barCode.charAt(10) + "");
            int c2 = (Integer.parseInt(barCode.charAt(1) + "") + Integer.parseInt(barCode.charAt(3) + "") + Integer.parseInt(barCode.charAt(5) + "") + Integer.parseInt(barCode.charAt(7) + "") + Integer.parseInt(barCode.charAt(9) + "") + Integer.parseInt(barCode.charAt(11) + "")) * 3;
            int c3 = Integer.parseInt(barCode.charAt(12) + "");
            int cc = 10 - (c1 + c2) % 10;
            if (cc == 10) {
                cc = 0;
            }
            if (cc == c3) {
                return true;
            }
        }
        return false;
    }

    /* 测试区 */

    /** 通过UUID的哈希值产生16位SN - 测试 */
    private final static void getSnByUUIdTest(int loop) {
        long[] ary = new long[loop];
        for (int i = 0; i < loop; i++) {
            ary[i] = Long.parseLong(getSnByUUId());
        }

        // 升序排序
        // Arrays.sort(ary);

        TAG_1: for (int i = 0; i < loop; i++) {
            for (int j = i + 1; j < loop; j++) {
                if (ary[i] == ary[j]) {
                    System.out.println(ary[i] + " - " + ary[j]);
                    break TAG_1;
                }
            }
            if (i == loop - 1) {
                System.out.println("通过UUID的哈希值产生16位SN没有重复");
            }
        }
    }

    /**
     * 条码校验 - 测试
     * 
     * @param barCode
     */
    private final static void barCodeVerificationTest(String barCode) {
        System.out.println("条码校验结果:" + barCodeVerification(barCode));
    }

}