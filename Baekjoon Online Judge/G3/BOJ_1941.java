package solving.solve_0925;
//BOJ G3 1941 소문난 칠공주

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

//1. 25개 중에 7개의 자리 뽑기
//2. 7개의 자리가 상하좌우로 연결되어 있는지 확인
//3. S가 Y보다 많은지 확인

public class BOJ_1941 {
    static char[][] map;
    static int[] posX;
    static int[] posY;
    static int[] select;
    static int res;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=5 || y>=5) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        posX = new int[25];
        posY = new int[25];
        for(int i=0; i<5; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
        }

        for(int i=0; i<25; i++) {
            posX[i] = i/5;
            posY[i] = i%5;
        }

        res = 0;
        select = new int[7];
        comb(0, 0);

        System.out.println(res);
    }

    private static void comb(int cnt, int idx) {
        if(cnt == 7) {
            if(bfs()) {
                res++;
            }
            return;
        }

        for(int i=idx; i<25; i++) {
            select[cnt] = i;
            comb(cnt+1, i+1);
        }
    }

    private static boolean bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[7];
        q.offer(select[0]);
        visit[0] = true;
        int total = 0;
        int cntS = 0;
        int cntY = 0;

        while(!q.isEmpty()) {
            Integer now = q.poll();
            int x = posX[now];
            int y = posY[now];
            total++;
            if(map[x][y] == 'S') {
                cntS++;
            }
            else {
                cntY++;
            }

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(!isInside(nx, ny)) {
                    continue;
                }
                int pos = (nx * 5) + ny;
                for(int j=0; j<7; j++) {
                    if(select[j] == pos && !visit[j]) {
                        visit[j] = true;
                        q.offer(pos);
                    }
                }
            }
        }

        if(total == 7) {
            if(cntS > cntY) {
                return true;
            }
        }
        return false;
    }
}
