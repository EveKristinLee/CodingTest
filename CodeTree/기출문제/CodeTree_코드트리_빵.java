package 기출문제;
//CodeTree 코드트리 빵

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree_코드트리_빵 {
    private static class Pos {
        int x;
        int y;
        public Pos (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N; //격자의 크기
    static int M; //사람의 수
    static int[][] map;
    static List<Integer>[][] route;
    static Map<Integer, Pos> store;
    static Map<Integer, Pos> camp;
    static Map<Integer, Pos> person;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0}; //상, 좌, 우, 하
    static final int INF = 987654321;
    static int time;
    static boolean[] destination;

    private static boolean isInside(int x, int y) {
        if(x<=0 || y<=0 || x>N || y>N) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        route = new ArrayList[N+1][N+1];
        store = new HashMap<>();
        camp = new HashMap<>();
        person = new HashMap<>();
        destination = new boolean[M+1];
        int campIdx = 1;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    camp.put(campIdx++, new Pos(i, j));
                }
                route[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            store.put(i+1, new Pos(x, y));
        }

        time = 1;
        while(true) {
            if(isAllDest()) {
                break;
            }
            //1. 사람 이동시키기
            if(time <= M) {
                for(int i=1; i<time; i++) { //현재 맵에 나와있는 사람만 이동시키기
                    if(!destination[i]) {
                        move(i);
                    }
                }
            }
            else { //모든 사람이 map에 나와있음
                for(int i=1; i<=M; i++) { //현재 맵에 나와있는 사람만 이동시키기
                    if(!destination[i]) {
                        move(i);
                    }
                }
            }

            //2. 편의점에 도착했다면 편의점에서 멈추게 하고, 해당 편의점을 지나갈 수 없게 만든다.
            for(int i=1; i<=M; i++) {
                if(destination[i]) {
                    Pos nowP = person.get(i);
                    map[nowP.x][nowP.y] = -1;
                }
            }

            //3. 베이스캠프에 들어가기
            if(time <= M) {
                //현재 time번째 사람이 베이스캠프를 찾아서 들어간다.
                int nowCampIdx = findNearCamp(time);
                Pos nowCamp = camp.get(nowCampIdx);
                route[nowCamp.x][nowCamp.y].add(time);
                person.put(time, new Pos(nowCamp.x, nowCamp.y));
                map[nowCamp.x][nowCamp.y] = -1; //사람이 지나간 베이스캠프는 지나갈 수 없다.
            }
            time++;
        }

        System.out.println(time -1); //마지막 추가된 시간 빼주기
    }

    private static void move(int idx) {
        Pos[][] parent = new Pos[N+1][N+1];
        boolean[][] visit = new boolean[N+1][N+1];
        Queue<int[]> q = new ArrayDeque<>();
        //사람의 현재 위치
        Pos p = person.get(idx);
        //도착해야할 편의점의 위치
        Pos d = store.get(idx);

        q.offer(new int[] {p.x, p.y, 0});
        visit[p.x][p.y] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            if(now[0] == d.x && now[1] == d.y) {
                break;
            }

            for(int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(!isInside(nx, ny)) {
                    continue;
                }

                if(!visit[nx][ny] && map[nx][ny] != -1) {
                    q.offer(new int[] {nx, ny, now[2]+1});
                    visit[nx][ny] = true;
                    parent[nx][ny] = new Pos(now[0], now[1]);
                }
            }
        }

        int nowX = d.x;
        int nowY = d.y;
        int bx = d.x;
        int by = d.y;
        while(nowX != p.x || nowY != p.y) {
            bx = nowX;
            by = nowY;
            Pos before = parent[nowX][nowY];
            nowX = before.x;
            nowY = before.y;
        }

        //사람 이동
        person.put(idx, new Pos(bx, by));
        List<Integer> bef = route[p.x][p.y];
        route[p.x][p.y] = new ArrayList<>();
        for(int i=0; i<bef.size(); i++) {
            if(bef.get(i) != idx) {
                route[p.x][p.y].add(bef.get(i));
            }
        }
        route[bx][by].add(idx);

        //현재 사람이 편의점에 도착했는지 확인하기
        if(bx == d.x && by == d.y) {
            destination[idx] = true;
        }
    }

    private static int findNearCamp(int idx) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) { //거리가 같으면
                    if(o1[1] == o2[1]) { //x좌표가 같으면
                        return o1[2] - o2[2];
                    }
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        }); //거리, x, y, idx
        for (int i = 1; i <= camp.size(); i++) {
            Pos nowCamp = camp.get(i);
            //현재 베이스캠프가 닫히지 않았으면
            if(map[nowCamp.x][nowCamp.y] == -1) {
                continue;
            }
            int nowDist = calcRoute(nowCamp, store.get(idx));
            if(nowDist != -1) {
                pq.offer(new int[] {nowDist, nowCamp.x, nowCamp.y, i});
            }
        }

        int nearCampIdx = -1;
        if(!pq.isEmpty()) {
            int[] near = pq.poll();
            nearCampIdx = near[3];
        }
        return nearCampIdx;
    }

    private static int calcRoute(Pos start, Pos end) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N+1][N+1];
        q.offer(new int[] {start.x, start.y, 0});
        visit[start.x][start.y] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            if(now[0] == end.x && now[1] == end.y) {
                return now[2];
            }

            for(int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(!isInside(nx, ny)) {
                    continue;
                }
                if(!visit[nx][ny] && map[nx][ny] != -1) {
                    visit[nx][ny] = true;
                    q.offer(new int[] {nx, ny, now[2] + 1});
                }
            }
        }
        return -1;
    }

    private static boolean isAllDest() {
        for(int i=1; i<=M; i++) {
            if(!destination[i]) {
                return false;
            }
        }
        return true;
    }
}
