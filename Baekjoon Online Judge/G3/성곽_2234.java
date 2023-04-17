package solve_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class 성곽_2234 {
    static int N; //세로
    static int M; //가로
    static int[][] map;
    static int[][] visit;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};//서, 북, 동, 남
    static int idx;
    static int maxCnt;
    static int maxRoomCnt;
    static Map<Integer, Integer> rooms;

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=M) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new int[N][M];
        rooms = new HashMap<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        idx = 0;
        maxCnt = Integer.MIN_VALUE;
        maxRoomCnt = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visit[i][j] == 0) { //방문하지 않았으면
                    idx++;
                    bfs(i, j);
                }
            }
        }
        findMaxRoom();
        System.out.println(idx);
        System.out.println(maxCnt);
        System.out.println(maxRoomCnt);
    }

    private static void bfs(int x, int y) {
        Queue<Integer[]> q = new ArrayDeque<>();
        q.offer(new Integer[] {x, y});
        visit[x][y] = idx;
        int cnt = 1;

        while(!q.isEmpty()) {
            Integer[] top = q.poll();
            x = top[0];
            y = top[1];
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(isInside(nx, ny)) {
                    if((map[x][y] & (1 << i)) == 0 && visit[nx][ny] == 0) {
                        //벽이 없고, 방문하지 않았으면
                        cnt++;
                        visit[nx][ny] = idx;
                        q.offer(new Integer[] {nx, ny});
                    }
                }
            }
        }
        rooms.put(idx, cnt);
        maxCnt = Math.max(maxCnt, cnt);
    }

    private static void findMaxRoom() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                int nowRoom = visit[i][j];

                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(isInside(nx, ny)) {
                        if(visit[nx][ny] != nowRoom) {
                            maxRoomCnt = Math.max(maxRoomCnt, rooms.get(nowRoom) + rooms.get(visit[nx][ny]));
                        }
                    }
                }
            }
        }
    }
}
