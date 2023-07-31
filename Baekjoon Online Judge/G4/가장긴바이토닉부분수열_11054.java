//BOJ G4
package solving.solve_1028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 가장긴바이토닉부분수열_11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int[] dp1 = new int[N];
        int[] dp2 = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        dp1[0] = 1;
        dp2[N-1] = 1;
        for(int i=1; i<N; i++) {
            dp1[i] = 1;
            for(int j=0; j<i; j++) {
                if(nums[j] < nums[i] && dp1[j] >= dp1[i]) {
                    dp1[i] = dp1[j] + 1;
                }
            }
        }

        for(int i=N-2; i>=0; i--) {
            dp2[i] = 1;
            for(int j=N-1; j>i; j--) {
                if(nums[j] < nums[i] && dp2[j] >= dp2[i]) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        }

        int res = 0;
        for(int i=0; i<N; i++) {
            res = Math.max(res, dp1[i] + dp2[i] - 1);
        }
        System.out.println(res);
    }
}
