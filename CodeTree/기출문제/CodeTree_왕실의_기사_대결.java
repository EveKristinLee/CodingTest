package 기출문제;

import java.util.*;
import java.io.*;

public class CodeTree_왕실의_기사_대결 {
    private static class Knight {
        int idx;
        int x;
        int y;
        int h;
        int w;
        int k;

        public Knight(int idx, int x, int y, int h, int w, int k) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
            this.k = k;
        }
    }

    static int L, N, Q;
    static int[][] map;
    static int[][] pos;
    static Map<Integer, Knight> knights;
    static boolean[] is_live;
    static int[] damage;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        map = new int[L+1][L+1];
        pos = new int[L+1][L+1];
        knights = new HashMap<>();
        is_live = new boolean[N+1];
        damage = new int[N+1];

        for(int i=1; i<=L; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=L; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int idx=1; idx<=N; idx++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for(int i=r; i<=(r+h-1); i++) {
                for(int j=c; j<=(c+w-1); j++) {
                    pos[i][j] = idx;
                }
            }
            knights.put(idx, new Knight(idx, r, c, h, w, k));
            is_live[idx] = true;
        }

        for(int q=1; q<=Q; q++) {

            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            order(i, d);
        }

        int res = 0;
        for(int i=1; i<=N; i++) {
            if(is_live[i]) {
                res += damage[i];
            }
        }
        System.out.println(res);
    }

    private static void order(int idx, int dir) {
        if(!is_live[idx]) { //체스판에서 사라진 기사
            return;
        }

        //기사 이동
        if(!canMove(idx, dir)) {
            return;
        }

        move(idx, dir, true);
    }

    private static void move(int idx, int dir, boolean push) {
        Knight knight = knights.get(idx);
        int nx = knight.x + dx[dir];
        int ny = knight.y + dy[dir];
        for(int i=knight.x; i<=knight.x+knight.h-1; i++) {
            for(int j=knight.y; j<=knight.y+knight.w-1; j++) {
                pos[i][j] = 0;
            }
        }

        for(int i=nx; i<=nx+knight.h-1; i++) {
            for(int j=ny; j<=ny+knight.w-1; j++) {
                if(pos[i][j] != 0 && pos[i][j] != idx) { //다른 기사 만나기
                    move(pos[i][j], dir, false);
                }
                pos[i][j] = idx;

                if(!push && map[i][j] == 1) {
                    damage[idx] += 1;
                }
            }
        }
        knights.put(idx, new Knight(idx, nx, ny, knight.h, knight.w, knight.k));
        if(damage[idx] >= knights.get(idx).k) {
            is_live[idx] = false;
            Knight now = knights.get(idx);
            for(int i=now.x; i<=now.x+now.h-1; i++) {
                for(int j=now.y; j<=now.y+now.w-1; j++) {
                    pos[i][j] = 0;
                }
            }
        }
    }

    private static boolean canMove(int idx, int dir) {
        boolean flag = true;
        Knight knight = knights.get(idx);
        int nx = knight.x + dx[dir];
        int ny = knight.y + dy[dir];
        for(int i=nx; i<=nx+knight.h-1; i++) {
            for(int j=ny; j<=ny+knight.w-1; j++) {
                if(!isInside(i, j)) { //맵 바깥
                    return false;
                }

                if(map[i][j] == 2) { //벽
                    return false;
                }

                if(pos[i][j] != 0 && pos[i][j] != idx) { //다른 기사 만나기
                    if(!canMove(pos[i][j], dir)) {
                        return false;
                    }
                }
            }
        }
        return flag;
    }

    private static boolean isInside(int x, int y) {
        if(x<=0||y<=0||x>L||y>L) {
            return false;
        }
        return true;
    }
}
