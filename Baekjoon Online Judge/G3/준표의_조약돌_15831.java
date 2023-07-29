//BOJ G3
package solving.solve_0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 준표의_조약돌_15831 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //조약돌의 총 개수
        int B = Integer.parseInt(st.nextToken()); //검은 조약돌의 최대 개수
        int W = Integer.parseInt(st.nextToken()); //흰 조약돌의 최소 개수
        String road = br.readLine();
        int start = 0;
        int end = 0;
        int cntW = 0;
        int cntB = 0;
        int res = Integer.MIN_VALUE;

        while(true) {
            if(cntB > B){ //start 조건 확인
                if(road.charAt(start) == 'W') {
                    cntW--;
                }
                else {
                    cntB--;
                }
                start++;
            }
            else if(end == N) { //end 범위 체크
                break;
            }
            else if(cntB <= B) { //end 조건 확인
                if(road.charAt(end) == 'W') {
                    cntW++;
                }
                else {
                    cntB++;
                }
                end++;
            }

            if(cntB <= B && cntW >= W) {
                res = Math.max(res, end - start);
            }
        }
        if(res == Integer.MIN_VALUE) res = 0;
        System.out.println(res);

    }
}
