package com.cn.jee.tools;

import java.io.File;

public class GetFoldFileNames {

    public static void main(String[] args) {

        String path = "C:\\Users\\Public\\Pictures\\Sample Pictures"; // 路径
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return;
        }
        print(f);
    }

    public static void print(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        // 递归调用
                        print(fileArray[i]);
                    }
                }
            } else {
                System.out.println(f.getName());
            }
        }
    }
}