package solving.solve_1028;
//BOJ G4 17951 흩날리는 시험지 속에서 내 평점이 느껴진거야

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17951 {
    static int N; //시험지 개수
    static int K; //그룹 수
    static long[] paper;
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        paper = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            paper[i] = Integer.parseInt(st.nextToken());
        }

        long front = 0;
        long end = 100_000L * 100_000L;
        while(front <= end) {
            long mid = (front + end) / 2;

            if(dividePaper(mid)) {
                res = mid;
                front = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        System.out.println(res);
    }

    private static boolean dividePaper(long res) {
        long sum = 0;
        int cnt = 0;
        for(int i=0; i<N; i++) {
            sum += paper[i];
            if(sum >= res) {
                cnt++;
                sum=0;
            }
        }

        if(cnt >= K) {
            return true;
        }
        return false;
    }
}
