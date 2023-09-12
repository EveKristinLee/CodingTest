//BOJ G5 ABCDE
package solving.solve_0914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13023 {
    static int N; //사람 수
    static int M; //관계 수
    static List<List<Integer>> rel;
    static boolean[] visit;
    static boolean isTrue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rel = new ArrayList<>();
        for(int i=0; i<N; i++){
            rel.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            rel.get(a).add(b);
            rel.get(b).add(a);
        }

        isTrue = false;
        for(int i=0; i<N; i++) {
            if(!isTrue) {
                visit = new boolean[N];
                getRel(i, 0);
            }
        }
        if(isTrue) {
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }

    private static void getRel(int idx, int depth) {
        if(depth == 5) {
            isTrue = true;
            return;
        }
        if(isTrue) {
            return;
        }

        for(int i=0; i<rel.get(idx).size(); i++) {
            if(!visit[rel.get(idx).get(i)]) {
                visit[rel.get(idx).get(i)] = true;
                getRel(rel.get(idx).get(i), depth+1);
                visit[rel.get(idx).get(i)] = false;
            }
        }
    }
}
