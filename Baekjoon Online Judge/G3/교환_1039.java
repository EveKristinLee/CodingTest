package solve_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ G3
public class 교환_1039 {
    static int N;
    static int len;
    static int K;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        res = -1;
        len = Integer.toString(N).length();
        bfs();
        System.out.println(res);
    }

    private static void bfs() {
        boolean[][] visit = new boolean[1000001][11];
        Queue<Integer[]> q = new ArrayDeque<>();
        q.offer(new Integer[] {N, 0});
        visit[N][0] = true;

        while(!q.isEmpty()) {
            Integer[] top = q.poll();

            if(top[1] == K) { //이동 횟수 다 찼으면
                res = Math.max(res, top[0]);
                continue;
            }

            for(int i=0; i<len; i++) {
                for(int j=i+1; j<len; j++) {
                    int tmp = calc(top[0], i, j);
                    if(tmp != -1 && !visit[tmp][top[1] +1]) {
                        visit[tmp][top[1] +1] = true;
                        q.offer(new Integer[] {tmp, top[1]+1});
                    }
                }
            }
        }
    }

    private static int calc(Integer now, int i, int j) {
        char[] s = now.toString().toCharArray();
        if(i == 0 && s[j] == '0') {
            return -1;
        }
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
        return Integer.parseInt(String.valueOf(s));
    }
}
