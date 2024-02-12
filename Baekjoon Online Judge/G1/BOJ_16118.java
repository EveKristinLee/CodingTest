package solving.solve_1005;
//BOJ G1 16118 달빛 여우

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16118 {
    private static class Node implements Comparable<Node> {
        int idx;
        int cost;
        boolean half;

        public Node(int idx, int cost, boolean half) {
            this.idx = idx;
            this.cost = cost;
            this.half = half;
        }

        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
    static int N; //그루터기의 수
    static int M; //오솔길의 개수
    static List<List<Node>> link;
    static int[][] wolfD; //[idx][half], 0 : half
    static int[] foxD;
    static final int INF = 987654321;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        link = new ArrayList<>();
        wolfD = new int[N+1][2];
        foxD = new int[N+1];
        for(int i=0; i<=N; i++) {
            link.add(new ArrayList<>());
            wolfD[i][1] = INF;
            wolfD[i][0] = INF;
            foxD[i] = INF;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int cost = c * 2;
            link.get(a).add(new Node(b, cost, false));
            link.get(b).add(new Node(a, cost, false));
        }

        res = 0;
        dijkstra();
        System.out.println(res);
    }

    private static void dijkstra() {
        Queue<Node> foxQ = new PriorityQueue<>();
        Queue<Node> wolfQ = new PriorityQueue<>();
        foxD[1] = 0;
        wolfD[1][1] = 0;
        foxQ.offer(new Node(1, foxD[1], false));
        wolfQ.offer(new Node(1, wolfD[1][1], true));

        while(!foxQ.isEmpty()) {
            Node now = foxQ.poll();
            if(foxD[now.idx] < now.cost) {
                continue;
            }
            for(int i=0; i<link.get(now.idx).size(); i++) {
                Node next = link.get(now.idx).get(i);
                if(foxD[next.idx] > foxD[now.idx] + next.cost) {
                    foxD[next.idx] = foxD[now.idx] + next.cost;
                    foxQ.offer(new Node(next.idx, foxD[next.idx], false));
                }
            }
        }

        while(!wolfQ.isEmpty()) {
            Node now = wolfQ.poll();
            if(now.half) {
                if(wolfD[now.idx][1] < now.cost) {
                    continue;
                }
                for(int i=0; i<link.get(now.idx).size(); i++) {
                    Node next = link.get(now.idx).get(i);
                    if(wolfD[next.idx][0] > wolfD[now.idx][1] + (next.cost / 2)) {
                        wolfD[next.idx][0] = wolfD[now.idx][1] + (next.cost / 2);
                        wolfQ.offer(new Node(next.idx, wolfD[next.idx][0], false));
                    }
                }
            }
            else{
                if(wolfD[now.idx][0] < now.cost) {
                    continue;
                }
                for(int i=0; i<link.get(now.idx).size(); i++) {
                    Node next = link.get(now.idx).get(i);
                    if(wolfD[next.idx][1] > wolfD[now.idx][0] + (next.cost * 2)) {
                        wolfD[next.idx][1] = wolfD[now.idx][0] + (next.cost * 2);
                        wolfQ.offer(new Node(next.idx, wolfD[next.idx][1], true));
                    }
                }
            }
        }

        for(int i=1; i<=N; i++) {
            if(foxD[i] < Math.min(wolfD[i][0] , wolfD[i][1])) {
                res++;
            }
        }
    }
}
