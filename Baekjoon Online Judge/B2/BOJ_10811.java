package homework;
//BOJ B2 10811 바구니 뒤집기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10811 {
    static int N;
    static int M;
    static int[] basket;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        basket = new int[N+1];
        for(int i=1; i<=N; i++) {
            basket[i] = i;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            reverse(s, e);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            sb.append(basket[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void reverse(int s, int e) {
        int[] tmp = Arrays.copyOf(basket, N+1);

        int idx = e;
        for(int i=s; i<=e; i++) {
            basket[i] = tmp[idx];
            idx--;
        }
    }
}
