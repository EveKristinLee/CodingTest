package solving.solve_1028;
//BOJ G4 트리의 지름

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1967 {
    private static class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    static int n; //노드의 개수
    static List<List<Node>> graph;
    static boolean[] visit;
    static int res;
    static int node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Node(e, c));
            graph.get(e).add(new Node(s, c));
        }

        res = 0;
        visit = new boolean[n+1];
        findRoute(1, 0);

        visit = new boolean[n+1];
        findRoute(node, 0);
        System.out.println(res);
    }

    private static void findRoute(int idx, int sum) {
        if(sum > res) {
            res = sum;
            node = idx;
        }
        visit[idx] = true;

        for(int i=0; i<graph.get(idx).size(); i++) {
            Node now = graph.get(idx).get(i);
            if(!visit[now.idx]) {
                visit[now.idx] = true;
                findRoute(now.idx, now.cost + sum);
            }
        }
    }
}
