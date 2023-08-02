//BOJ G4
package solving.solve_1028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 고냥이_16472 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int[] alpha = new int[27];
        int cnt = 1; //사용하고 있는 알파벳 개수
        int res = Integer.MIN_VALUE;

        int start = 0;
        int end = 1;
        alpha[s.charAt(0) - 'a']++;
        while(end < s.length()) {
            int idx = s.charAt(end) - 'a';
            alpha[idx]++;
            if(alpha[idx] == 1) { //새로 추가된 알파벳
                cnt++;
            }

            while(cnt > N) {
                int idx2 = s.charAt(start) - 'a';
                alpha[idx2]--;

                if(alpha[idx2] == 0) {
                    cnt--;
                }
                start++;
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        System.out.println(res);
    }
}
