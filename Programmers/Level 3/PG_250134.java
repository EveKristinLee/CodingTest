//Programmers leve3 250134 [PCCP 기출 문제] 수레 움직이기

import java.util.*;

public class PG_250134 {
    private static class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N; //세로
    static int M; //가로
    static int[][] map;
    static boolean[][][] visit; //0 = 빨간 수레 , 1 = 파란 수레
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Pos redS;
    static Pos blueS;
    static boolean redEnd; //빨강 도착
    static boolean blueEnd; //파랑 도착
    static final int MAX = 987654321;

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=M) {
            return false;
        }
        return true;
    }

    private static boolean isPossible(Pos curR, Pos nextR, Pos curB, Pos nextB) {
        //이동한 위치가 벽
        if(map[nextR.x][nextR.y] == 5 || map[nextB.x][nextB.y] == 5) {
            return false;
        }

        //두 수레끼리 자리를 바꾸며 움직일 수 없음
        if((curR.x == nextB.x && curR.y == nextB.y)
                && (curB.x == nextR.x && curB.y == nextR.y)) {
            return false;
        }

        //같은 칸에 동시에 있을 수 없음
        if(nextR.x == nextB.x && nextR.y == nextB.y) {
            return false;
        }

        //도착하지 않고, 이동방향이 중복일때
        if((!redEnd && visit[nextR.x][nextR.y][0])
                || (!blueEnd && visit[nextB.x][nextB.y][1])) {
            return false;
        }

        return true;
    }

    public static int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        map = new int[N][M];
        map = maze;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 1) {
                    redS = new Pos(i, j);
                }
                else if(map[i][j] == 2) {
                    blueS = new Pos(i, j);
                }
            }
        }

        visit = new boolean[N][M][2];
        redEnd = false;
        blueEnd = false;
        visit[redS.x][redS.y][0] = true;
        visit[blueS.x][blueS.y][1] = true;
        int answer = move(redS, blueS, 0);
        return (answer == MAX) ? 0 : answer;
    }

    private static int move(Pos red, Pos blue, int cnt) {
        if(redEnd && blueEnd) { //둘다 도착
            return cnt;
        }

        int nowCnt = MAX;

        for(int dirR=0; dirR<4; dirR++) {
            for(int dirB=0; dirB<4; dirB++) {
                int nextRx = red.x + dx[dirR];
                int nextRy = red.y + dy[dirR];
                int nextBx = blue.x + dx[dirB];
                int nextBy = blue.y + dy[dirB];
                if(!isInside(nextRx, nextRy) || !isInside(nextBx, nextBy)) {
                    continue;
                }
                Pos nextR = new Pos(nextRx, nextRy);
                Pos nextB = new Pos(nextBx, nextBy);
                //도착지점에 이미 도착했으면 움직이지 않음
                if(redEnd) {
                    nextR = red;
                }
                if(blueEnd) {
                    nextB = blue;
                }

                if(!isPossible(red, nextR, blue, nextB)) {
                    continue;
                }

                visit[nextRx][nextRy][0] = true;
                visit[nextBx][nextBy][1] = true;
                if(map[nextRx][nextRy] == 3) {
                    redEnd = true;
                }
                if(map[nextBx][nextBy] == 4) {
                    blueEnd = true;
                }

                nowCnt = Math.min(nowCnt, move(nextR, nextB, cnt+1));

                redEnd = false;
                blueEnd = false;
                visit[nextRx][nextRy][0] = false;
                visit[nextBx][nextBy][1] = false;
            }
        }

        return nowCnt;
    }

    public static void main(String[] args) {
        int[][] maze = {{1, 4}, {0, 0}, {2, 3}};
        int res = solution(maze);
        System.out.println(res);
    }
}
