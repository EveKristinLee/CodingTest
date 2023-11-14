package solving.solve_0914;
//BOJ G5 수 고르기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2230 {
    static int N;
    static int M;
    static int[] nums;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        res = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        int front = 0;
        int end = 1;
        Arrays.sort(nums);
        while(front <= end && end < N) {
            int now = nums[end] - nums[front];
            if(now >= M) {
                res = Math.min(res, now);
                front++;
            }
            else{
                end++;
            }
        }

        System.out.println(res);
    }
}
