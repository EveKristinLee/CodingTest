package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 바이러스2606 {
    static int n;
    static int m;
    static int graph[][];
    static boolean visit[];

    public static void bfs(int idx) {
        Queue<Integer> q = new LinkedList<Integer>();
        int cnt = 0;
        q.offer(idx);
        visit[idx] = true;

        while(!q.isEmpty()) {
            int tmp = q.poll();
            for(int i=1; i<=n; i++) {
                if(!visit[i] && graph[tmp][i] == 1) {
                    q.offer(i);
                    visit[i] = true;
                    cnt++;
                }
            }
        }
        System.out.println("result = " + cnt);
    }

    public static void main(String[] args) {
        graph = new int[101][101];
        visit = new boolean[101];

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i=0; i<m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x][y] = graph[y][x] = 1;
        }
        bfs(1);
    }
}
