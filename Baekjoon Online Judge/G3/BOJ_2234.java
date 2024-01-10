package solving.solve_0925;
//BOJ G3 2234 성곽

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2234 {
    public static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Room{
        int idx;
        int size;

        public Room(int idx, int size) {
            this.idx = idx;
            this.size = size;
        }
    }

    static int N; //세로
    static int M; //기로
    static int[][] map;
    static int[][] visit;
    static List<Room> rooms;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0}; //좌, 상, 우, 하
    static int roomCnt;
    static int originMaxSize;
    static int maxRoomSize;


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
        rooms = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        roomCnt = 0;
        originMaxSize = Integer.MIN_VALUE;
        maxRoomSize = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visit[i][j] == 0) {
                    roomCnt++;
                    bfs(i, j);

                }
            }
        }

        findMaxRoomSize();
        System.out.println(roomCnt);
        System.out.println(originMaxSize);
        System.out.println(maxRoomSize);
    }

    private static void bfs(int x, int y) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(x, y));
        visit[x][y] = roomCnt;
        int size = 1;

        while(!q.isEmpty()) {
            Pos now = q.poll();

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(!isInside(nx, ny)) {
                    continue;
                }
                if((map[now.x][now.y] & (1<<i)) == 0 && visit[nx][ny] == 0) {
                    visit[nx][ny] = roomCnt;
                    size++;
                    q.offer(new Pos(nx, ny));
                }
            }
        }
        originMaxSize = Math.max(originMaxSize, size);
        rooms.add(new Room(roomCnt, size));
    }

    private static void findMaxRoomSize() {
        for(int x=0; x<N; x++) {
            for(int y=0; y<M; y++) {
                for(int i=0; i<4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(!isInside(nx, ny)) {
                        continue;
                    }
                    if(visit[x][y] != visit[nx][ny]) { //붙어있는 방이 다른 방
                        int nextSize = rooms.get(visit[nx][ny]-1).size;
                        int nowSize = rooms.get(visit[x][y]-1).size;
                        maxRoomSize = Math.max(maxRoomSize, nextSize + nowSize);
                    }
                }
            }
        }
    }
}
