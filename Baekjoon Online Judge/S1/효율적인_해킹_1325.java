package algo_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 효율적인_해킹_1325 {
    static int N;
    static int M;
    static int maxCnt;
    static int[] res;
    static List<List<Integer>> trust;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trust = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            trust.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            trust.get(B).add(A);
        }

        res = new int[N+1];
        for(int i=1; i<=N; i++) {
            int cnt = bfs(i);
            res[i] = cnt;
            maxCnt = Math.max(maxCnt, cnt);
        }

        for(int i=1; i<=N; i++) {
            if(res[i] == maxCnt) {
                sb.append(i + " ");
            }
        }
        System.out.println(sb);
    }

    private static int bfs(int now) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[N+1];
        q.offer(now);
        visit[now] = true;
        int cnt = 0;

        while(!q.isEmpty()) {
            int top = q.poll();
            for(int i : trust.get(top)) {
                if(!visit[i]) {
                    visit[i] = true;
                    q.offer(i);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
