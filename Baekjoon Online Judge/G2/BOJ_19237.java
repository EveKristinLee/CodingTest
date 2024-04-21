package solving_2.solve_12.solve_19;
//BOJ G2 19237 어른 상어

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_19237 {
    private static class Shark implements Comparable<Shark>{
        int idx;
        int x;
        int y;
        int dir;
        int[] up;
        int[] down;
        int[] left;
        int[] right;

        public Shark(int idx, int x, int y, int dir) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.up = new int[4];
            this.down = new int[4];
            this.left = new int[4];
            this.right = new int[4];
        }

        public int compareTo(Shark s) {
            return this.idx - s.idx;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Shark shark = (Shark) o;
            return (x == shark.x) && (y == shark.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    static int N; //격자의 크기
    static int M; //상어의 수
    static int k; //냄새가 유지되는 시간
    static int[][][] map; // x, y, k = 상어 idx;
    static List<Shark> shark;

    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=N) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[N][N][10_001];
        shark = new ArrayList<>();
        //map 입력
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j][1] = n;
                if(n != 0) {
                    shark.add(new Shark(n, i, j, -1));
                }
            }
        }
        //상어 방향 입력
        Collections.sort(shark);
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            shark.get(i).dir = Integer.parseInt(st.nextToken());
        }

        //상어의 방향 우선순위 입력
        for(int i=0; i<M; i++) {
            for(int j=0; j<4; j++) {
                //위, 아래, 왼쪽, 오른쪽
                st = new StringTokenizer(br.readLine());
                if(j == 0) {
                    for(int k=0; k<4; k++) {
                        shark.get(i).up[k] = Integer.parseInt(st.nextToken());
                    }
                }
                if(j == 1) {
                    for(int k=0; k<4; k++) {
                        shark.get(i).down[k] = Integer.parseInt(st.nextToken());
                    }
                }
                if(j == 2) {
                    for(int k=0; k<4; k++) {
                        shark.get(i).left[k] = Integer.parseInt(st.nextToken());
                    }
                }
                if(j == 3) {
                    for(int k=0; k<4; k++) {
                        shark.get(i).right[k] = Integer.parseInt(st.nextToken());
                    }
                }
            }
        }
        System.out.println(move());
    }

    private static int move() {
        Queue<Shark> q = new ArrayDeque<>();
        Queue<Shark> nq = new ArrayDeque<>();
        for(int i=0; i<M; i++) {
            q.offer(shark.get(i));
        }
        int turn = 2;
        boolean one = false;

        while(true) {
            if(q.size() == 1 && q.peek().idx == 1) { //1번 상어만 격자에 남기
                one = true;
                break;
            }
            if(turn > 1_001) {
                break;
            }

            int size = q.size();
            for(int i=0; i<size; i++) {
                Shark now = q.poll();
                boolean move = false;
                if(now.dir == 1) { // up
                    for(int d=0; d<4; d++) {
                        int nowDir = shark.get(now.idx-1).up[d];
                        int nx = now.x + dx[nowDir];
                        int ny = now.y + dy[nowDir];
                        if(!isInside(nx, ny)) {
                            continue;
                        }
                        boolean ok = true;
                        for(int j=2; j<=k; j++) {
                            if(turn - j >= 1 && map[nx][ny][turn-j] != 0) {
                                ok = false;
                                break;
                            }
                        }
                        if(ok) { //움직이기 가능
                            map[nx][ny][turn] = now.idx;
                            nq.offer(new Shark(now.idx, nx, ny, nowDir));
                            move = true;
                            break;
                        }
                    }
                    if(!move) { //못 움직였어
                        //이전의 위치로 이동
                        boolean end = false;
                        for(int d=0; d<4; d++) {
                            int nowDir = shark.get(now.idx-1).up[d];
                            int nx = now.x + dx[nowDir];
                            int ny = now.y + dy[nowDir];
                            if(!isInside(nx, ny)) {
                                continue;
                            }
                            for(int j=2; j<=k; j++) {
                                if(turn - j >= 1 && map[nx][ny][turn-j] == now.idx) {
                                    map[nx][ny][turn] = now.idx;
                                    nq.offer(new Shark(now.idx, nx, ny, nowDir));
                                    end = true;
                                    break;
                                }
                            }
                            if(end) {
                                break;
                            }
                        }
                    }
                }
                else if(now.dir == 2) { //down
                    for(int d=0; d<4; d++) {
                        int nowDir = shark.get(now.idx -1).down[d];
                        int nx = now.x + dx[nowDir];
                        int ny = now.y + dy[nowDir];
                        if(!isInside(nx, ny)) {
                            continue;
                        }
                        boolean ok = true;
                        for(int j=2; j<=k; j++) {
                            if(turn - j >= 1 && map[nx][ny][turn-j] != 0) {
                                ok = false;
                                break;
                            }
                        }
                        if(ok) { //움직이기 가능
                            map[nx][ny][turn] = now.idx;
                            nq.offer(new Shark(now.idx, nx, ny, nowDir));
                            move = true;
                            break;
                        }
                    }
                    if(!move) { //못 움직였어
                        //이전의 위치로 이동
                        boolean end = false;
                        for(int d=0; d<4; d++) {
                            int nowDir = shark.get(now.idx-1).down[d];
                            int nx = now.x + dx[nowDir];
                            int ny = now.y + dy[nowDir];
                            if(!isInside(nx, ny)) {
                                continue;
                            }
                            for(int j=2; j<=k; j++) {
                                if(turn - j >= 1 && map[nx][ny][turn-j] == now.idx) {
                                    map[nx][ny][turn] = now.idx;
                                    nq.offer(new Shark(now.idx, nx, ny, nowDir));
                                    end = true;
                                    break;
                                }
                            }
                            if(end) {
                                break;
                            }
                        }
                    }
                }
                else if(now.dir == 3) { //left
                    for(int d=0; d<4; d++) {
                        int nowDir = shark.get(now.idx -1).left[d];
                        int nx = now.x + dx[nowDir];
                        int ny = now.y + dy[nowDir];
                        if(!isInside(nx, ny)) {
                            continue;
                        }
                        boolean ok = true;
                        for(int j=2; j<=k; j++) {
                            if(turn - j >= 1 && map[nx][ny][turn-j] != 0) {
                                ok = false;
                                break;
                            }
                        }
                        if(ok) { //움직이기 가능
                            map[nx][ny][turn] = now.idx;
                            nq.offer(new Shark(now.idx, nx, ny, nowDir));
                            move = true;
                            break;
                        }
                    }
                    if(!move) { //못 움직였어
                        //이전의 위치로 이동
                        boolean end = false;
                        for(int d=0; d<4; d++) {
                            int nowDir = shark.get(now.idx-1).left[d];
                            int nx = now.x + dx[nowDir];
                            int ny = now.y + dy[nowDir];
                            if(!isInside(nx, ny)) {
                                continue;
                            }
                            for(int j=2; j<=k; j++) {
                                if(turn - j >= 1 && map[nx][ny][turn-j] == now.idx) {
                                    map[nx][ny][turn] = now.idx;
                                    nq.offer(new Shark(now.idx, nx, ny, nowDir));
                                    end = true;
                                    break;
                                }
                            }
                            if(end) {
                                break;
                            }
                        }
                    }
                }
                else if(now.dir == 4) { //right
                    for(int d=0; d<4; d++) {
                        int nowDir = shark.get(now.idx -1).right[d];
                        int nx = now.x + dx[nowDir];
                        int ny = now.y + dy[nowDir];
                        if(!isInside(nx, ny)) {
                            continue;
                        }
                        boolean ok = true;
                        for(int j=2; j<=k; j++) {
                            if(turn - j >= 1 && map[nx][ny][turn-j] != 0) {
                                ok = false;
                                break;
                            }
                        }
                        if(ok) { //움직이기 가능
                            map[nx][ny][turn] = now.idx;
                            nq.offer(new Shark(now.idx, nx, ny, nowDir));
                            move = true;
                            break;
                        }
                    }
                    if(!move) { //못 움직였어
                        //이전의 위치로 이동
                        boolean end = false;
                        for(int d=0; d<4; d++) {
                            int nowDir = shark.get(now.idx-1).right[d];
                            int nx = now.x + dx[nowDir];
                            int ny = now.y + dy[nowDir];
                            if(!isInside(nx, ny)) {
                                continue;
                            }
                            for(int j=2; j<=k; j++) {
                                if(turn - j >= 1 && map[nx][ny][turn-j] == now.idx) {
                                    map[nx][ny][turn] = now.idx;
                                    nq.offer(new Shark(now.idx, nx, ny, nowDir));
                                    end = true;
                                    break;
                                }
                            }
                            if(end) {
                                break;
                            }
                        }
                    }
                }
            }
            //상어 겹치는지 확인
            q.clear();
            q = sharkPosDup(nq, turn);
            //한 턴 끝남
            turn++;
        }

        if(one) {
            return turn - 2;
        }
        return -1;
    }

    private static Queue<Shark> sharkPosDup(Queue<Shark> q, int turn) {
        Set<Shark> s = new HashSet<>();
        s.clear();
        Queue<Shark> res = new ArrayDeque<>();
        while(!q.isEmpty()) {
            Shark now = q.poll();
            if(!s.contains(now)) {
                res.offer(now);
                map[now.x][now.y][turn] = now.idx;
            }
            s.add(now);
        }
        return res;
    }
}
