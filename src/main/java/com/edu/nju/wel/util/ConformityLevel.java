package com.edu.nju.wel.util;

/**
 * 判定关键词与电影名的符合程度
 * Created by qianzhihao on 2017/3/24.
 */
public class ConformityLevel {
    /**
     * 返回分成的级数（不包括-1）
     * @return 分成的级数
     */
    public static int getLevels(){
        return 4;
    }

    /**
     * 返回关键词与电影名的符合程度
     *
     * @param keywords 关键词
     * @param name     电影名
     * @return 符合程度，越小说明越符合，负数表示不符合各种情况
     * 0：完全一样
     * 1：从电影名的某个单词开始与当前关键字符合的(即智能补全内容）
     * 2：包含所有关键字的（以空格为分隔号）
     * 3+：包含某些关键字，包含的越多数字越大
     * -1：不符合任意情况
     */
    public static int getConformityLevel(String keywords, String name) {
        //均转为小写
        String k = keywords.toLowerCase();
        String n = name.toLowerCase();
        if (isCompletelySame(k, n))
            return 0;
        else if (isPartOf(k, n))
            return 1;
        else
            return keywordsContain(k, n);
    }

    /**
     * 是否完全一样，对应情况0
     *
     * @param k 关键字
     * @param n 电影名
     * @return 是否对应情况0
     */
    private static boolean isCompletelySame(String k, String n) {
        return k.equals(n);
    }

    /**
     * 是否能被智能补全，对应情况1
     *
     * @param k 关键字
     * @param n 电影名
     * @return 是否对应情况1
     */
    private static boolean isPartOf(String k, String n) {
        //判断是否是一部分
        int index = n.indexOf(k);
        if (index == -1) {
            return false;
        } else {
            //判断是否是作为一个单词的开始
            //若不是单词首字母
            if (index > 0 && isWord(n.charAt(index - 1))) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 返回关键字包含情况，即判断在情况2、3+、-1中的哪一个
     *
     * @param k 关键词
     * @param n 电影名
     * @return 2、3+、-1中的一个
     */
    private static int keywordsContain(String k, String n) {
        for(String word:commonWords){
            //并删除一个空格，防止出现两个空格
            k = k.replace(word+" ","");
        }
        String[] words = k.split(" ");
        String[] nameWords = n.replaceAll("[~!()|':;',\\[\\]./?-]", " ").split(" ");
        //符合条件单词个数
        int inWordCount = 0;
        //是否都符合条件
        boolean allWordIn = true;

        for (String word : words) {
            boolean in = false;
            for (String name : nameWords) {
                //去除the
                if(name.equals(word)){
                    inWordCount++;
                    in = true;
                }
            }
            if(!in){
                allWordIn = false;
            }
        }
        if (allWordIn) {
            return 2;
        } else if ((!allWordIn) && inWordCount>0) {
            //范围3以上的数字，越大符合程度越大
            return 2+inWordCount;
        } else {
            return -1;
        }
    }

    private static final String[] commonWords = {"and","the","of"};

    private static boolean isWord(char c) {
        return c >= 'a' && c <= 'z';
    }
}
