package solving.solve_0914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질_3_13549 {
    static int N; //수빈이
    static int K; //동생
    static boolean[] visit;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new boolean[100_001];
        res = Integer.MAX_VALUE;

        find(N, K);
        System.out.println(res);
    }

    private static void find(int now, int dest) {
        Queue<Integer[]> q = new ArrayDeque<>();
        q.offer(new Integer[] {now, 0});
        visit[now] = true;

        while (!q.isEmpty()) {
            Integer[] top = q.poll();
            now = top[0];
            int cnt = top[1];
            if(dest == now) {
                res = Math.min(res, cnt);
            }
            if(now * 2 <= 100_000 && !visit[now*2]) {
                visit[now*2] = true;
                q.offer(new Integer[] {now*2, cnt});
            }
            if(now - 1 >= 0 && !visit[now-1]) {
                visit[now-1] = true;
                q.offer(new Integer[] {now-1, cnt+1});
            }
            if(now + 1 <= 100_000 && !visit[now+1]) {
                visit[now+1] = true;
                q.offer(new Integer[] {now+1, cnt+1});
            }
        }
    }
}
