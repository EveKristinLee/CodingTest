package solving.solve_0914;
//BOJ G5 2023 신기한 소수

import java.util.Scanner;

public class BOJ_2023 {
    static int N;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sb = new StringBuilder();

        for(int i=2; i<10; i++) {
            if(isPrime(i)) {
                findNum(Integer.toString(i));
            }
        }
        System.out.println(sb);
    }

    private static void findNum(String num) {
        if(num.length() == N) {
            if(isPrime(Integer.parseInt(num))) {
                sb.append(num).append("\n");
            }
            return;
        }

        for(int i=0; i<10; i++) {
            String tmp = num + Integer.toString(i);
            if(isPrime(Integer.parseInt(tmp))) {
                findNum(tmp);
            }
        }
    }

    private static boolean isPrime(int num) {
        for(int i=2; i<=(int)Math.sqrt(num); i++) {
            if(num%i == 0) {
                return false;
            }
        }
        return true;
    }
}
