import java.util.*;

public class 미로_탈출 {
    public static class Pos {
        int x;
        int y;
        int cnt;
        Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static int N;
    static int M;
    static char[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Pos start;
    static Pos end;
    static Pos lever;

    private static boolean isInside(int x, int y) {
        if(x<0||y<0||x>=N||y>=M) {
            return false;
        }
        return true;
    }

    public static int solution(String[] maps) {
        int result = 0;
        N = maps.length;
        M = maps[0].length();
        map = new char[N][M];
        for(int i=0; i<N; i++) {
            map[i] = maps[i].toCharArray();
            for(int j=0; j<M; j++) {
                if(map[i][j] == 'S') {
                    start = new Pos(i, j, 0);
                }
                else if(map[i][j] == 'L') {
                    lever = new Pos(i, j, 0);
                }
                else if(map[i][j] == 'E') {
                    end = new Pos(i, j, 0);
                }
            }
        }
        int toLever = bfs(start, lever);
        int toExit = bfs(lever, end);
        if(toLever>0 && toExit>0) {
            result = toLever + toExit;
        }
        else {
            result = -1;
        }
        return result;
    }

    private static int bfs(Pos from, Pos to) {
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];
        q.offer(from);
        visit[from.x][from.y] = true;

        while(!q.isEmpty()) {
            Pos top = q.poll();

            if(top.x == to.x && top.y == to.y) {
                //도착
                return top.cnt;
            }

            for(int i=0; i<4; i++) {
                int nx = top.x + dx[i];
                int ny = top.y + dy[i];
                if(isInside(nx, ny)) {
                    if(map[nx][ny] != 'X' && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        q.offer(new Pos(nx, ny, top.cnt+1));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        System.out.println(solution(maps));
    }

}