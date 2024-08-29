package algo_0822;
//BOJ S2 24479 알고리즘 수업 - 깊이 우선 탐색 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_24479 {
    static int N;
    static int M;
    static int R;
    static List<List<Integer>> link;
    static int[] visit;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        link = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            link.add(new ArrayList<>());
        }
        visit = new int[N+1];
        Arrays.fill(visit, -1);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            link.get(s).add(e);
            link.get(e).add(s);
        }
        for(int i=1; i<=N; i++) {
            Collections.sort(link.get(i));
        }

        cnt = 1;
        dfs(R);
        for(int i=1; i<=N; i++) {
            if(visit[i] != -1) {
                System.out.println(visit[i]);
            }
            else {
                System.out.println(0);
            }
        }
    }


    private static void dfs(int now) {
        visit[now] = cnt++;

        for(int i=0; i<link.get(now).size(); i++) {
            int next = link.get(now).get(i);
            if(visit[next] == -1) {
                dfs(next);
            }
        }
    }
}
