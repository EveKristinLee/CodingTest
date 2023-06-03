public class 택배_배달과_수거하기 {
    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int d = 0;
        int p = 0;

        //가장 먼곳부터
        for(int i=n-1; i>=0; i--) {
            if(deliveries[i] != 0 || pickups[i] != 0) {
                int cnt = 0;
                while(d < deliveries[i] || p < pickups[i]) {
                    cnt++;
                    d += cap;
                    p += cap;
                }

                d -= deliveries[i];
                p -= pickups[i];
                answer += (i+1) * cnt * 2;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int cap = 4;
        int n = 5;
        int[] deliveries = {1,0, 3, 1, 2};
        int[] pickups = {0, 3, 0, 4, 0};
        long res = solution(cap, n, deliveries, pickups);
        System.out.println(res);
    }
}
