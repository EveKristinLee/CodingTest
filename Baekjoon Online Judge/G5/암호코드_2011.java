package solving.solve_0914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 암호코드_2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] dp = new int[s.length() +1];
        dp[0] = 1;
        for(int i=1; i<=s.length(); i++) {
            int n = s.charAt(i-1) - '0';
            if(n>=1 && n<=9) { //한자리 숫자
                dp[i] += dp[i-1];
                dp[i] %= 1000000;
            }

            if(i>1) {
                int p = s.charAt(i-2) - '0';
                if(p!=0) {
                    int tmp = p*10 + n;
                    if(tmp >= 10 && tmp <=26) {
                        dp[i] += dp[i-2];
                        dp[i] %= 1000000;
                    }
                }
            }
        }
        System.out.println(dp[s.length()]);
    }
}
