package solving.solve_1005;
//BOJ G1 9328 열쇠

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_9328 {
    private static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int M;
    static char[][] map;
    static boolean[][] visit;
    static Set<Character> keys;
    static List<Pos> history;
    static int res;
    static Queue<Pos> q;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=M) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            visit = new boolean[N][M];
            q = new ArrayDeque<>();
            history = new ArrayList<>();

            for(int i=0; i<N; i++) {
                String s = br.readLine();
                map[i] = s.toCharArray();
            }

            keys = new HashSet<>();
            String key = br.readLine();
            if(!key.equals("0")) {
                for(int i=0; i<key.length(); i++) {
                    keys.add(Character.toUpperCase(key.charAt(i)));
                }
            }

            res = 0;
            enter();
            sb.append(res).append("\n");
        }
        System.out.print(sb);
    }

    private static void enter() {
        //가장자리만 입구
        for(int i=0; i<N; i++) {
            if(map[i][0] != '*' && !visit[i][0]) {
                stealDocument(i, 0);
            }
            if(map[i][M-1] != '*' && !visit[i][M-1]) {
                stealDocument(i, M-1);
            }
        }

        for(int i=0; i<M; i++) {
            if(map[0][i] != '*' && !visit[0][i]) {
                stealDocument(0, i);
            }
            if(map[N-1][i] != '*' && !visit[N-1][i]) {
                stealDocument(N-1, i);
            }
        }
    }

    private static void stealDocument(int x, int y) {
        q.offer(new Pos(x, y));
        visit[x][y] = true;

        if(map[x][y] == '$') { //바로 문서
            res++;
            map[x][y] = '.';
        }

        if(map[x][y] >= 'A' && map[x][y] <= 'Z') { //바로 문
            if(!keys.contains(map[x][y])) {
                visit[x][y] = false;
                //못연문 저장
                history.add(new Pos(x, y));
                return;
            }
        }

        if(map[x][y] >= 'a' && map[x][y] <= 'z') { //바로 열쇠
            keys.add(Character.toUpperCase(map[x][y]));
        }

        while(!q.isEmpty()) {
            Pos now = q.poll();
            if(map[now.x][now.y] >= 'A' && map[now.x][now.y] <= 'Z') {
                if(!keys.contains(map[now.x][now.y])) {
                    continue;
                }
            }
            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(!isInside(nx, ny)) {
                    continue;
                }
                if(!visit[nx][ny] && map[nx][ny] != '*') {
                    if(map[nx][ny] == '.') {
                        visit[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                    }
                    if(map[nx][ny] == '$') {
                        visit[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                        res++;
                    }
                    if(map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') {
                        if(keys.contains(map[nx][ny])) {
                            visit[nx][ny] = true;
                            q.offer(new Pos(nx, ny));
                        }
                        else{ //못연문 저장
                            history.add(new Pos(nx, ny));
                        }
                    }
                    if(map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {
                        keys.add(Character.toUpperCase(map[nx][ny]));
                        visit[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                        //못열었던 문 다시 체크
                        for(int j=0; j<history.size(); j++) {
                            q.offer(history.get(j));
                        }
                    }
                }
            }
        }
    }
}
