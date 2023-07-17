//BOJ S3
package solving.solve_0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 피보나치_함수_1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] zero = new int[n+1];
            int[] one = new int[n+1];
            zero[0] = 1;
            one[0] = 0;
            if(n >= 1) {
                zero[1] = 0;
                one[1] = 1;
            }
            for(int i=2; i<=n; i++) {
                zero[i] = zero[i-1] + zero[i-2];
                one[i] = one[i-1] + one[i-2];
            }
            System.out.println(zero[n] + " " + one[n]);
        }
    }
}
