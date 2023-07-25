//BOJ G3
package solving.solve_0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ACM_Craft_1005 {
    static int N; //건물의 개수
    static int K; //건설순서 규칙
    static int[] degree;
    static int[] D; //각 건물의 건설에 걸리는 시간
    static List<List<Integer>> order;
    static int W; //승리하기 위해 건설해야할 건물
    static int[] time; //건물을 짓기 위해 걸리는 최소의 시간
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            degree = new int[N+1];
            D = new int[N+1];
            order = new ArrayList<>();
            time = new int[N+1];
            for(int i=0; i<=N; i++) {
                order.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) {
                D[i] = Integer.parseInt(st.nextToken());
                time[i] = D[i];
            }
            for(int i=1; i<=K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                order.get(from).add(to);
                degree[to]++;
            }
            W = Integer.parseInt(br.readLine());

            topologyOrder();
            System.out.println(res);
        }
    }

    private static void topologyOrder() {
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1; i<=N; i++) {
            if(degree[i] == 0) {
                q.offer(i);
                time[i] = D[i];
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();
            if(now == W) {
                res = time[now];
                return;
            }
            for(Integer next : order.get(now)) {
                degree[next]--;
                //이전의 건물짓는데 걸린 시간 vs 현재 건물 짓는 시간중에 더 큰값으로 하면 앞에 동시에 건물 짓는 것 중에 더 큰값으로 들어감
                time[next] = Math.max(time[next], D[next] + time[now]);
                if(degree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}
