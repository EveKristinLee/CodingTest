package solving.solve_0914;
//BOJ G5 3079 입국심사

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3079 {
    static long N; //심사대 수
    static long M; //친구의 수
    static long[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        times = new long[(int)N];
        long minValue = Long.MAX_VALUE;
        for(int i=0; i<N; i++) {
            times[i] = Long.parseLong(br.readLine());
            minValue = Math.min(minValue, times[i]);
        }

        long front = minValue;
        long end = minValue * M;
        long res = 0;
        while(front <= end) {
            long mid = (front + end) / 2;

            if(isComplete(mid) >= M) {
                res = mid;
                end = mid - 1;
            }
            else {
                front = mid + 1;
            }
        }
        System.out.println(res);
    }

    private static long isComplete(long time) {
        long cnt = 0;
        for(int i=0; i<N; i++) {
            cnt += (time / times[i]);
        }
        return cnt;
    }
}
