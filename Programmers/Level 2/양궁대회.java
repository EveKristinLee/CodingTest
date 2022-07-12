public class 양궁대회 {
    static int maxScore = -1000;
    static int[] res = {-1};
    static int[] lion;

    public static void dfs(int[] info, int depth, int n) {
        if(depth == n) {
            int lionScore = 0;
            int apeachScore = 0;
            for(int i=0; i<=10; i++) {
                if(info[i] != 0 || lion[i] != 0) {
                    if(lion[i] > info[i]) {
                        lionScore += (10 - i);
                    }
                    else {
                        apeachScore += (10 - i);
                    }
                }
            }
            if(lionScore > apeachScore) {
                if(lionScore - apeachScore >= maxScore) {
                    res = lion.clone();
                    maxScore = lionScore - apeachScore;
                }
            }
            return;
        }
        for(int i=0; i<=10 && lion[i] <= info[i]; i++) {
            lion[i]++;
            dfs(info, depth+1, n);
            lion[i]--;
        }
    }
    public static int[] solution(int n, int[] info) {
        lion = new int[11];
        dfs(info, 0, n);
        return res;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] info = {2,1,1,1,0,0,0,0,0,0,0};
        int[] result = solution(n, info);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
