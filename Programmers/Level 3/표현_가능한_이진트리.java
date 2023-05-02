import java.util.*;
public class 표현_가능한_이진트리 {
    static int result = 1;
    static boolean[] bin;
    static int binLen;
    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i=0; i<numbers.length; i++) {
            String s = Long.toBinaryString(numbers[i]);
            int len = s.length();
            int exp = 1;
            do {
                binLen = (int)Math.pow(2, exp++) - 1;
            }while(binLen < len);
            bin = new boolean[binLen];
            int idx = binLen - len;
            for(int j=0; j<len; j++) {
                if(s.charAt(j) == '1') {
                    bin[idx] = true;
                }
                idx++;
            }

            result = 1;
            possible(0, binLen-1, false);
            answer[i] = result;
        }
        return answer;
    }

    public static void possible(int s, int e, boolean isDump) {
        int mid = (s + e)/2;

        if(isDump && bin[mid]) {
            result = 0;
            return;
        }

        if(s!=e) {
            possible(s, mid-1, !bin[mid]);
            possible(mid+1, e, !bin[mid]);
        }
    }

    public static void main(String[] args) {
        long[] numbers = {7, 42, 5};
        int[] res = solution(numbers);
        for(int i=0; i<res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
