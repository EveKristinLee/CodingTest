import java.util.*;
public class 광물_캐기 {

    static int[][] rate = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    static int[] digger;
    static int tire;

    public static int solution(int[] picks, String[] minerals) {
        int answer = 0;
        digger = new int[picks[0]+picks[1]+picks[2]];
        int idx = 0;
        tire = Integer.MAX_VALUE;
        for(int i=0; i<picks.length; i++) {
            for(int j=0; j<picks[i]; j++) {
                digger[idx++] = i;
            }
        }
        Arrays.sort(digger);

        // int cnt = 0;
        do{
            // System.out.println(Arrays.toString(digger));
            // cnt++;
            // if(cnt == 2) {
            //     break;
            // }
            int nowTire = 0;
            int cnt = 0;
            int index = 0;
            for(int i=0; i<minerals.length; i++) {
                int m = -1;
                if(minerals[i].equals("diamond")) {
                    m = 0;
                }
                else if(minerals[i].equals("iron")) {
                    m = 1;
                }
                else if(minerals[i].equals("stone")) {
                    m = 2;
                }
                nowTire += rate[digger[index]][m];
                cnt++;
                if(cnt == 5) {
                    index++;
                    if(index >= digger.length) {
                        break;
                    }
                    cnt=0;
                }
            }
            tire = Math.min(nowTire, tire);
        }while(next_perm());
        return tire;
    }

    public static void main(String[] args) {
        int[] picks = {0, 1, 1};
        String[] minerals = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
        System.out.println(solution(picks, minerals));
    }

    private static boolean next_perm() {
        int i = digger.length-1;
        while(i>0 && digger[i-1]>=digger[i]) --i;
        if(i == 0) return false;

        int j = digger.length-1;
        while(digger[i-1] >= digger[j]) --j;
        swap(digger, i-1, j);

        int k = digger.length-1;
        while(i<k) swap(digger, i++, k--);
        return true;
    }

    private static void swap(int[] origin, int i, int j) {
        int tmp = origin[i];
        origin[i] = origin[j];
        origin[j] = tmp;
    }
}
