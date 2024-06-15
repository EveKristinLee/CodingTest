package 기출문제;
//CodeTree 술래 잡기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CodeTree_술래_잡기 {
    private static class Person {
        int idx;
        int x;
        int y;
        int dir;

        public Person(int idx, int x, int y,int dir) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static int N; //격자의 크기
    static int M; //도망자의 수
    static int H; //나무의 수
    static int K; //라운드의 수
    static int round;
    static int[][] tree;
    static boolean[] isCatch;
    static List<Person>[][] runner; //도망자
    static List<Person> runnerList;
    static Person catcher; //술래
    static boolean in;
    static int[][] catcherPosIn;
    static int[][] catcherPosOut;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1}; //상, 우, 하, 좌

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
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        runner = new ArrayList[N+1][N+1];
        runnerList = new ArrayList<>();
        runnerList.add(new Person(-1, -1, -1, -1));
        tree = new int[N+1][N+1];
        in = false; //처음에는 나가는 방향
        round = 1;
        isCatch = new boolean[M+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                runner[i][j] = new ArrayList<>();
            }
        }
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());
            int rt = Integer.parseInt(st.nextToken());
            int rd = -1;
            if(rt == 1) {
                rd = 1;
            }
            else if(rt == 2) {
                rd = 2;
            }
            if(rd != -1) {
                runner[rx][ry].add(new Person(i, rx, ry, rd));
                runnerList.add(new Person(i, rx, ry, rd));
            }
        }
        for(int i=0; i<H; i++) {
            st = new StringTokenizer(br.readLine());
            int hx = Integer.parseInt(st.nextToken());
            int hy = Integer.parseInt(st.nextToken());
            tree[hx][hy] = 1;
        }

        catcher = new Person(0, (N/2)+1, (N/2)+1, 0);
        catcherPosIn = new int[N+1][N+1];
        catcherPosOut = new int[N+1][N+1];
        spiralIn();
        spiralOut();

        int res = 0;
        for(int k=0; k<K; k++) {
            runnerMove();
            catcherMove();
            res += catchRunner();
            round+=1;
        }
        System.out.println(res);
    }

    private static int catchRunner() {
        int score = 0;
        int catcherDir = catcher.dir;
        int nowX = catcher.x;
        int nowY = catcher.y;
        for(int i=0; i<3; i++) {
            int nx = nowX + (dx[catcherDir] * i);
            int ny = nowY + (dy[catcherDir] * i);

            if(isInside(nx, ny)) {
                if(runner[nx][ny].size() > 0) {
                    if(tree[nx][ny] != 1) { //나무에 가려지지 않음
                        score += (round * runner[nx][ny].size());
                        for(int k=0; k<runner[nx][ny].size(); k++) {
                            isCatch[runner[nx][ny].get(k).idx] = true;
                        }
                        runner[nx][ny] = new ArrayList<>();
                    }
                }
            }
        }

        return score;
    }

    private static void runnerMove() {
        for(int i=1; i<=M; i++) {
            Person nowRun = runnerList.get(i);
            if(isCatch[nowRun.idx]) {
                continue;
            }
            int nowDist = calcDist(i);
            if(nowDist <= 3) { //술래와의 거리가 3이하인 도망자들만 움직일 수 있음
                int nowD = nowRun.dir;
                int nx = nowRun.x + dx[nowD];
                int ny = nowRun.y + dy[nowD];
                if(isInside(nx, ny)) { //격자를 벗어나지 않을때
                    if(catcher.x != nx || catcher.y != ny) { //술래와 겹치지 않는다면
                        List<Person> bef = runner[nowRun.x][nowRun.y];
                        runner[nowRun.x][nowRun.y] = new ArrayList<>();
                        for(int s=0; s<bef.size(); s++) {
                            if(bef.get(s).idx != nowRun.idx) {
                                runner[nowRun.x][nowRun.y].add(bef.get(s));
                            }
                        }

                        runner[nx][ny].add(new Person(nowRun.idx, nx, ny, nowD));
                        runnerList.get(nowRun.idx).x = nx;
                        runnerList.get(nowRun.idx).y = ny;
                    }
                }
                else { //격자를 벗어날 때
                    int newD = (nowD + 2 + 4) % 4;
                    nx = nowRun.x + dx[newD];
                    ny = nowRun.y + dy[newD];
                    if(isInside(nx, ny)) {
                        if(catcher.x != nx || catcher.y != ny) { //술래가 없다면 한칸앞으로 이동
                            List<Person> bef = runner[nowRun.x][nowRun.y];
                            runner[nowRun.x][nowRun.y] = new ArrayList<>();
                            for(int s=0; s<bef.size(); s++) {
                                if(bef.get(s).idx != nowRun.idx) {
                                    runner[nowRun.x][nowRun.y].add(bef.get(s));
                                }
                            }
                            runner[nx][ny].add(new Person(nowRun.idx, nx, ny, newD));
                            runnerList.get(nowRun.idx).x = nx;
                            runnerList.get(nowRun.idx).y = ny;
                            runnerList.get(nowRun.idx).dir = newD;
                        }
                    }
                }
            }
        }
    }

    private static int calcDist(int idx) {
        int x = catcher.x;
        int y = catcher.y;
        Person run = runnerList.get(idx);
        return Math.abs(x - run.x) + Math.abs(y - run.y);
    }

    private static void catcherMove() {
        if(in) { //안으로 들어가기
            int nowX = catcher.x;
            int nowY = catcher.y;
            int nowDir = catcherPosIn[nowX][nowY];
            nowX += dx[nowDir];
            nowY += dy[nowDir];
            int nextDir = catcherPosIn[nowX][nowY];
            catcher = new Person(0, nowX, nowY, nextDir);
            //마지막 위치
            if(nowX == (N/2) + 1 && nowY == (N/2) + 1) {
                in = false;
                int cDir = catcherPosOut[nowX][nowY];
                catcher = new Person(0, nowX, nowY, cDir);
            }
        }
        else {
            int nowX = catcher.x;
            int nowY = catcher.y;
            int nowDir = catcherPosOut[nowX][nowY];
            nowX += dx[nowDir];
            nowY += dy[nowDir];
            int nextDir = catcherPosOut[nowX][nowY];
            catcher = new Person(0, nowX, nowY,  nextDir);
            //마지막 위치
            if(nowX == 1 && nowY == 1) {
                in = true;
                int cDir = catcherPosIn[nowX][nowY];
                catcher = new Person(0, nowX, nowY, cDir);
            }
        }
    }

    private static void spiralIn() {
        int x = 1;
        int y = 1;
        int height = N;
        int width = N-1;
        int num = 1;
        int end = N * N;

        while(num <= end) {
            for(int i=0; i<height; i++) {
                catcherPosIn[x][y] = 2;
                num++;
                if(i == height-1) {
                    catcherPosIn[x][y] = 1;
                }
                x++;
            }
            x--;
            y++;
            height--;
            for(int i=0; i<width; i++) {
                catcherPosIn[x][y] = 1;
                num++;
                if(i == width-1) {
                    catcherPosIn[x][y] = 0;
                }
                y++;
            }
            x--;
            y--;
            width--;


            for(int i=0; i<height; i++) {
                catcherPosIn[x][y] = 0;
                num++;
                if(i == height-1) {
                    catcherPosIn[x][y] = 3;
                }
                x--;
            }
            x++;
            y--;
            height--;

            for(int i=0; i<width; i++) {
                catcherPosIn[x][y] = 3;
                num++;
                if(i == width-1) {
                    catcherPosIn[x][y] = 2;
                }
                y--;
            }
            x++;
            y++;
            width--;
        }
    }

    private static void spiralOut() {
        int x = (N/2) + 1;
        int y = (N/2) + 1;
        int n = 1;
        int limit = 1;

        while(true) {
            for(int i=0; i<limit; i++) {
                catcherPosOut[x][y] = 0;
                n++;
                x--;
            }
            if(n-1 == (N * N)) {
                break;
            }

            for(int i=0; i<limit; i++) {
                catcherPosOut[x][y] = 1;
                n++;
                y++;
            }
            limit++;

            for(int i=0; i<limit; i++) {
                catcherPosOut[x][y] = 2;
                n++;
                x++;
            }

            for(int i=0; i<limit; i++) {
                catcherPosOut[x][y] = 3;
                n++;
                y--;
            }
            limit++;
        }
    }
}
