package solving;
//BOJ S1 2343 기타 레슨

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2343 {
    static int N; //강의의 수
    static int M; //블루레이 개수
    static int[] lesson;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lesson = new int[N];
        int front = 0;
        int end = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            lesson[i] = Integer.parseInt(st.nextToken());
            front = Math.max(front, lesson[i]);
            end += lesson[i];
        }

        int res = 0;
        while(front <= end) {
            int mid = (front + end) / 2;

            int cnt = getCount(mid);
            if(cnt > M) {
                front = mid + 1;
            }
            else {
                end = mid - 1;
                res = mid;
            }
        }
        System.out.println(res);
    }

    private static int getCount(int time) {
        int sum = 0;
        int cnt = 0;

        for(int i=0; i<N; i++) {
            if(sum + lesson[i] > time) {
                sum = 0;
                cnt += 1;
            }
            sum += lesson[i];
        }

        if(sum != 0) {
            cnt += 1;
        }
        return cnt;
    }
}
