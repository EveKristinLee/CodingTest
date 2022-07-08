import java.util.*;

public class 주차요금계산 {
    public static int[] solution(int[] fees, String[] records) {
        ArrayList<Integer> answer = new ArrayList<>();
        Map<String, ArrayList<Integer>> map = new LinkedHashMap<>();
        List<String> cars = new ArrayList<>();
        int lastTime = 1439; //23:59
        for(int i=0; i<records.length; i++) {
            String[] info = records[i].split(" ");
            if(!map.containsKey(info[1])) {
                map.put(info[1], new ArrayList<>());
                cars.add(info[1]);
            }
            String time = info[0];
            String[] timeInfo = time.split(":");
            int hour = Integer.parseInt(timeInfo[0]);
            int min = Integer.parseInt(timeInfo[1]);
            int totalTime = (hour * 60) + min;
            map.get(info[1]).add(totalTime);
        }
        Collections.sort(cars);
        for (String s : cars) {
            if(map.get(s).size() %2 != 0) {
                map.get(s).add(lastTime);
            }
            int total = 0;
            Integer[] time = map.get(s).toArray(new Integer[0]);
            for (Integer integer : time) {
            }
            for(int i=1; i<time.length; i+=2) {
                total += (time[i] - time[i-1]);
            }
            if(total < fees[0]) {
                answer.add(fees[1]);
            }
            else {
                int totalFee = fees[1];
                total -= fees[0];
                totalFee += (total/fees[2]) * fees[3];
                if(total%fees[2] != 0) {
                    totalFee += fees[3];
                }
                answer.add(totalFee);
            }
        }
        return answer.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] result = solution(fees, records);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
