package algo_0822;
//BOJ S2 1326 폴짝폴짝

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1326 {
    private static class Pos {
        int idx;
        int cnt;

        public Pos(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    static int N;
    static int[] bridge;
    static int a;
    static int b;

    private static boolean isInside(int pos) {
        if(pos < 1 || pos > N) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bridge = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            bridge[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        System.out.println(jump());
    }

    private static int jump() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(a, 0));

        while(!q.isEmpty()) {
            Pos now = q.poll();
            if(now.idx == b) {
                return now.cnt;
            }

            int idx = now.idx;
            int mul = bridge[now.idx];
            int cnt = 1;
            while((mul * cnt) <= N) {
                if(isInside(idx - (mul * cnt))) {
                    q.offer(new Pos(idx - (mul * cnt), now.cnt+1));
                }
                if(isInside(idx + (mul * cnt))) {
                    q.offer(new Pos((idx + (mul * cnt)), now.cnt+1));
                }
                cnt++;
            }
        }
        return -1;
    }
}
