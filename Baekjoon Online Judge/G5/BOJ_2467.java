package solving.solve_0914;
//BOJ G5 2467 용액

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467 {
    static int N;
    static long[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        int left = 0;
        int right = N-1;
        int resL = -1;
        int resR = -1;
        long resSum = Long.MAX_VALUE;
        while(left < right) {
            long sum = nums[left] + nums[right];

            if(resSum > Math.abs(sum)) { //오차가 더 작은 조합 발견
                resL = left;
                resR = right;
                resSum = Math.abs(sum);
            }
            if(sum >= 0) {
                right -= 1;
            }
            else {
                left += 1;
            }
        }

        System.out.println(nums[resL] + " " + nums[resR]);
    }
}
