import java.util.*;

public class 혼자_놀기의_달인 {
    static int len;
    static int maxSum;
    static boolean[] visit;
    static int cnt;
    static List<Integer> cnts;

    public static int solution(int[] cards) {
        int answer = 0;
        len = cards.length;
        maxSum = Integer.MIN_VALUE;
        visit = new boolean[len];
        cnts = new ArrayList<>();

        for(int i=0; i<len; i++) {
            if(!visit[cards[i]-1]) {
                cnt = 0;
                getMax(i, cards);
                cnts.add(cnt);
            }
        }
        Collections.sort(cnts, Collections.reverseOrder());
        if(cnts.size() >= 2) {
            answer = cnts.get(0) * cnts.get(1);
        }
        return answer;
    }

    private static void getMax(int idx, int[] cards) {
        if(!visit[cards[idx]-1]){
            visit[cards[idx]-1] = true;
            cnt++;
            getMax(cards[idx]-1, cards);
        }
    }

    public static void main(String[] args) {
        int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};
        int result = solution(cards);
        System.out.println(result);
    }
}
