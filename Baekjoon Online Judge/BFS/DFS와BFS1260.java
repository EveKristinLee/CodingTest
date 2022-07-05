package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DFS와BFS1260 {
    static int n;
    static int m;
    static int v;
    static int graph[][];
    static boolean visit[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        v = sc.nextInt();
        graph = new int[1001][1001];
        visit = new boolean[1001];

        for(int i=0; i<m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x][y] = 1;
            graph[y][x] = 1;
        }
        dfs(v);
        System.out.println();
        visit = new boolean[1001];
        bfs(v);
    }
    public static void dfs(int idx) {
        visit[idx] = true;
        System.out.print(idx + " ");

        for(int i=1; i<=n; i++) {
            if(!visit[i] && graph[idx][i] == 1) {
                dfs(i);
            }
        }
    }

    public static void bfs(int idx) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(idx);
        visit[idx] = true;

        while(!q.isEmpty()) {
            int now = q.poll();
            System.out.print(now + " ");

            for(int i=1; i<=n; i++) {
                if(!visit[i] && graph[now][i] == 1) {
                    q.offer(i);
                    visit[i] = true;
                }
            }
        }
    }
}
