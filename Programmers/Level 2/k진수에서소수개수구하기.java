
public class k진수에서소수개수구하기 {
    static boolean isPrime(long n) {
        if(n <= 1) {
            return false;
        }
        else if(n == 2) {
            return true;
        }
        for(int i=2; i<=(int) Math.sqrt(n); i++) {
            if(n%i == 0) {
                return false;
            }
        }
        return true; //소수이면 true
    }
    static String turn(int n, int k) {
        String result = "";
        while(n > 0) {
            result += Integer.toString(n%k);
            n /= k;
        }
        StringBuffer sb = new StringBuffer(result);
        result = sb.reverse().toString();
        return result;
    }
    public static int solution(int n, int k) {
        int answer = 0;
        String s = turn(n, k);
        String num = "";
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) != '0') {
                num += s.charAt(i);
            }
            if(s.charAt(i) == '0' || i == (s.length()-1)) {
                if(num!="" && isPrime(Long.parseLong(num))) {
                    answer++;
                }
                num = "";
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        int n = 437674;
        int k = 3;
        int result = solution(n, k);
        System.out.println("result = " + result);
    }
}
