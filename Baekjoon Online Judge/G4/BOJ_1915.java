package solving.solve_1028;
//BOJ G4 1915 가장 큰 정사각형

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][m+1];
        int[][] dp = new int[n+1][m+1];
        int res = 0;
        for(int i=1; i<=n; i++) {
            String s = br.readLine();
            for(int j=1; j<=m; j++) {
                map[i][j] = s.charAt(j-1) - '0';
                dp[i][j] = map[i][j];
                int tmp = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                if(map[i][j] == 1 && tmp != 0) {
                    dp[i][j] += tmp;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        System.out.println(res * res);
    }
}
