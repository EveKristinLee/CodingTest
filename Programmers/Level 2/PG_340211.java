//Programmers Level2 340211 충돌위험 찾기

import java.util.*;

public class PG_340211 {
    private static class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Pos>[] route; //각 로봇의 루트 기록

    public static int solution(int[][] points, int[][] routes) {
        int cnt = routes.length; //로봇의 개수
        robotMove(points, routes, cnt);
        return crash(cnt);
    }

    public static int crash(int cnt) {
        int res = 0;
        int finishCnt = 0;
        while(finishCnt != cnt) {
            int[][] map = new int[101][101];
            finishCnt = 0;

            for(int i=0; i<cnt; i++) {
                if(route[i].isEmpty()) {
                    finishCnt += 1;
                    continue;
                }

                Pos now = route[i].poll();
                map[now.x][now.y] += 1;
            }

            for(int i=1; i<101; i++) {
                for(int j=1; j<101; j++) {
                    if(map[i][j] > 1) { //충돌
                        res += 1;
                    }
                }
            }
        }
        return res;
    }

    private static void robotMove(int[][] points, int[][] routes, int cnt) {
        route = new Queue[cnt];
        for(int i=0; i<cnt; i++) {
            route[i] = new ArrayDeque<>();
            //각 로봇의 시작위치
            int pointNum = routes[i][0] -1;
            int x = points[pointNum][0];
            int y = points[pointNum][1];
            route[i].offer(new Pos(x, y));

            //경로 이동
            for(int j=1; j<routes[i].length; j++) {
                int startNum = routes[i][j-1] -1;
                int destNum = routes[i][j] -1;
                int sx = points[startNum][0];
                int sy = points[startNum][1];
                int dx = points[destNum][0];
                int dy = points[destNum][1];

                while(dx != sx) {
                    sx += (sx > dx) ? -1 : 1;
                    route[i].offer(new Pos(sx, sy));
                }

                while(dy != sy) {
                    sy += (sy > dy) ? -1 : 1;
                    route[i].offer(new Pos(sx, sy));
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes = {{4, 2}, {1, 3}, {2, 4}};
        System.out.println(solution(points, routes));
    }
}
