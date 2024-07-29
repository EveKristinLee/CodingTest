package solving.solve_1028;
//BOJ G4 1043 거짓말

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1043 {
    static int N; //사람의 수
    static int M; //파티의 수
    static List<Integer> know; //진실을 아는 사람
    static List<List<Integer>> party;
    static int[] p;
    static int res;

    private static void init() {
        p = new int[N+1];
        for(int i=1; i<=N; i++) {
            p[i] = i;
        }
    }

    private static int find(int a) {
        if(p[a] == a) {
            return a;
        }
        return p[a] = find(p[a]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a==b) {
            return false;
        }
        p[b] = a;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int knowCnt = Integer.parseInt(st.nextToken());
        know = new ArrayList<>();
        if(knowCnt > 0) {
            for(int i=0; i<knowCnt; i++) {
                know.add(Integer.parseInt(st.nextToken()));
            }
        }
        init();

        party = new ArrayList<>();
        for(int i=0; i<M; i++) {
            party.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            party.get(i).add(a);
            int b = -1;
            for(int j=0; j<cnt-1; j++) {
                b = Integer.parseInt(st.nextToken());
                party.get(i).add(b);
                union(a, b);
                b = a;
            }
        }

        res = -1;
        if(knowCnt == 0) {
            res = M;
        }
        else {
            res = M;
            List<Integer> parent = new ArrayList<>();
            for(int i=0; i<knowCnt; i++) {
                parent.add(find(know.get(i)));
            }

            for(int i=0; i<M; i++) {
                for(int j=0; j<party.get(i).size(); j++) {
                    int nowP = find(party.get(i).get(j));
                    if(parent.contains(nowP)) {
                        res -= 1;
                        break;
                    }
                }
            }
        }
        System.out.println(res);
    }
}
