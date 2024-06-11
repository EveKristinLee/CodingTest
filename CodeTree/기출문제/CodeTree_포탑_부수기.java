package 기출문제;
//CodeTree 포탑 부수기

import java.util.*;
import java.io.*;

public class CodeTree_포탑_부수기 {
    private static class Pos {
        int x;
        int y;
        int power;
        int time;
        int posSum;

        public Pos(int x, int y, int power, int time, int posSum) {
            this.x = x;
            this.y = y;
            this.power = power;
            this.time = time;
            this.posSum = posSum;
        }
    }

    static int N; //세로
    static int M; //가로
    static int K; //턴의 횟수
    static int turn;
    static int allCnt;
    static int[][] map;
    static boolean[][] isUsed;
    static int[][] isAttack; //공격한 포탄
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0}; //우, 하, 좌, 상

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        isAttack = new int[N+1][M+1];

        allCnt = 0;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0) {
                    allCnt += 1;
                }
            }
        }
        turn = 1;
        while(K-->0) {
            isUsed = new boolean[N+1][M+1];
            Pos weak = findWeak();
            if(weak == null) {
                break;
            }
            Pos strong = findStrong(weak.x, weak.y);
            if(strong == null) {
                break;
            }
            boolean success = laser(weak, strong);
            if(!success) { //레이저 공격 실패
                bomb(weak, strong);
            }
            if(allCnt <= 1) {
                break;
            }
            restore();
            turn+=1;
        }

        Pos res = findStrong(-1, -1);
        System.out.println(res.power);
    }

    private static Pos findWeak() {
        PriorityQueue<Pos> pq = new PriorityQueue<>(new Comparator<Pos>() {
            @Override
            public int compare(Pos p1, Pos p2) {
                if(p1.power == p2.power) {
                    if(p1.time == p2.time) {
                        if(p1.posSum == p2.posSum) {
                            return p2.y - p1.y; //열 값이 가장 큰
                        }
                        return p2.posSum - p1.posSum; //행 열 합이 가장 큰
                    }
                    return p2.time - p1.time; //가장 최근에 공격한
                }
                return p1.power - p2.power; //공격력이 가장 낮은
            }
        });
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(map[i][j] > 0) {
                    pq.offer(new Pos(i, j, map[i][j], isAttack[i][j], i+j));
                }
            }
        }

        Pos res = null;
        if(!pq.isEmpty()) {
            res = pq.poll();
        }
        return res;
    }

    private static Pos findStrong(int x, int y) {
        PriorityQueue<Pos> pq = new PriorityQueue<>(new Comparator<Pos>() {
            @Override
            public int compare(Pos p1, Pos p2) {
                if(p1.power == p2.power) {
                    if(p1.time == p2.time) {
                        if(p1.posSum == p2.posSum) {
                            return p1.y - p2.y; //열 값이 가장 작은
                        }
                        return p1.posSum - p2.posSum; //행 열 합이 가장 작은
                    }
                    return p1.time - p2.time; //공격한지 가장 오래된
                }
                return p2.power - p1.power; //공격력이 가장 높은
            }
        });
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(map[i][j] > 0) {
                    pq.offer(new Pos(i, j, map[i][j], isAttack[i][j], i+j));
                }
            }
        }

        Pos res = null;
        while(!pq.isEmpty()) {
            Pos now = pq.poll();
            if(now.x == x && now.y == y) { //약한 포탑과 같은 포탑
                continue;
            }
            else {
                res = now;
                break;
            }
        }
        return res;
    }

    private static boolean laser(Pos weak, Pos strong) {
        Queue<Pos> q = new ArrayDeque<>();
        int[][] visit = new int[N+1][M+1];
        Pos[][] parent = new Pos[N+1][M+1];

        q.offer(new Pos(weak.x, weak.y, weak.power, weak.time, weak.posSum));
        visit[weak.x][weak.y] = 1;
        boolean find = false;

        while(!q.isEmpty()) {
            Pos now = q.poll();
            if(now.x == strong.x && now.y == strong.y) {
                find = true;
                break;
            }

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx <= 0) {
                    nx = N;
                }
                else if(nx > N) {
                    nx = 1;
                }
                if(ny <= 0) {
                    ny = M;
                }
                else if(ny > M) {
                    ny = 1;
                }
                if(visit[nx][ny] == 0 && map[nx][ny] != 0) { //방문하지 않고, 포탑이 부서지지 않았다면
                    visit[nx][ny] = visit[now.x][now.y] + 1;
                    q.offer(new Pos(nx, ny, -1, -1, -1));
                    parent[nx][ny] = now;
                }
            }
        }

        if(find) { //최단 경로 있음
            isAttack[weak.x][weak.y] = turn;
            map[weak.x][weak.y] += N+M;
            isUsed[weak.x][weak.y] = true;

            int nowX = strong.x;
            int nowY = strong.y;
            while(nowX != weak.x || nowY != weak.y) {
                isUsed[nowX][nowY] = true;
                int nowPower = map[weak.x][weak.y];
                if(nowX == strong.x && nowY == strong.y) {
                    map[nowX][nowY] -= nowPower;
                }
                else {
                    map[nowX][nowY] -= (nowPower / 2);
                }

                if(map[nowX][nowY] <= 0) {
                    map[nowX][nowY] = 0;
                    allCnt-=1;
                }

                Pos next = parent[nowX][nowY];
                nowX = next.x;
                nowY = next.y;
            }

            return true;
        }
        return false;
    }

    private static void bomb(Pos weak, Pos strong) {
        int[] ex = {0, 0, 1, -1, 1, 1, -1, -1};
        int[] ey = {1, -1, 0, 0, -1, 1, -1, 1};
        int nowPower = weak.power + N + M;
        map[weak.x][weak.y] = nowPower;
        map[strong.x][strong.y] -= nowPower;
        isUsed[weak.x][weak.y] = true;
        isUsed[strong.x][strong.y] = true;
        isAttack[weak.x][weak.y] = turn;
        if(map[strong.x][strong.y] <= 0) {
            map[strong.x][strong.y] = 0;
            allCnt-=1;
        }

        for(int i=0; i<8; i++) {
            int nx = strong.x + ex[i];
            int ny = strong.y + ey[i];
            if(nx <= 0) {
                nx = N;
            }
            else if(nx > N) {
                nx = 1;
            }
            if(ny <= 0) {
                ny = M;
            }
            else if(ny > M) {
                ny = 1;
            }
            if(nx == weak.x && ny == weak.y) {
                continue;
            }
            if(map[nx][ny] == 0) {
                continue;
            }
            map[nx][ny] -= (nowPower / 2);
            isUsed[nx][ny] = true;
            if(map[nx][ny] <= 0) {
                map[nx][ny] = 0;
                allCnt -=1;
            }
        }
    }

    private static void restore() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(!isUsed[i][j] && map[i][j] != 0) {
                    map[i][j] += 1;
                }
            }
        }
    }
}
