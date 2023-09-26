//BOJ G4 합이 0
package solving.solve_1028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3151 {
    static int N;
    static int[] skill;
    static boolean[] visit;
    static long res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        skill = new int[N];
        visit = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            skill[i] = Integer.parseInt(st.nextToken());
        }

        res = 0;
        Arrays.sort(skill);
        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                int sum = skill[i] + skill[j];
                int lower = lowerBound(j+1, N, -sum);
                int upper = upperBound(j+1, N, -sum);
                res += (upper - lower);
            }
        }
        System.out.println(res);
    }

    //key보다 크거나 같은 값 반환
    private static int lowerBound(int start, int end, int key) {
        while(start < end) {
            int mid = (start + end) / 2;
            if(skill[mid] >= key) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }
        return end;
    }

    //key보다 큰 값 반환
    private static int upperBound(int start, int end, int key) {
        while(start < end) {
            int mid = (start + end) / 2;
            if(skill[mid] <= key) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return end;
    }
}
