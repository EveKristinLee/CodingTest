package solving_2.solve_12.solve_19;
//BOJ G2 1135 뉴스 전하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1135 {
    private static class Node implements Comparable<Node>{
        int idx;
        int time;

        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

        public int compareTo(Node n) {
            if(this.time == n.time) {
                return this.idx - n.idx;
            }
            return n.time - this.time;
        }
    }

    static int N;
    static List<List<Integer>> tree;
    static int[] dp;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            tree.add(new ArrayList<>());
            int parent = Integer.parseInt(st.nextToken());
            if(parent != -1) {
                tree.get(parent).add(i);
            }
        }

        dp = new int[N];
        visit = new boolean[N];
        System.out.println(dfs(0));
    }

    //자식이 많은 노드부터 탐색해야 최소값
    private static int dfs(int idx) {
        visit[idx] = true;
        Queue<Node> pq = new PriorityQueue<>();

        for(int i=0; i<tree.get(idx).size(); i++) {
            int now = tree.get(idx).get(i);
            if(!visit[now]) {
                dp[now] = dfs(now);
                pq.offer(new Node(now, dp[now]));
            }
        }

        int time = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            time++;
            dp[idx] = Math.max(dp[idx], now.time+time);
        }

        return dp[idx];
    }
}
