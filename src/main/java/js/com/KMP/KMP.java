package js.com.KMP;

/**
 * KMP算法
 * 关键在于计算一个next数组，存储对于每个查找串S不需要重复循环的偏移量
 * Created by Administrator on 2015/4/21.
 */
public class KMP {
    public static void main(String[] args) {
        String s = "acbcdcfcdcdfcdcfdfcdcdsdsacbc";
        String p = "cdcfd";
        System.out.println(searchStr(s, p));;
    }

    public static int searchStr(String str, String pattern){
        boolean start =false;
        boolean find = false;
        int startI = 0;
        int time = 0;


        int[] n = next(pattern);
        for(int i=0; i<str.length(); ){
            if( find == true){
                break;
            }
            int j = 0;
            for(j=0; j<pattern.length(); j++){
                time++;
                if(str.charAt(j+i) == pattern.charAt(j)){
                    if(start == false){
                        startI = i;
                        start = true;
                    }
                    if(j == pattern.length()-1){
                        find = true;
                        break;
                    }
                }else{
                    start=false;
                    break;
                }
            }
            i++;
            i+=n[j];

        }
        System.out.println("循环次数："+time);

        if(find){
            return startI;
        }else{
            return -1;
        }
    }

    public static int[] next(String str){
        int[] nextArr = new  int[str.length()];
        if(str == null || "".equals(str)){
            return null;
        }
        nextArr[0] = 0;
        for(int i=1; i<str.length();i++){

            if(nextArr[i-1] > 1){
                if(str.charAt(i-1) == str.charAt(nextArr[i-1]-1)){
                    nextArr[i] = nextArr[i-1] + 1;
                }else{
                    nextArr[i] = 1;
                }
            }else{
                if(i!=1 &&str.charAt(i-1) == str.charAt(0)){
                    nextArr[i] = 2;
                }else{
                    nextArr[i] = 1;
                }
            }

        }
        return nextArr;
    };
}
