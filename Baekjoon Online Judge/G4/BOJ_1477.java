package solving.solve_1028;
//BOJ G4 1477 휴게소 세우기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1477 {
    static int N;
    static int M;
    static int L;
    static int[] station;
    static int[] diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        station = new int[N+2];
        station[0] = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            station[i] = Integer.parseInt(st.nextToken());
        }
        station[N+1] = L;
        Arrays.sort(station);
        diff = new int[N+1];
        for(int i=1; i<station.length; i++) {
            diff[i-1] = station[i] - station[i-1] - 1; //시작과 끝점을 포함하지 않음.
        }

        int front = 1;
        int end = L-1;
        while(front <= end) {
            int mid = (front + end) / 2;
            int cnt = 0;

            for(int i=0; i<diff.length; i++) {
                cnt += (diff[i] / mid);
            }

            if(cnt > M) { //휴게소 개수 보다 더 많이 나옴
                front = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        System.out.println(front);
    }

}
