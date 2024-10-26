package solving.solve_0914;
//BOJ G5 15661 링크와 스타트

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15661 {
    static int N; //사람 수
    static int[][] skill;
    static boolean[] select;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        skill = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                skill[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        select = new boolean[N];
        res = Integer.MAX_VALUE;
        subset(0);
        System.out.println(res);
    }

    private static void subset(int cnt) {
        if(cnt == N) {
            int start = 0; //true
            int link = 0; //false
            for(int i=0; i<N; i++) {
                for(int j=i+1; j<N; j++) {
                    if(select[i] != select[j]) {//같은 팀이 아니면
                        continue;
                    }
                    if(select[i]) {
                        start += skill[i][j] + skill[j][i];
                    }
                    else {
                        link += skill[i][j] + skill[j][i];
                    }
                }
            }

            int diff = Math.abs(start - link);
            res = Math.min(res, diff);
            return;
        }

        select[cnt] = true;
        subset(cnt+1);

        select[cnt] = false;
        subset(cnt+1);
    }
}
