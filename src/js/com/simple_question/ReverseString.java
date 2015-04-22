package js.com.simple_question;

/**
 * 字符串反转
 * 问题1，把how are you反转为 you are how
 * 问题2，完全反转 how are you 反转为 uoy era woh
 * Created by Administrator on 2015/4/22.
 */
public class ReverseString {
    public static void main(String[] args) {
        reverseWord();
        System.out.println();
        reverseSentence();
    }

    public static void reverseWord(){
        String s = "how are you";
        String[] words = s.split(" ");
        for(int i=words.length-1; i>=0; i--){
            System.out.print(words[i] + " ");
        }
    }

    public static void reverseSentence(){
        String s = "how are you";
        char[] a = s.toCharArray();
        for(int i=a.length-1; i>=0; i--){
            System.out.print(a[i]);
        }
    }
}
