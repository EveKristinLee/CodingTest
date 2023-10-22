package solving_2.solve_12.solve_19;
//BOJ G2 문제집
//그래프간의 선후관계를 찾는 문제이므로 위상정렬 사용
//위상 정렬
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1766 {
    static int N; //문제 수
    static int M;
    static int[] edge; //집입차수 저장 함수
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edge = new int[N+1];
        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            edge[b]++;
        }

        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new PriorityQueue<>();
        for(int i=1; i<=N; i++) {
            if(edge[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");

            List<Integer> list = graph.get(now);
            for(int i=0; i<list.size(); i++) {
                edge[list.get(i)]--; //집입 차수 줄이기
                if(edge[list.get(i)] == 0) {
                    q.offer(list.get(i));
                }
            }
        }

        System.out.println(sb);
    }
}
