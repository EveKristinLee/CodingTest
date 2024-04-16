package solving_2.solve_12.solve_19;
//BOJ G2 11967 불켜기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11967 {
    private static class Pos{
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int M;
    static List<List<List<Pos>>> button;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    private static boolean isInside(int x, int y) {
        if(x<=0 || y<=0 || x>N || y>N) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        button = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            button.add(new ArrayList<>());
            for(int j=0; j<=N; j++) {
                button.get(i).add(new ArrayList<>());
            }
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            button.get(sx).get(sy).add(new Pos(ex, ey));
        }

        System.out.println(lightOn());
    }

    private static int lightOn() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(1, 1));
        boolean[][] light = new boolean[N+1][N+1];
        boolean[][] visit = new boolean[N+1][N+1];
        List<Pos> route = new ArrayList<>();
        light[1][1] = true;
        visit[1][1] = true;

        int cnt = 1;

        while(!q.isEmpty()) {
            Pos now = q.poll();
            route.add(now);
            for(int i=0; i<button.get(now.x).get(now.y).size(); i++) {
                Pos next = button.get(now.x).get(now.y).get(i);
                if(!light[next.x][next.y]) {
                    cnt++;
                    light[next.x][next.y] = true;
                }
            }

            for(Pos r : route) {
                for(int d=0; d<4; d++) {
                    int nx = r.x + dx[d];
                    int ny = r.y + dy[d];
                    if(!isInside(nx, ny)) {
                        continue;
                    }

                    if(light[nx][ny] && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                    }
                }
            }
        }
        return cnt;
    }
}
