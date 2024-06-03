package 기출문제;

import java.util.*;
import java.io.*;

public class CodeTree_마법의_숲_탐색 {
    private static class Army {
        int x;
        int y;
        public Army(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static class Golem{
        int idx;
        int x;
        int y;
        int dir;

        public Golem(int idx, int x, int y, int dir) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static int R;
    static int C;
    static int K;
    static int res;
    static int[] ex = {-2, -1, 0, -1};
    static int[] ey = {0, 1, 0, -1}; //북, 동, 남, 서

    static int[] mx = {0, 0, 1, -1};
    static int[] my = {1, -1, 0, 0};
    static int[][] map;
    static int[][] exit;

    private static boolean isInside(int x, int y) {
        if(x<=0 || y<=0 || x>R || y>C) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) + 3;
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R+1][C+1];
        exit = new int[R+1][C+1];

        res = 0;
        for(int i=1; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            Golem golem = new Golem(i, 2, y, dir);
            GolemMove(golem);
        }
        System.out.println(res);
    }

    private static void armyMove(Golem g) {
        int exitX = g.x + ex[g.dir];
        int exitY = g.y + ey[g.dir];
        exit[exitX][exitY] = g.idx; //출구 표시
        Army a = new Army(exitX, exitY);
        int maxDown = a.x;

        Queue<Army> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[R+1][C+1];
        q.offer(a);
        visit[a.x][a.y] = true;

        while(!q.isEmpty()) {
            Army now = q.poll();
            maxDown = Math.max(now.x, maxDown);

            for(int i=0; i<4; i++) {
                int nx = now.x + mx[i];
                int ny = now.y + my[i];
                if(isInside(nx, ny) && !visit[nx][ny]) {
                    if(exit[now.x][now.y] != 0) { //다른데로 이동 가능
                        if(map[nx][ny] != 0) {
                            visit[nx][ny] = true;
                            q.offer(new Army(nx, ny));
                        }
                    }
                    else { //하나의 골렘에서만 이동 가능
                        if(map[nx][ny] != 0 && (map[nx][ny] == map[now.x][now.y] )) {
                            visit[nx][ny] = true;
                            q.offer(new Army(nx, ny));
                        }
                    }
                }
            }
        }
        res += (maxDown - 3);
    }

    private static void GolemMove(Golem g) {
        Queue<Golem> q = new ArrayDeque<>();
        q.offer(g);
        Golem res = null;

        while(!q.isEmpty()) {
            Golem now = q.poll();
            res = now;
            if(down(now)) {
                q.offer(new Golem(now.idx, now.x+1, now.y, now.dir));
            }
            else if(left(now)) {
                int nDir = now.dir - 1;
                if(nDir == -1) {
                    nDir = 3;
                }
                q.offer(new Golem(now.idx, now.x+1, now.y-1, nDir));
            }
            else if(right(now)) {
                int nDir = now.dir + 1;
                if(nDir == 4) {
                    nDir = 0;
                }
                q.offer(new Golem(now.idx, now.x+1, now.y+1, nDir));
            }
        }

        if(res != null) {
            if(isAllIn(res)) {
                position(res);
                armyMove(res);
            }
            else {
                map = new int[R+1][C+1];
                exit = new int[R+1][C+1];
            }
        }
    }

    private static boolean down(Golem g) {
        int[] dx = {0, 1, 0};
        int[] dy = {-1, 0, 1};
        for(int i=0; i<3; i++) {
            int nx = g.x + dx[i];
            int ny = g.y + dy[i];
            if(!isInside(nx, ny) || map[nx][ny] != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean left(Golem g) {
        int[] dx = {-2, -1, 0, 0, 1};
        int[] dy = {-1, -2, -1, -2, -1};
        for(int i=0; i<5; i++) {
            int nx = g.x + dx[i];
            int ny = g.y + dy[i];
            if(!isInside(nx, ny) || map[nx][ny] != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean right(Golem g) {
        int[] dx = {-2, -1, 0, 0, 1};
        int[] dy = {1, 2, 1, 2, 1};
        for(int i=0; i<5; i++) {
            int nx = g.x + dx[i];
            int ny = g.y + dy[i];
            if(!isInside(nx, ny) || map[nx][ny] != 0) {
                return false;
            }
        }
        return true;
    }

    private static void position(Golem g) {
        int[] dx = {-1, -2, -1, 0, -1};
        int[] dy = {-1, 0, 0, 0, 1};
        for(int i=0; i<5; i++) {
            int nx = g.x + dx[i];
            int ny = g.y + dy[i];
            map[nx][ny] = g.idx;
        }
    }

    private static boolean isAllIn(Golem g) {
        int[] dx = {-1, -2, -1, 0, -1};
        int[] dy = {-1, 0, 0, 0, 1};
        for(int i=0; i<5; i++) {
            int nx = g.x + dx[i];
            int ny = g.y + dy[i];
            if(nx <= 3) {
                return false;
            }
        }
        return true;
    }
}
