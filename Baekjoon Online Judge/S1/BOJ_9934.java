package solving;
//BOJ S1 9934 완전 이진 트리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_9934 {
    static int K;
    static int[] building;
    static boolean[] visit;
    static List<List<Integer>> level;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        int cnt = (int)Math.pow(2, K) -1 ;
        building = new int[cnt+1];
        visit = new boolean[cnt+1];
        level = new ArrayList<>();
        for(int i=0; i<K; i++) {
            level.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=cnt; i++) {
            building[i] = Integer.parseInt(st.nextToken());
        }

        int start = (cnt / 2) + 1;
        calcLevel(0, start, 0,  cnt);
        for(int i=0; i<K; i++) {
            for(Integer now : level.get(i)) {
                System.out.print(now + " ");
            }
            System.out.println();
        }
    }

    private static void calcLevel(int depth, int idx, int now, int cnt) {
        if(depth == K) {
            return;
        }
        if(idx < 1 || idx > cnt) {
            return;
        }
        if(visit[idx]) {
            return;
        }
        level.get(depth).add(building[idx]);
        visit[idx] = true;

        int half = Math.abs(idx - now) / 2;
        calcLevel(depth+1, (idx-half), idx, cnt);
        calcLevel(depth+1, (idx+half), idx, cnt);
    }
}
