//Programmers Level3 금과 은 운반하기
import java.util.ArrayList;
import java.util.List;

public class 금과_은_운반하기 {
    private static class City {
        int gold;
        int silver;
        int weight;
        int time;

        public City(int gold, int silver, int weight, int time) {
            this.gold = gold;
            this.silver = silver;
            this.weight = weight;
            this.time = time;
        }
    }

    static List<City> city;

    public static long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        int len = g.length;
        city = new ArrayList<>();
        for(int i=0; i<len; i++) {
            city.add(new City(g[i], s[i], w[i], t[i]));
        }
        long back = 400_000_000_000_000L;
        long front = 0L;

        while(front <= back) {
            long mid = (front + back) / 2L;
            if(isPossible(mid, a, b)) {
                back = mid - 1;
            }
            else {
                front = mid + 1;
            }
        }
        return front;
    }

    private static boolean isPossible(long time, int a, int b) {
        long total = 0L;
        long totalGold = 0L;
        long totalSilver = 0L;
        int len = city.size();

        for(int i=0; i<len; i++) {
            long cnt = time / (2L * city.get(i).time);
            if(time % (2L * city.get(i).time) >= city.get(i).time) {
                cnt++;
            }

            long now = Math.min(cnt * city.get(i).weight, city.get(i).gold + city.get(i).silver);

            total += now;
            totalGold += Math.min(now, city.get(i).gold);
            totalSilver += Math.min(now, city.get(i).silver);
        }

        if(total >= a + b && totalGold >= a && totalSilver >= b) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int a = 90;
        int b = 500;
        int[] g = {70, 70, 0};
        int[] s = {0, 0, 500};
        int[] w = {100, 100, 2};
        int[] t = {4, 8, 1};

        System.out.println(solution(a, b, g, s, w, t));
    }
}
