package solve_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 피리_부는_사나이_16724 {
    static int N; //세로
    static int M; //가로
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1}; //상, 하, 좌, 우
    static int[] p;
    static int len;
    static Set<Integer> uniP;

    private static void init() {
        p = new int[len];
        for(int i=0; i<len; i++) {
            p[i] = i;
        }
    }

    private static int find(int a) {
        if(p[a] == a) {
            return a;
        }
        return p[a] = find(p[a]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) {
            return false;
        }
        p[a] = b;
        return true;
    }

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=M) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        len = N*M;
        init();
        map = new char[N][M];
        visit = new boolean[N][M];
        for(int i=0; i<N; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!visit[i][j]) {
                    visit[i][j] = true;
                    findSafe(i, j);
                }
            }
        }

        uniP = new HashSet<>();
        for(int i=0; i<len; i++) {
            int pNow = find(i);
            uniP.add(pNow);
        }
        System.out.println(uniP.size());
    }

    private static void findSafe(int x, int y) {
        char now = map[x][y];
        int nowIdx = (x*M) + y;

        switch (now) {
            case 'U': //상
                int nx = x + dx[0];
                int ny = y + dy[0];
                int nIdx = (nx*M)+ny;
                if(union(nowIdx, nIdx) && !visit[nx][ny]){
                    visit[nx][ny] = true;
                    findSafe(nx, ny);
                }
                break;
            case 'D': //하
                nx = x + dx[1];
                ny = y + dy[1];
                nIdx = (nx*M)+ny;
                if(union(nowIdx, nIdx) && !visit[nx][ny]){
                    visit[nx][ny] = true;
                    findSafe(nx, ny);
                }
                break;
            case 'L': //좌
                nx = x + dx[2];
                ny = y + dy[2];
                nIdx = (nx*M)+ny;
                if(union(nowIdx, nIdx) && !visit[nx][ny]){
                    visit[nx][ny] = true;
                    findSafe(nx, ny);
                }
                break;
            case 'R': //우
                nx = x + dx[3];
                ny = y + dy[3];
                nIdx = (nx*M)+ny;
                if(union(nowIdx, nIdx) && !visit[nx][ny]){
                    visit[nx][ny] = true;
                    findSafe(nx, ny);
                }
                break;
        }
    }
}
