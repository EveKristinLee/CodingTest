package solving.solve_0914;
//BOJ G5 1052 물병

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1052 {
    static int N;
    static int K;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        res = 0;

        while(true) {
            int count = Integer.bitCount(N);
            if(count <= K) {
                break;
            }
            N++;
            res += 1;
        }

        System.out.println(res);
    }

}
