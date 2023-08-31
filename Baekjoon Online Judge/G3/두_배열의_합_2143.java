package solving.solve_0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두_배열의_합_2143 {
    static int T;
    static int N;
    static int M;
    static int[] A;
    static long[] sumA;
    static int[] B;
    static long[] sumB;
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int idxA = N*(N+1)/2;
        int idxB = M*(M+1)/2;
        sumA = new long[idxA];
        sumB = new long[idxB];
        idxA = 0; idxB = 0;
        for(int i=0; i<N; i++) {
            int tmpA = 0;
            for(int j=i; j<N; j++) {
                tmpA += A[j];
                sumA[idxA++] = tmpA;
            }
        }
        for(int i=0; i<M; i++) {
            int tmpB = 0;
            for(int j=i; j<M; j++) {
                tmpB += B[j];
                sumB[idxB++] = tmpB;
            }
        }
        Arrays.sort(sumA);
        Arrays.sort(sumB);

        int aSize = N*(N+1)/2;
        int bSize = M*(M+1)/2;
        idxA = 0;
        idxB = bSize -1;
        while(idxA < aSize && idxB >= 0) {
            long nowA = sumA[idxA];
            long nowB = sumB[idxB];
            long sum = nowA + nowB;
            if(sum == T) {
                long tA = 0; long tB = 0;
                while(idxA < aSize && nowA == sumA[idxA]) {
                    idxA++;
                    tA++;
                }

                while(idxB >= 0 && nowB == sumB[idxB]) {
                    idxB--;
                    tB++;
                }
                res += tA * tB;
            }

            if(sum > T) {
                idxB--;
            }
            else if(sum < T) {
                idxA++;
            }
        }

        System.out.println(res);
    }
}
