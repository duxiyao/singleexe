package com.dxy.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wudan-mac
 * @ClassName: FileHelper
 * @ClassNameExplain: 文件操作辅助类
 * @Description:
 * @date 16/4/20 下午3:40
 */
public class FileHelper {

    /**
     * @param baseDir 基础路径
     * @param file    文件绝对路径
     * @return java.lang.String
     * @Title: getRelativePath
     * @TitleExplain:
     * @Description: 获取相对路径
     * @version 1.0.0
     * @author wudan-mac
     */
    public static String getRelativePath(File baseDir, File file) {
        if (baseDir.equals(file))
            return "";
        if (baseDir.getParentFile() == null)
            return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
    }

    /**
     * @param file      文件夹
     * @param collector 存储文件夹下文件的集合
     * @return void
     * @Title: getRelativePath
     * @TitleExplain:
     * @Description: 获取文件夹下全部文件
     * @version 1.0.0
     * @author wudan-mac
     */
    public static void listFiles(File file, List collector) throws IOException {
        collector.add(file);
        if ((!file.isHidden() && file.isDirectory()) && !isIgnoreFile(file)) {
            File[] subFiles = file.listFiles();
            for (int i = 0; i < subFiles.length; i++) {
                listFiles(subFiles[i], collector);
            }
        }
    }

    public static void listOnlyFiles(File file, List collector) throws IOException {
        if (file.isFile())
            collector.add(file);
        if ((!file.isHidden() && file.isDirectory()) && !isIgnoreFile(file)) {
            File[] subFiles = file.listFiles();
            for (int i = 0; i < subFiles.length; i++) {
                listOnlyFiles(subFiles[i], collector);
            }
        }
    }

    public static void listOnlyDirs(File file, List collector) throws IOException {
        if (file.isDirectory())
            collector.add(file);
        if ((!file.isHidden() && file.isDirectory()) && !isIgnoreFile(file)) {
            File[] subFiles = file.listFiles();
            for (int i = 0; i < subFiles.length; i++) {
                listOnlyDirs(subFiles[i], collector);
            }
        }
    }

    public static void listOnlyFilesByOneDeep(File file, List collector)  {

        if ((!file.isHidden() && file.isDirectory()) && !isIgnoreFile(file)) {
            File[] subFiles = file.listFiles();
            for (int i = 0; i < subFiles.length; i++) {
                if (subFiles[i].isFile())
                    collector.add(subFiles[i]);
            }
        }
    }
    public static void listOnlyDirsByOneDeep(File file, List collector) throws IOException {

        if ((!file.isHidden() && file.isDirectory()) && !isIgnoreFile(file)) {
            File[] subFiles = file.listFiles();
            for (int i = 0; i < subFiles.length; i++) {
                if (subFiles[i].isDirectory())
                    collector.add(subFiles[i]);
            }
        }
    }

    /**
     * @param file 文件
     * @return boolean  true 需要忽略   false 不需要忽略
     * @Title: getRelativePath
     * @TitleExplain:
     * @Description: 判断是否为要忽略的文件
     * @version 1.0.0
     * @author wudan-mac
     */
    private static boolean isIgnoreFile(File file) {
        List ignoreList = new ArrayList();
        ignoreList.add(".svn");
        ignoreList.add("CVS");
        ignoreList.add(".idea");
        ignoreList.add(".iml");
        ignoreList.add(".jcfg");
        for (int i = 0; i < ignoreList.size(); i++) {
            if (file.getName().equals(ignoreList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        return readFileContent(file);
    }

    public static String readFileContent(File file) {
        String encoding = "UTF-8";
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 迭代删除文件夹
     *
     * @param dirPath 文件夹路径
     */
    public static void deleteDir(String dirPath) {
        File file = new File(dirPath);
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }

    /**
     * 复制文件
     *
     * @param source
     * @param dest
     * @throws IOException
     */
    public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

    public static String txt2String(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
