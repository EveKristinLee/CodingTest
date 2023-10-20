//BOJ G2 트리의 지름
package solving_2.solve_12.solve_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1167 {
    private static class Node{
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    static int V; //트리의 정점 개수
    static List<List<Node>> graph;
    static boolean[] visit;
    static int res;
    static int node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for(int i=0; i<=V; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int nextIdx = Integer.parseInt(st.nextToken());
            while(nextIdx != -1) {
                int nextCost = Integer.parseInt(st.nextToken());
                graph.get(idx).add(new Node(nextIdx, nextCost));
                nextIdx = Integer.parseInt(st.nextToken());
            }
        }

        res = 0;

        visit = new boolean[V+1];
        findRoute(1, 0); //어떤 정점이든 가장 먼 정점 찾기

        visit = new boolean[V+1];
        findRoute(node, 0); //가장 먼 정점인 node에서부터 거리 구하기

        System.out.println(res);
    }

    private static void findRoute(int idx, int cost) {
        if(cost > res) {
            res = cost;
            node = idx;
        }

        visit[idx] = true;
        for(int i=0; i<graph.get(idx).size(); i++) {
            Node now = graph.get(idx).get(i);
            if(!visit[now.idx]) {
                visit[now.idx] = true;
                findRoute(now.idx, now.cost + cost);
            }
        }
    }
}
