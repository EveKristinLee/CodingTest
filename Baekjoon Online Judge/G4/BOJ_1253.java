package solving.solve_1028;
//BOJ G4 1253 좋다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {
    static int N;
    static long[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            num[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(num);

        int res = 0;
        for(int i=0; i<N; i++) {
            if(isGood(i)) {
                res += 1;
            }
        }

        System.out.println(res);
    }

    private static boolean isGood(int idx) {
        for(int i=0; i<N; i++) {
            if(i != idx) {
                long diff = num[idx] - num[i];
                int front = 0;
                int end = N-1;

                while(front <= end) {
                    int mid = (front + end) / 2;
                    if(mid != idx && mid != i && num[mid] == diff) {
                        return true;
                    }
                    if(num[mid] > diff) {
                        end = mid-1;
                    }
                    else {
                        front = mid + 1;
                    }
                }
            }
        }
        return false;
    }
}
