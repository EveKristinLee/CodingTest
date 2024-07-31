package algo_0822;
//BOJ S2 16953 A -> B

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16953 {
    static long A;
    static long B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        int res = calc(A, 1);
        if (res == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(res);
        }
    }

    private static int calc(long now, int cnt) {
        if(now > B) {
            return Integer.MAX_VALUE;
        }
        if(now == B) {
            return cnt;
        }
        int nowRes = calc(now*2, cnt+1);
        String numString = Long.toString(now);
        numString += "1";
        int nextRes = calc(Long.parseLong(numString), cnt+1);
        return Math.min(nowRes, nextRes);
    }
}
