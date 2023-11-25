package solving.solve_0925;
//BOJ G3 할로윈의 양아치

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20303 {
    private static class Group{
        int cnt; //아이 수
        int totalCandy;

        public Group(int cnt, int totalCandy) {
            this.cnt = cnt;
            this.totalCandy = totalCandy;
        }
    }

    static int N; //거리에 있는 아이들의 수
    static int M; //친구 관계 수
    static int K; //울음소리 최소 아이의 수
    static int[] candy;
    static int[] p;
    static int[] linkCnt;
    static int[] totalCandy;
    static List<Group> groups;
    static int res;
    static int[] dp;

    private static void init() {
        p = new int[N+1];
        linkCnt = new int[N+1];
        totalCandy = new int[N+1];
        for(int i=1; i<=N; i++) {
            p[i] = i;
        }
    }

    private static int find(int a) {
        if(p[a] == a) {
            return a;
        }
        return p[a] = find(p[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            p[b] = a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        res = 0;
        candy = new int[N+1];
        dp = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
        }
        groups = new ArrayList<>();
        init();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            union(s, e);
        }

        for(int i=1; i<=N; i++) {
            linkCnt[find(i)]++;
            totalCandy[find(i)] += candy[i];
        }

        for(int i=1; i<=N; i++) {
            if(linkCnt[i] > 0) {
                groups.add(new Group(linkCnt[i], totalCandy[i]));
            }
        }

        for(int i=0; i<groups.size(); i++){
            for(int j=K-1; j>=groups.get(i).cnt; j--) {
                dp[j] = Math.max(dp[j], dp[j-groups.get(i).cnt] + groups.get(i).totalCandy);
            }
        }

        System.out.println(dp[K-1]);
    }
}
