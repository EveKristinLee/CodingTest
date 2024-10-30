package solving.solve_1028;
//BOJ G4 2110 공유기 설치

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    static int N; //집의 개수
    static int C; //공유기의 개수
    static long[] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        pos = new long[N];
        for(int i=0; i<N; i++) {
            pos[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(pos);

        long res = 0;
        long front = 0;
        long end = 1_000_000_000;
        while(front<=end) {
            long mid = (front + end) / 2;
//            System.out.println("mid = " + mid);
            int cnt = 1;
            long now = pos[0];
            for(int i=1; i<N; i++) {
                if(pos[i] - now >= mid) {
                    cnt += 1;
                    now = pos[i];
                }
            }

//            System.out.println("cnt = " + cnt);
            if(cnt >= C) {
                front = mid + 1;
                res = mid;
            }
            else {
                end = mid - 1;
            }
        }

        System.out.println(res);
    }
}
