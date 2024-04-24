package solving_2.solve_12.solve_19;
//BOJ G2 7453 합이 0인 네 정수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7453 {
    static int n;
    static long[] A;
    static long[] B;
    static long[] C;
    static long[] D;
    static long[] ab;
    static long[] cd;
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new long[n];
        B = new long[n];
        C = new long[n];
        D = new long[n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong(st.nextToken());
            C[i] = Long.parseLong(st.nextToken());
            D[i] = Long.parseLong(st.nextToken());
        }

        res = 0;

        ab = new long[n*n];
        cd = new long[n*n];

        int idx = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                ab[idx] = A[i]+B[j];
                cd[idx] = C[i]+D[j];
                idx++;
            }
        }
        Arrays.sort(ab);
        Arrays.sort(cd);

        for(int i=0; i<n*n; i++) {
            res += (upper_bound(cd, -(ab[i])) - lower_bound(cd, -(ab[i])));
        }

        System.out.println(res);
    }

    private static long lower_bound(long[] nums, long num) {
        int front = 0;
        int end = n*n-1;

        while(front <= end) {
            int mid = (front + end) / 2;

            if(nums[mid] >= num) {
                end = mid-1;
            }
            else {
                front = mid+1;
            }
        }
        return end;
    }

    private static long upper_bound(long[] nums, long num) {
        int front = 0;
        int end = n*n-1;

        while(front <= end) {
            int mid = (front + end) / 2;

            if(nums[mid] > num) {
                end = mid-1;
            }
            else {
                front = mid+1;
            }
        }
        return end;
    }
}
