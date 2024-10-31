package solving.solve_1028;
//BOJ G4 13424 비밀 모임

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13424 {
    private static class Room implements Comparable<Room>{
        int idx;
        int cost;
        public Room(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        public int compareTo(Room r) {
            return this.cost - r.cost;
        }
    }
    static int N; //방의 개수
    static int M; //비밀 통로의 개수
    static List<List<Room>> road;
    static int K; //친구의 수
    static int[] pos; //현재 친구가 있는 방의 위치
    static int[] sum; //각 방의 이동 거리 합
    static int[] d; //각 방의 이동거리
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            road = new ArrayList<>();
            for(int i=0; i<=N; i++) {
                road.add(new ArrayList<>());
            }
            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                road.get(a).add(new Room(b, c));
                road.get(b).add(new Room(a, c));
            }

            K = Integer.parseInt(br.readLine());
            pos = new int[K+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++) {
                pos[i] = Integer.parseInt(st.nextToken());
            }
            sum = new int[N+1];
            d = new int[N+1];

            for(int i=1; i<=K; i++) { //친구마다 각 방의 거리 구하기
                Arrays.fill(d, INF);
                dijkstra(pos[i]);
                for(int j=1; j<=N; j++) {
                    sum[j] += d[j];
                }
            }

            int resIdx = 0;
            int minSum = Integer.MAX_VALUE;
            for(int i=1; i<=N; i++) {
                if(minSum > sum[i]) {
                    minSum = sum[i];
                    resIdx = i;
                }
            }
            System.out.println(resIdx);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Room> pq = new PriorityQueue<>();
        pq.offer(new Room(start, 0));
        d[start] = 0;

        while(!pq.isEmpty()) {
            Room now = pq.poll();

            for(int i=0; i<road.get(now.idx).size(); i++) {
                Room next = road.get(now.idx).get(i);
                if(d[next.idx] > d[now.idx] + next.cost) {
                    d[next.idx] = d[now.idx] + next.cost;
                    pq.offer(new Room(next.idx, d[next.idx]));
                }
            }
        }

//        System.out.println(Arrays.toString(d));
    }
}
