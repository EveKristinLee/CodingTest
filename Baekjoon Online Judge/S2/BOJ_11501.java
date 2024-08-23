package algo_0822;
//BOJ S2 11501 주식

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11501 {
    static int N; //날의 수
    static int[] profit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());
            profit = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                profit[i] = Integer.parseInt(st.nextToken());
            }

            long sum = 0;
            int nowMax = profit[N-1];
            for(int i=N-2; i>=0; i--) {
                if(profit[i] <= nowMax) {
                    sum += (nowMax - profit[i]);
                }

                if(profit[i] > nowMax) {
                    nowMax = profit[i];
                }
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
