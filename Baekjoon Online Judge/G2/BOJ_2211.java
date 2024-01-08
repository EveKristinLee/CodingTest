package solving_2.solve_12.solve_19;
//BOJ G2 2211 네트워크 복구

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2211 {
    public static class Computer implements Comparable<Computer>{
        int idx;
        int cost;

        public Computer(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Computer c) {
            return this.cost - c.cost;
        }
    }
    static int N; //컴퓨터 개수
    static int M; //회선 개수
    static List<List<Computer>> line;
    static int[] dist;
    static int[] route;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        line = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            line.add(new ArrayList<>());
        }
        dist = new int[N+1];
        route = new int[N+1];
        Arrays.fill(dist, INF);
        Arrays.fill(route, -1);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            line.get(A).add(new Computer(B, C));
            line.get(B).add(new Computer(A, C));
        }

        dijkstra();
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=N; i++) {
            if(route[i] != -1) {
                cnt++;
                sb.append(i).append(" ").append(route[i]).append("\n");
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }

    private static void dijkstra() {
        Queue<Computer> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.offer(new Computer(1, dist[1]));

        while(!pq.isEmpty()) {
            Computer now = pq.poll();

            for(int i=0; i<line.get(now.idx).size(); i++) {
                Computer next = line.get(now.idx).get(i);
                if(dist[next.idx] > dist[now.idx] + next.cost) {
                    dist[next.idx] = dist[now.idx] + next.cost;
                    pq.offer(new Computer(next.idx, dist[next.idx]));
                    route[next.idx] = now.idx;
                }
            }
        }
    }
}
