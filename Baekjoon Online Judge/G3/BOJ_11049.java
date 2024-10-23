package solving.solve_0925;
// BOJ G3 11049 행렬 곱셈 순서

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11049 {
    static int N;
    static int[][] num;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N][2];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            num[i][0] = a;
            num[i][1] = b;
        }
        dp = new int[N][N];

        for(int k=1; k<N; k++) { //구간의 간격
            for(int i=0; i+k<N; i++) { //구간 시작점
                dp[i][i+k] = INF;
                for(int j=i; j<i+k; j++) { //중간지점
                    int tmp = dp[i][j]+dp[j+1][i+k]+(num[i][0] * num[j][1] * num[i+k][1]);
                    dp[i][i+k] = Math.min(dp[i][i+k], tmp);
                }
            }
        }
        System.out.println(dp[0][N-1]);
    }
}
