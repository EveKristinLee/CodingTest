package solving.solve_0925;
//BOJ G3 9466 텀 프로젝트

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9466_2 {
    static int n;
    static int cnt;
    static int[] link;
    static boolean[] visit;
    static boolean[] isCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            n = Integer.parseInt(br.readLine());
            cnt = 0;
            link = new int[n+1];
            visit = new boolean[n+1];
            isCycle = new boolean[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++) {
                link[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=n; i++) {
                dfs(i);
            }
            System.out.println(n - cnt);
        }
    }

    private static void dfs(int now) {
        visit[now] = true;
        int next = link[now];
        if(!visit[next]) {
            dfs(next);
        }
        else {
            if(!isCycle[next]) {
                //탐색이 종료되지 않았는데 방문된다면 그 노드는 싸이클이 있는 노드로 간주
                cnt++;
                while(next != now) {
                    //노드의 꼬리까지 쫒아가서 모든 노드 카운트
                    cnt++;
                    next = link[next];
                }
            }
        }
        isCycle[now] = true;
    }
}
