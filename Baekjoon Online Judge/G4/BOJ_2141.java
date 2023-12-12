package solving.solve_1028;
//BOJ G4 2141 우체국

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2141 {
    private static class Town implements Comparable<Town>{
        int pos;
        int people;

        public Town(int pos, int people) {
            this.pos = pos;
            this.people = people;
        }

        public int compareTo(Town t) {
            return this.pos - t.pos;
        }
    }

    static int N;
    static Town[] town;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        town = new Town[N];
        long sum = 0;
        long mid = 0;
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            town[i] = new Town(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sum += town[i].people;
        }
        Arrays.sort(town);

        if(sum % 2 != 0) {
            sum += 1;
        }
        mid = sum / 2;

        long now = 0;
        for(int i=0; i<N; i++) {
            now += town[i].people;
            if(mid <= now) {
                res = town[i].pos;
                break;
            }
        }

        System.out.println(res);
    }
}
