package solving.solve_0925;
//BOJ G3 1774 우주신과의 교감

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1774 {
    private static class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static class Edge implements Comparable<Edge>{
        int s;
        int e;
        double dist;
        public Edge(int s, int e, double dist) {
            this.s = s;
            this.e = e;
            this.dist = dist;
        }
        public int compareTo(Edge e) {
            if(this.dist < e.dist) {
                return -1;
            }
            return 1;
        }
    }
    static int N; //우주신들의 개수
    static int M; //통로의 개수
    static List<Pos> pos;
    static List<Edge> dist;
    static int[] p;

    private static void init() {
        p = new int[N+1];
        for(int i=1; i<=N; i++) {
            p[i] = i;
        }
    }

    private static int findP(int a) {
        if(p[a] == a) {
            return a;
        }

        return p[a] = findP(p[a]);
    }

    private static boolean union(int a, int b) {
        a = findP(a);
        b = findP(b);
        if(a == b) {
            return false;
        }

        if(a < b) {
            p[b] = a;
        }
        else {
            p[a] = b;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pos = new ArrayList<>();
        pos.add(new Pos(-1, -1)); //idx 맞추기
        dist = new ArrayList<>();
        init();

        int linkCnt = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pos.add(new Pos(x, y));
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(union(s, e)) {
                linkCnt += 1;
            }
        }

        //각 정점의 거리 구하기
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i != j) {
                    double d = calcDist(pos.get(i), pos.get(j));
                    dist.add(new Edge(i, j, d));
                }
            }
        }
        Collections.sort(dist);

        int cnt = linkCnt;
        double res = 0;
        for(int i=0; i<dist.size(); i++) {
            Edge now = dist.get(i);
            if(union(now.s, now.e)) {
                cnt += 1;
                res += now.dist;
            }
            if(cnt == N-1) {
                break;
            }
        }

        res = (Math.round(res * 100) / 100.0);
        System.out.printf("%.2f\n", res);
    }

    private static double calcDist(Pos s, Pos e) {
        double sum = Math.abs(Math.pow(s.x - e.x, 2) + Math.pow(s.y - e.y, 2));
        double res = Math.sqrt(sum);
        return res;
    }
}
