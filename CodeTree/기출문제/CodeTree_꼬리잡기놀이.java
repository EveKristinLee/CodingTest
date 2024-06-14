package 기출문제;
//CodeTree 꼬리잡기놀이

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree_꼬리잡기놀이 {
    static int N; //격자의 크기
    static int M; //팀의 개수
    static int K; //라운드 수
    static int round;
    static int ballDir;
    static int ballLine;
    static int[][] map;
    static int res;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0}; //우, 상, 좌, 하

    private static boolean isInside(int x, int y){
        if(x<=0 || y<=0 || x>N || y>N) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        round = 1;
        ballDir = 0;
        ballLine = 1;
        res = 0;
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int r=1; r<=K; r++) {
            //팀 움직이기
            move();
            //공 던지기
            throwBall();
            //다음 턴에 던질 공 방향, 선 구해놓기
            changeBall();
        }

        System.out.println(res);
    }

    private static void throwBall() {
        int nowX = -1;
        int nowY = -1;
        if(ballDir == 0 || ballDir == 2) { //y좌표 움직여서 확인
            nowX = ballLine;
            if(ballDir == 0) {
                nowY = 1;
            }
            else {
                nowY = N;
            }
            while(true) {
                if(map[nowX][nowY] != 4 && map[nowX][nowY] != 0) { //사람 만났어
                    break;
                }
                nowX += dx[ballDir];
                nowY += dy[ballDir];
                if(!isInside(nowX, nowY)) {
                    break;
                }
            }
        }
        else { //x좌표 움직여서 확인
            if(ballDir == 1) {
                nowX = N;
            }
            else {
                nowX = 1;
            }
            nowY = ballLine;
            while(true) {
                if(map[nowX][nowY] != 4 && map[nowX][nowY] != 0) { //사람 만났어
                    break;
                }
                nowX += dx[ballDir];
                nowY += dy[ballDir];
                if(!isInside(nowX, nowY)) {
                    break;
                }
            }
        }

        if(isInside(nowX, nowY)) { //만난 사람 계산
            int cnt = -1;
            if(map[nowX][nowY] == 1) {
                cnt = 1;
            }
            else {
                cnt = calcCnt(nowX, nowY);
            }
            if(cnt != -1) {
                res += (cnt * cnt);
            }


            //머리 사람 꼬리 사람 바꾸기
            int[] headTail = getHeadTail(nowX, nowY);
            int headX = headTail[0];
            int headY = headTail[1];
            int tailX = headTail[2];
            int tailY = headTail[3];
            map[tailX][tailY] = 1;
            map[headX][headY] = 3;
        }
    }

    private static int[] getHeadTail(int x, int y) {
        int[] res = new int[4];
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N+1][N+1];
        q.offer(new int[] {x, y});
        visit[x][y] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];
            if(map[nowX][nowY] == 1) {
                res[0] = nowX;
                res[1] = nowY;
            }
            if(map[nowX][nowY] == 3) {
                res[2] = nowX;
                res[3] = nowY;
            }

            for(int i=0; i<4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];
                if(!isInside(nx, ny)) {
                    continue;
                }
                if(!visit[nx][ny] && map[nx][ny] != 4 && map[nx][ny] != 0) {
                    visit[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                }
            }
        }
        return res;
    }

    private static int calcCnt(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N+1][N+1];
        q.offer(new int[] {x, y, 1});
        visit[x][y] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];
            int nowCnt = now[2];
            for(int i=0; i<4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];
                if(!isInside(nx, ny)) {
                    continue;
                }
                if(!visit[nx][ny] && map[nx][ny] == 2) {
                    visit[nx][ny] = true;
                    q.offer(new int[] {nx, ny, nowCnt + 1});
                }
                if(map[nowX][nowY] != 3 && map[nx][ny] == 1) {
                    return nowCnt + 1;
                }
            }
        }

        return -1;
    }

    private static void changeBall() {
        if(ballDir == 0) {
            ballLine += 1;
        }
        else if(ballDir == 1) {
            ballLine += 1;
        }
        else if(ballDir == 2) {
            ballLine -= 1;
        }
        else if(ballDir == 3) {
            ballLine -= 1;
        }

        if(ballLine == 0 || ballLine == N+1) { //방향 바꿀 타이밍
            ballDir = (ballDir + 1 + 4) % 4;
            if(ballDir == 0 || ballDir == 1) {
                ballLine = 1;
            }
            else if(ballDir == 2 || ballDir == 3) {
                ballLine = N;
            }
        }
    }

    private static void move() {
        boolean[][] visit = new boolean[N+1][N+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(!visit[i][j] && map[i][j] == 1) { //머리 사람
                    teamMove(i, j, visit);
                }
            }
        }
    }

    private static void teamMove(int x, int y, boolean[][] visit) {
        int moveX = -1;
        int moveY = -1;
        int tmpX = -1;
        int tmpY = -1;
        int tmpDestX = -1;
        int tmpDestY = -1;
        boolean findTwo = false;
        boolean findThree = false;
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(!isInside(nx, ny) || map[nx][ny] == 0) {
                continue;
            }
            if(!findTwo && map[nx][ny] == 2) {
                findTwo = true;
                tmpX = nx;
                tmpY = ny;
            }
            if(!findThree && map[nx][ny] == 3) {
                findThree = true;
                tmpDestX = nx;
                tmpDestY = ny;
            }
            if(map[nx][ny] == 4) {
                moveX = nx;
                moveY = ny;
                break;
            }
        }

        if(moveX != -1 && moveY != -1) { //경로에 4가 있음
            map[moveX][moveY] = 1;
            visit[moveX][moveY] = true;
            int beforeX = moveX;
            int beforeY = moveY;
            while(true) {
                beforeX = moveX;
                beforeY = moveY;
                for(int i=0; i<4; i++) {
                    int nx = moveX + dx[i];
                    int ny = moveY + dy[i];
                    if(!isInside(nx, ny) || map[nx][ny] == 4 || visit[nx][ny] || map[nx][ny] == 0) {
                        continue;
                    }
                    visit[nx][ny] = true;
                    moveX = nx;
                    moveY = ny;
                    break;
                }

                if(map[moveX][moveY] == 3) {
                    break;
                }
                map[moveX][moveY] = 2;
            }

            map[beforeX][beforeY] = 3;
            map[moveX][moveY] = 4;
        }
        else { //경로에 빈공간 없음
            int cDestX = -1;
            int cDestY = -1;
            for(int i=0; i<4; i++) {
                int nx = tmpDestX + dx[i];
                int ny = tmpDestY + dy[i];
                if(isInside(nx, ny) && map[nx][ny] == 2) {
                    cDestX = nx;
                    cDestY = ny;
                    break;
                }
            }
            map[cDestX][cDestY] = 3;
            map[tmpDestX][tmpDestY] = 1;
            map[x][y] = 2; //1이었던 위치
            visit[tmpDestX][tmpDestY] = true;

        }

    }

}
