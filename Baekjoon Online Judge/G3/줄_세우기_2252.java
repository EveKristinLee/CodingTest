//BOJ G3
package solving.solve_0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 줄_세우기_2252 {
    static int N;
    static int M;
    static List<List<Integer>> height;
    static int[] degree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        height = new ArrayList<>();
        degree = new int[N+1];
        for(int i=0; i<=N; i++) {
            height.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            height.get(from).add(to); //from -> to
            degree[to]++; //to의 차수 증가시키기
        }

        topologySort();
        System.out.println(sb);
    }

    private static void topologySort() {
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1; i<=N; i++) {
            if(degree[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");

            for(Integer link : height.get(now)) {
                degree[link]--; //현재 노드 연결 빼기
                if(degree[link] == 0) {
                    q.offer(link);
                }
            }
        }
    }
}
