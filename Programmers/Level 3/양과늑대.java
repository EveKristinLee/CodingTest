import java.util.*;

public class 양과늑대 {
    static int maxCnt = 0;
    static Map<Integer, List<Integer>> tree;
    public static void dfs(int now, int s, int w, int[] info, List<Integer> list){
        if(info[now] == 0) {
            s+=1;
        }
        else {
            w+=1;
        }
        if(w >= s) {
            return;
        }
        maxCnt = Math.max(s, maxCnt);

        List<Integer> next = new ArrayList<>();
        next.addAll(list);
        if(tree.containsKey(now)) {
            next.addAll(tree.get(now));
        }
        next.remove(Integer.valueOf(now));
        for (Integer i : next) {
            dfs(i, s, w, info, next);
        }
    }
    public static int solution(int[] info, int[][] edges) {
        tree = new HashMap<>();
        for (int[] edge : edges) {
            if(!tree.containsKey(edge[0])) {
                tree.put(edge[0], new ArrayList<>());
            }
            tree.get(edge[0]).add(edge[1]);
        }
        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, 0, 0, info, list);
        return maxCnt;
    }
    public static void main(String[] args) {
        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        int result = solution(info, edges);
        System.out.println("result = " + result);
    }
}
