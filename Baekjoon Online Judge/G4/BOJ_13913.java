package solving.solve_1028;
//BOJ G4 숨바꼭질 4

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_13913 {
    private static class Pos {
        int idx;
        int time;

        public Pos(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    static int N;
    static int K;
    static boolean[] visit;
    static int[] parent;

    private static boolean isInside(int x) {
        if(x < 0 || x > 100_000) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new boolean[100_001];
        parent = new int[100_001];

        Pos res = bfs();
        System.out.println(res.time);
        Stack<Integer> s = new Stack<>();
        int now = K;
        s.push(K);
        while(now != N) {
            s.push(parent[now]);
            now = parent[now];
        }

        while(!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
    }

    private static Pos bfs() {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(N, 0));
        visit[N] = true;

        while(!q.isEmpty()) {
            Pos now = q.poll();
            if(now.idx == K) {
                return now;
            }

            if(isInside(now.idx - 1)) {
                if(!visit[now.idx - 1]) {
                    visit[now.idx - 1] = true;
                    parent[now.idx - 1] = now.idx;
                    q.offer(new Pos(now.idx - 1, now.time + 1));
                }
            }
            if(isInside(now.idx + 1)) {
                if(!visit[now.idx + 1]) {
                    visit[now.idx + 1] = true;
                    parent[now.idx + 1] = now.idx;
                    q.offer(new Pos(now.idx + 1, now.time + 1));
                }
            }
            if(isInside(now.idx * 2)) {
                if(!visit[now.idx * 2]) {
                    visit[now.idx * 2] = true;
                    parent[now.idx * 2] = now.idx;
                    q.offer(new Pos(now.idx * 2, now.time + 1));
                }
            }
        }
        return null;
    }
}
