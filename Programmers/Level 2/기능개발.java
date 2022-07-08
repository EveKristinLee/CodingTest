import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 기능개발 {
    static int getTime(int progress, int speed) {
        int restTime = 100 - progress;
        if((restTime % speed) == 0) {
            return restTime/speed;
        }
        return (restTime/speed) + 1;
    }
    public static int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<progresses.length; i++) {
            q.offer(getTime(progresses[i], speeds[i]));
        }
        while(!q.isEmpty()) {
            int cnt = 1;
            int now = q.poll();
            while(!q.isEmpty() && now >= q.peek()) {
                cnt++;
                q.poll();
            }
            list.add(cnt);
        }
        return list.stream().mapToInt(i->i).toArray();
    }
    public static void main(String[] args) {
        int[] progresses = {96, 94};
        int[] speeds = {3, 3};
        int[] result = solution(progresses, speeds);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
