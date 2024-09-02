package solving.solve_0930;
//BOJ S3 31870 버블버블

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_31870 {
    static int N;
    static int[] nums;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        res = Integer.MAX_VALUE;
        int[] tmp = Arrays.copyOf(nums, N);
        ascending(nums);
        descending(tmp);
        System.out.println(res);
    }

    private static void ascending(int[] nums) {
        int cnt = 0;

        boolean complete = false;
        while (true) {
            if(complete) {
                break;
            }

            boolean chk = false;
            for(int i=0; i<N-1; i++) {
                if(nums[i] > nums[i+1]) {
                    chk = true;
                    int tmp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = tmp;
                    cnt+=1;
                }
            }

            if(!chk) {
                complete = true;
            }
        }

        res = Math.min(res, cnt);
    }

    private static void descending(int[] nums) {
        int cnt = 1;

        boolean complete = false;
        while (true) {
            if(complete) {
                break;
            }

            boolean chk = false;
            for(int i=0; i<N-1; i++) {
                if(nums[i] < nums[i+1]) {
                    chk = true;
                    int tmp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = tmp;
                    cnt+=1;
                }
            }

            if(!chk) {
                complete = true;
            }
        }

        res = Math.min(res, cnt);
    }
}
