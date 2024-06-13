package 기출문제;
//CodeTree 코드트리 투어

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CodeTree_코드트리_투어 {
    private static class City implements Comparable<City>{
        int idx;
        int cost;
        public City(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(City c) {
            return this.cost - c.cost;
        }
    }

    private static class Travel implements Comparable<Travel>{
        int id;
        int revenue;
        int dest;
        int benefit;
        public Travel(int id, int revenue, int dest, int benefit) {
            this.id = id;
            this.revenue = revenue;
            this.dest = dest;
            this.benefit = benefit;
        }

        @Override
        public int compareTo(Travel o) {
            if(this.benefit == o.benefit) {
                return this.id - o.id;
            }
            return o.benefit - this.benefit;
        }
    }

    static int N; //도시의 개수
    static int M; //간선의 개수
    static List<List<City>> land;
    static int[] dist; //출발지로부터 최적의 거리
    static final int INF = 987654321;
    static boolean[] isMade; //해당 id의 여행이 만들어졌는가
    static PriorityQueue<Travel> bestTravel;
    static boolean[] isKill; //해당 id의 여행이 취소됐는가
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        for(int q=1; q<=Q; q++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            if(order == 100) { //코드트리 랜드 건설
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                land = new ArrayList<>();
                isMade = new boolean[30_005];
                bestTravel = new PriorityQueue<>();
                isKill = new boolean[30_005];
                for(int i=0; i<=N; i++) {
                    land.add(new ArrayList<>());
                }
                for(int i=0; i<M; i++) {
                    int v = Integer.parseInt(st.nextToken());
                    int u = Integer.parseInt(st.nextToken());
                    int w = Integer.parseInt(st.nextToken());
                    land.get(v).add(new City(u, w));
                    land.get(u).add(new City(v, w));
                }
                dijkstra(0);
            }
            else if(order == 200) { //여행 상품 생성
                int id = Integer.parseInt(st.nextToken());
                int revenue = Integer.parseInt(st.nextToken());
                int dest = Integer.parseInt(st.nextToken());
                int benefit = revenue - dist[dest];
                Travel input = new Travel(id, revenue, dest, benefit);
                bestTravel.offer(input);
                isMade[id] = true;
            }
            else if(order == 300) { //여행 상품 취소
                int id = Integer.parseInt(st.nextToken());
                if(isMade[id]) {
                    isKill[id] = true;
                }
            }
            else if(order == 400) { //최적의 여행 상품 판매
                Travel best = null;
                while(!bestTravel.isEmpty()) {
                    Travel t = bestTravel.peek();
                    if(t.benefit < 0) {
                        break;
                    }
                    bestTravel.poll();
                    if(!isKill[t.id]) {
                        best = t;
                        break;
                    }
                }

                if(best != null) {
                    sb.append(best.id).append("\n");
                }
                else {
                    sb.append("-1\n");
                }
            }
            else if(order == 500) { //여행 상품의 출발지 변경
                int s = Integer.parseInt(st.nextToken());
                dijkstra(s);

                List<Travel> tmp = new ArrayList<>();
                while(!bestTravel.isEmpty()) {
                    tmp.add(bestTravel.poll());
                }
                for(Travel t : tmp) {
                    isMade[t.id] = true;
                    int b = t.revenue - dist[t.dest];
                    bestTravel.offer(new Travel(t.id, t.revenue, t.dest, b));
                }
            }
        }
        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        dist = new int[N+1];
        Arrays.fill(dist, INF);
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.offer(new City(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            City now = pq.poll();

            for(int i=0; i<land.get(now.idx).size(); i++) {
                City next = land.get(now.idx).get(i);
                if(dist[next.idx] > dist[now.idx] + next.cost) {
                    dist[next.idx] = dist[now.idx] + next.cost;
                    pq.offer(new City(next.idx, dist[next.idx]));
                }
            }
        }
    }
}
