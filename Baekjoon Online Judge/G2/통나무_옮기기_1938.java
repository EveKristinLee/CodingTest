package solve_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

//BOJ G2
public class 통나무_옮기기_1938 {

    static class T {
        int x;
        int y;
        int cnt;
        int move; //상:1, 하:2, 좌:3, 우:4, 회전:5
        int dir; //가로:1, 세로:2

        public T(int x, int y, int cnt, int move, int dir) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.move = move;
            this.dir = dir;
        }
    }

    static int N;
    static char[][] map;
    static Queue<T> q;
    static T dest;
    static boolean[][][][] visit;

    static int[] dx = {0, -1, 1, 0, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1, 0};

    static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=N) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        q = new ArrayDeque<>();
        visit = new boolean[N][N][6][3];
        boolean isPushB = false;
        boolean isPushE = false;
        for(int i=0; i<N; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!isPushB && map[i][j] == 'B') {
                    isPushB = true;
                    if(isInside(i+1, j) && map[i+1][j] == 'B') {//세로
                        q.offer(new T(i+1, j, 0, 0, 2));
                        visit[i+1][j][0][2] = true;
                        for(int k=0; k<=2; k++) {
                            map[i+k][j] = '0';
                        }
                    }
                    else if (isInside(i, j+1) && map[i][j+1] == 'B'){ //가로
                        q.offer(new T(i, j+1, 0, 0, 1));
                        visit[i][j+1][0][1] = true;
                        for(int k=0; k<=2; k++) {
                            map[i][j+k] = '0';
                        }
                    }
                    break;
                }
                if(!isPushE && map[i][j] == 'E') {
                    isPushE = true;
                    if(isInside(i+1, j) && map[i+1][j] == 'E') {//세로
                        dest = new T(i+1, j, 0, 0, 2);
                        for(int k=0; k<=2; k++) {
                            map[i+k][j] = '0';
                        }
                    }
                    else if (isInside(i, j+1) && map[i][j+1] == 'E'){ //가로
                        dest = new T(i, j+1, 0, 0, 1);
                        for(int k=0; k<=2; k++) {
                            map[i][j+k] = '0';
                        }
                    }
                    break;
                }
            }
        }

        int result = bfs();
        if(result == -1) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }

    private static int bfs() {

        while(!q.isEmpty()) {
            T top = q.poll();
            if(top.x == dest.x && top.y == dest.y && top.dir == dest.dir) {
                return top.cnt;
            }

            for(int i=1; i<6; i++) {
                if(isMove(i, top)) {
                    int nx = top.x + dx[i];
                    int ny = top.y + dy[i];
                    if(i == 5) {
                        if(top.dir == 1) {
                            if (!visit[nx][ny][i][2]) {
                                visit[nx][ny][i][2] = true;
                                q.offer(new T(nx, ny, top.cnt+1, i, 2));
                            }
                        }else {
                            if (!visit[nx][ny][i][1]) {
                                visit[nx][ny][i][1] = true;
                                q.offer(new T(nx, ny, top.cnt+1, i, 1));
                            }
                        }
                    }else {
                        if(!visit[nx][ny][i][top.dir]) {
                            visit[nx][ny][i][top.dir] = true;
                            q.offer(new T(nx, ny, top.cnt+1, i, top.dir));
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isMove(int dir, T top) {
        if(dir == 1) { //상
            if(top.dir == 1) { //가로
                for(int i=-1; i<2; i++) {
                    if(!isInside(top.x-1, top.y + i) || map[top.x-1][top.y + i] != '0') {
                        return false;
                    }
                }
                return true;
            } else { //세로
                for(int i=-2; i<0; i++) {
                    if(!isInside(top.x+i, top.y) || map[top.x+i][top.y] != '0') {
                        return false;
                    }
                }
                return true;
            }
        }else if(dir == 2) { //하
            if(top.dir == 1) { //가로
                for(int i=-1; i<2; i++) {
                    if(!isInside(top.x+1, top.y + i) || map[top.x+1][top.y + i] != '0') {
                        return false;
                    }
                }
                return true;
            } else { //세로
                for(int i=1; i<3; i++) {
                    if(!isInside(top.x+i, top.y) || map[top.x+i][top.y] != '0') {
                        return false;
                    }
                }
                return true;
            }
        }else if(dir == 3) { //좌
            if(top.dir == 2) { //세로
                for(int i=-1; i<2; i++) {
                    if(!isInside(top.x+i, top.y-1) || map[top.x+i][top.y-1] != '0') {
                        return false;
                    }
                }
                return true;
            } else { //가로
                for(int i=-2; i<0; i++) {
                    if(!isInside(top.x, top.y+i) || map[top.x][top.y+i] != '0') {
                        return false;
                    }
                }
                return true;
            }
        }else if(dir == 4) { //우
            if(top.dir == 2) { //세로
                for(int i=-1; i<2; i++) {
                    if(!isInside(top.x+i, top.y+1) || map[top.x+i][top.y+1] != '0') {
                        return false;
                    }
                }
                return true;
            } else { //가로
                for(int i=1; i<3; i++) {
                    if(!isInside(top.x, top.y+i) || map[top.x][top.y+i] != '0') {
                        return false;
                    }
                }
                return true;
            }
        }else if(dir == 5) { //회전
            if(top.dir == 1) { //가로
                for(int i=-1; i<2; i++) {
                    if(!isInside(top.x-1, top.y + i) || map[top.x-1][top.y + i] != '0') {
                        return false;
                    }
                    if(!isInside(top.x+1, top.y + i) || map[top.x+1][top.y + i] != '0') {
                        return false;
                    }
                }
                return true;
            } else { //세로
                for(int i=-1; i<2; i++) {
                    if(!isInside(top.x+i, top.y-1) || map[top.x+i][top.y-1] != '0') {
                        return false;
                    }
                    if(!isInside(top.x+i, top.y+1) || map[top.x+i][top.y+1] != '0') {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
