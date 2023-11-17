package solving.solve_1011;
//BOJ P5 숨바꼭질 5

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17071 {
    static int N;
    static int K;
    static final int MAX_POS = 500_000;
    static int[][] visit;
    static final int INF = 987654321;

    private static boolean isInside(int idx) {
        if(idx < 0 || idx > MAX_POS) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new int[2][500_001];
        for(int i=0; i<2; i++) {
            Arrays.fill(visit[i], INF);
        }

        if(N == K) {
            System.out.println(0);
        }
        else{
            System.out.println(bfs());
        }
    }

    private static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        int time = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            int isEven = time % 2;

            for(int i=0; i<size; i++) {
                Integer now = q.poll();

                if(isInside(now-1)) {
                    if(visit[isEven][now-1] == INF) {
                        visit[isEven][now-1] = time;
                        q.offer(now-1);
                    }
                }
                if(isInside(now+1)) {
                    if(visit[isEven][now+1] == INF) {
                        visit[isEven][now+1] = time;
                        q.offer(now+1);
                    }
                }
                if(isInside(now*2)) {
                    if (visit[isEven][now * 2] == INF) {
                        visit[isEven][now * 2] = time;
                        q.offer(now * 2);
                    }
                }
            }
            time+=1;
            K += time;
            if(!isInside(K)) {
                return -1;
            }
            if(visit[isEven][K] != INF) {
                return time;
            }
        }
        return -1;
    }
}
