package 기출문제;
//CodeTree 메이즈 러너

import java.util.*;
import java.io.*;

public class CodeTree_메이즈_러너 {

    private static class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Person{
        int idx;
        int x;
        int y;
        boolean out;
        public Person(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.out = false;
        }
    }

    static int N;
    static int M;
    static int K;
    static Pos exit;
    static int[][] map;
    static List<Integer>[][] pos;
    static List<Person> person;
    static int[] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1}; //상, 하, 좌, 우
    static final int INF = 987654321;

    private static boolean isInside(int x, int y) {
        if(x<=0 || y<=0 || x>N || y>N) {
            return false;
        }
        return true;
    }

    private static int calcDist(int x1, int y1, int x2, int y2) {
        return(Math.abs(x1-x2) + Math.abs(y1-y2));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        pos = new ArrayList[N+1][N+1];
        dist = new int[M+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                pos[i][j] = new ArrayList<>();
            }
        }
        person = new ArrayList<>();
        person.add(new Person(-1, -1, -1));
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            person.add(new Person(i, x, y));
            pos[x][y].add(i);
        }
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        exit = new Pos(x, y);

        for(int i=0; i<K; i++) {
            move();
            rotation();
        }

        int moveSum = 0;
        for(int i=1; i<=M; i++) {
            moveSum += dist[i];
        }
        System.out.println(moveSum);
        System.out.println(exit.x + " " + exit.y);
    }

    private static void move() {
        for(int i=1; i<=M; i++) {
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    if(a[0] == b[0]) {
                        return a[1] - b[1];
                    }
                    return a[0] - b[0];
                }
            });
            Person now = person.get(i);
            if(now.out) {
                continue;
            }
            int moveDir = -1;
            int moveDist = INF;
            for(int d=0; d<4; d++) {
                int nx = now.x+dx[d];
                int ny = now.y+dy[d];
                if(!isInside(nx, ny)) {
                    continue;
                }
                int nowDist = calcDist(exit.x, exit.y, nx, ny);
                if(nowDist <= moveDist) {
                    moveDist = nowDist;
                    moveDir = d;
                    pq.offer(new int[] {nowDist, d});
                }
            }

            boolean move = false;
            while(!pq.isEmpty()) {
                if(move) {
                    break;
                }
                int[] tmpDist = pq.poll();
                if(tmpDist[0] == moveDist) {
                    int moveX = now.x + dx[tmpDist[1]];
                    int moveY = now.y + dy[tmpDist[1]];
                    if(map[moveX][moveY] > 0) {
                        continue;
                    }

                    move = true;
                    moveDir = tmpDist[1];
                }
            }
            if(moveDir != -1) {
                //가장 짧은 이동 방향이 벽이 아니라면 이동
                int moveX = now.x + dx[moveDir];
                int moveY = now.y + dy[moveDir];
                if(map[moveX][moveY] > 0) {
                    continue;
                }

                List<Integer> tmp = pos[now.x][now.y];
                pos[now.x][now.y] = new ArrayList<>();
                for(int j=0; j<tmp.size(); j++) {
                    if(tmp.get(j) != now.idx) {
                        pos[now.x][now.y].add(tmp.get(j));
                    }
                }
                //출구를 만났는지 확인
                if(moveX != exit.x || moveY != exit.y) {
                    pos[moveX][moveY].add(now.idx);
                    person.get(now.idx).x = moveX;
                    person.get(now.idx).y = moveY;
                }
                else {
                    person.get(now.idx).out = true;
                }
                dist[now.idx]+=1;
            }
        }
    }

    private static void rotation() {
        //가장 작은 정사각형 구하기
        int rx = -1;
        int ry = -1;
        int d = -1;
        boolean find = false;
        for(int k=1; k<N; k++) { //변의 길이
            for(int i=1; i<=N-k; i++) {
                for(int j=1; j<=N-k; j++) {
                    if(isProfit(i, j, k)) {
                        rx = i;
                        ry = j;
                        d = k;
                        find = true;
                        break;
                    }
                }
                if(find) {
                    break;
                }
            }
            if(find) {
                break;
            }
        }

        if(rx != -1 && ry != -1) {
            rotate(rx, ry, d);
        }


    }

    private static void rotate(int x, int y, int d) {
        int[][] tmpMap = new int[x+d+1][y+d+1];
        int nx = -1;
        int ny = -1;
        List<Integer>[][] tmpPos = new ArrayList[x+d+1][y+d+1];
        for(int i=x; i<=x+d; i++) {
            for(int j=y; j<=y+d; j++) {
                tmpMap[i][j] = map[i][j];
                tmpPos[i][j] = new ArrayList<>();
                for(int k=0; k<pos[i][j].size(); k++) {
                    tmpPos[i][j].add(pos[i][j].get(k));
                }
                if(exit.x == i && exit.y == j) {
                    nx = i;
                    ny = j;
                }
            }
        }

        int idxY = y;
        for(int i=x; i<=x+d; i++) {
            int idxX = x+d;
            for(int j=y; j<=y+d; j++) {
                map[i][j] = tmpMap[idxX][idxY];
                pos[i][j] = tmpPos[idxX][idxY];
                if(nx == idxX && ny == idxY) { //출구 옮기기
                    exit = new Pos(i, j);
                }
                if(map[i][j] > 0) { //벽 내구도 깎기
                    map[i][j] -= 1;
                }
                if(pos[i][j].size() > 0) { //사람 위치 옮기기
                    for(int k=0; k<pos[i][j].size(); k++) {
                        person.get(pos[i][j].get(k)).x = i;
                        person.get(pos[i][j].get(k)).y = j;
                    }
                }
                idxX-=1;
            }
            idxY+=1;
        }
    }

    private static boolean isProfit(int x, int y, int d) {
        boolean isExit = false;
        boolean isPerson = false;
        for(int i=x; i<=x+d; i++) {
            for(int j=y; j<=y+d; j++) {
                if(exit.x == i && exit.y == j) {
                    isExit = true;
                }
                if(pos[i][j].size() > 0) {
                    for(int k=0; k<pos[i][j].size(); k++) {
                        Integer nowIdx = pos[i][j].get(k);
                        Person now = person.get(nowIdx);
                        if(!now.out) {
                            isPerson = true;
                            break;
                        }
                    }
                }
            }
            if(isExit && isPerson) {
                break;
            }
        }

        if(isExit && isPerson) {
            return true;
        }
        return false;
    }

}
