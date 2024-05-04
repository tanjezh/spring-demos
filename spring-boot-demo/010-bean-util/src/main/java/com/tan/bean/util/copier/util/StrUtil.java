package com.tan.bean.util.copier.util;

/**
 * @author tanjezh
 * @create 2024-05-03 21:42
 */
public class StrUtil {
    private static final char UNDER_LINE = '_';

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String toCamel(String str){
        if(null == str  || str.length() == 0){
            return null;
        }
        if(!contains(str,UNDER_LINE)){
            return str;
        }
        int length = str.length();
        boolean nextUnderLine = false;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++){
            char ch = str.charAt(i);
            if(ch == UNDER_LINE){
                nextUnderLine = true;
            } else if (nextUnderLine){
                builder.append(String.valueOf(ch).toUpperCase());
                nextUnderLine = false;
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     */
    public static String toUnderLine(String str){
        if(null == str){
            return str;
        }
        int length = str.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++){
            char ch = str.charAt(i);
            if(Character.isUpperCase(ch)){
                builder.append(UNDER_LINE).append(Character.toLowerCase(ch));
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    /**
     * 判断 str 中是否包含 c
     * @param str
     * @param c
     * @return
     */
    public static boolean contains(String str, char c){
        return str.indexOf(c) >= 0;
    }

    public static void main(String[] args) {
        String userTbl = toCamel("user_tbl_t_test");
        System.out.println("转驼峰："+userTbl);
        String underLine = toUnderLine(userTbl);
        System.out.println("转下划线："+underLine);
    }

}
