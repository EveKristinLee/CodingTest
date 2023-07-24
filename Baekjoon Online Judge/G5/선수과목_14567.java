//BOJ G5
package solving.solve_0914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 선수과목_14567 {
    static int N; //과목의 수
    static int M; //선수 조건의 수
    static int[] degree;
    static int[] result;
    static List<List<Integer>> subject;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        degree = new int[N+1];
        result = new int[N+1];
        subject = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            subject.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            subject.get(from).add(to);
            degree[to]++;
        }

        topoloySort();
        for(int i=1; i<=N; i++) {
            System.out.print(result[i] + " ");
        }
    }

    private static void topoloySort() {
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1; i<=N; i++) {
            if(degree[i] == 0) {
                q.offer(i);
                result[i] = 1;
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();

            for(Integer link : subject.get(now)) {
                degree[link]--;
                if(degree[link] == 0) {
                    q.offer(link);
                    result[link] = result[now]+1;
                }
            }
        }
    }
}
