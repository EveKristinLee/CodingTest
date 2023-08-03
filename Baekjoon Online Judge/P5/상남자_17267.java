//BOJ P5
package solving.solve_1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 상남자_17267 {
    static int N;
    static int M;
    static int L;
    static int R;
    static char[][] map;
    static boolean[][] visit;
    static Man man;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1}; //상, 하, 좌, 우

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=M) {
            return false;
        }
        return true;
    }

    private static class Man implements Comparable<Man>{
        int x;
        int y;
        int l;
        int r;

        public Man(int x, int y, int l, int r) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Man m) {
            return (m.l + m.r) - (this.l + this.r);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[N][M];
        for(int i=0; i<N; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
            for(int j=0; j<M; j++) {
                if(map[i][j] == '2'){
                    man = new Man(i, j, L, R);
                }
            }
        }
        move();

        int res = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visit[i][j]) res++;
            }
        }
        System.out.println(res);
    }

    private static void move() {
        PriorityQueue<Man> q = new PriorityQueue<>();
        q.offer(man);
        visit[man.x][man.y] = true;

        while(!q.isEmpty()) {
            Man now = q.poll();
            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                boolean move = false;
                if(isInside(nx, ny)) {
                    if(!visit[nx][ny] && map[nx][ny] == '0') {
                        if(i==2 && now.l > 0) { //좌
                            move = true;
                            q.offer(new Man(nx, ny, now.l-1, now.r));
                        }
                        else if(i==3 && now.r > 0) { //우
                            move = true;
                            q.offer(new Man(nx, ny, now.l, now.r-1));
                        }
                        else if (i < 2) { //상 하
                            move = true;
                            q.offer(new Man(nx, ny, now.l, now.r));
                        }
                        if(move) {
                            visit[nx][ny] = true;
                        }
                    }
                }
            }
        }
    }
}
