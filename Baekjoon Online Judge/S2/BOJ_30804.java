package algo_0822;
//BOJ S2 30804 과일 탕후루

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_30804 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] fruit = new int[N];
        int[] cntF = new int[10];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int now = Integer.parseInt(st.nextToken());
            fruit[i] = now;
        }

        int left = 0;
        int right = 0;
        int res = 0;
        int cnt = 0;
        int kind = 0; //과일 종류의 수
        while(right < N) {
            if(cntF[fruit[right]] == 0) { //새로 발견한 과일
                kind++; //과일 종류의 수 +1
            }

            cntF[fruit[right]]++; //해당 과일의 수 증가
            cnt++; //배열의 길이 증가

            if(kind > 2) { //과일 종류가 2개 이상이면 왼쪽 포인터 이동
                cntF[fruit[left]]--;
                cnt--;
                if(cntF[fruit[left]] == 0) { //해당 과일의 개수가 0개가 되었다면
                    kind--; //과일 종류의 수 -1
                }
                left++;
            }

            res = Math.max(res, cnt);
            right++;
        }

        System.out.println(res);
    }
}
