package solving;
//BOJ S4 11047 동전 0

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coin = new int[N];
        for(int i=0; i<N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int res = 0;
        int now = K;
        int idx = N-1;
        while(now > 0) {
            if(now >= coin[idx]) {
                int tmp = now / coin[idx];
                res += tmp;
                now -= tmp * coin[idx];
            }
            idx--;
        }
        System.out.println(res);
    }
}
