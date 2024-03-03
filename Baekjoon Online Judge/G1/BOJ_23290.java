package solving.solve_1005;
//BOJ G1 23290 마법사 상어와 복제

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_23290 {
    private static class Fish implements Cloneable{
        int x;
        int y;
        int dir;

        public Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        protected Fish clone() throws CloneNotSupportedException {
            return (Fish) super.clone();
        }
    }

    static int M; //물고기의 수
    static int S; //마법 연습 횟수
    static List<List<Fish>>[] map;
    static int[][] smell;
    static Fish shark;
    static int[] move;
    static int[] select;
    static int maxCnt;
    static int res;

    static int[] fDx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] fDy = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int[] sDx = {-1, 0, 1, 0};
    static int[] sDy = {0, -1, 0, 1}; //상, 좌, 하, 우

    private static boolean isInside(int x, int y) {
        if(x<=0 || y<=0 || x>4 || y>4) {
            return false;
        }
        return true;
    }

    private static boolean isShark(int x, int y) {
        if(shark.x == x && shark.y == y) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        smell = new int[5][5];
        map = new ArrayList[5];
        for(int i=0; i<5; i++) {
            map[i] = new ArrayList<>();
            for(int j=0; j<5; j++) {
                map[i].add(new ArrayList<>());
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            map[x].get(y).add(new Fish(x, y, dir-1));
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        shark = new Fish(x, y, 0);

        while(S-- >0) {
            magic();
        }
        cntFish();
        System.out.println(res);
    }

    private static void magic() throws CloneNotSupportedException {
        //물고기 이동을 위한 Queue 초기화
        Queue<Fish> q = new ArrayDeque<>();

        //현재 물고기 상태 저장
        Queue<Fish> originFish = new ArrayDeque<>();
        List<List<Fish>>[] nowMap = new ArrayList[5];
        for(int i=0; i<5; i++) {
            nowMap[i] = new ArrayList<>();
            for(int j=0; j<5; j++) {
                nowMap[i].add(new ArrayList<>());
                for(int k=0; k<map[i].get(j).size(); k++) {
                    Fish now = map[i].get(j).get(k);
                    originFish.offer(new Fish(now.x, now.y, now.dir));
                    q.offer(new Fish(now.x, now.y, now.dir));
                }
            }
        }

        //물고기 이동
        q = moveFish(q);
        while(!q.isEmpty()) {
            Fish now = q.poll();
            nowMap[now.x].get(now.y).add(new Fish(now.x, now.y, now.dir));
        }
        map = mapCopy(nowMap);

        //상어 이동
        moveShark();

        //물고기 복제
        while(!originFish.isEmpty()) {
            Fish now = originFish.poll();
            map[now.x].get(now.y).add(new Fish(now.x, now.y, now.dir));
        }

    }

    private static void cntFish() {
        res = 0;
        for(int i=1; i<5; i++) {
            for(int j=1; j<5; j++) {
                res+=map[i].get(j).size();
            }
        }
    }

    private static void moveShark() {
        move = new int[3];
        maxCnt = -1;
        select = new int[3];

        combi(0);

        //상어가 이동한 길의 물고기 없애기
        eatFish();
    }

    private static void combi(int idx) {
        if(idx == 3) {
            //상어가 물고기를 가장 많이 먹을 수 있는 이동 찾기
            int cnt = findRoad();
            if(maxCnt < cnt) {
                maxCnt = cnt;
                select = Arrays.copyOf(move, 3);
            }
            return;
        }
        for(int i=0; i<4; i++) {
            move[idx] = i;
            combi(idx+1);
        }
    }

    private static void eatFish() {
        int x = shark.x;
        int y = shark.y;
        for(int i=0; i<3; i++) {
            int nowDir = select[i];
            int nx = x + sDx[nowDir];
            int ny = y + sDy[nowDir];

            if(map[nx].get(ny).size() > 0) {
                smell[nx][ny] = S;
                map[nx].get(ny).clear();
            }
            x = nx;
            y = ny;
        }
        shark = new Fish(x, y, 0);
    }

    private static int findRoad() {
        boolean[][] visit = new boolean[5][5];
        int cnt = 0;
        int x = shark.x;
        int y = shark.y;
        for(int i=0; i<3; i++) {
            int nowDir = move[i];
            int nx = x + sDx[nowDir];
            int ny = y + sDy[nowDir];

            if(!isInside(nx, ny)) {
                return -1;
            }

            if(!visit[nx][ny] && map[nx].get(ny).size() > 0) {
                visit[nx][ny] = true;
                cnt += map[nx].get(ny).size();
            }
            x = nx;
            y = ny;
        }

        return cnt;
    }

    private static Queue<Fish> moveFish(Queue<Fish> q) {
        Queue<Fish> nowQ = new ArrayDeque<>();
        while(!q.isEmpty()) {
            Fish now = q.poll();
            boolean move = false;

            for(int i=0; i<8; i++) {
                int nowDir = ((now.dir-i)+8) % 8;
                int nx = now.x + fDx[nowDir];
                int ny = now.y + fDy[nowDir];
                if(!isInside(nx, ny) || isShark(nx, ny) || (smell[nx][ny] != 0 && Math.abs(S - smell[nx][ny]) <= 2)) {
                    continue;
                }
                move = true;
                nowQ.offer(new Fish(nx, ny, nowDir));
                break;
            }

            if(!move) {
                nowQ.offer(now);
            }
        }
        return nowQ;
    }

    private static List<List<Fish>>[] mapCopy(List<List<Fish>>[] map) throws CloneNotSupportedException {
        List<List<Fish>>[] resMap = new ArrayList[5];
        for(int i=0; i<5; i++) {
            resMap[i] = new ArrayList<>();
            for(int j=0; j<5; j++) {
                resMap[i].add(new ArrayList<>());
                for(int k=0; k<map[i].get(j).size(); k++) {
                    resMap[i].get(j).add(map[i].get(j).get(k).clone());
                }
            }
        }
        return resMap;
    }
}
