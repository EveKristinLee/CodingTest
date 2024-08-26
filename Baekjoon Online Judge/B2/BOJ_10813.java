package homework;
//BOJ B2 10813 공 바꾸기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10813 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] ball = new int[N+1];
        for (int i=1; i<=N; i++) {
            ball[i] = i;
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int tmp = ball[a];
            ball[a] = ball[b];
            ball[b] = tmp;
        }

        for(int i=1; i<=N; i++) {
            System.out.print(ball[i] + " ");
        }
        System.out.println();
    }
}
