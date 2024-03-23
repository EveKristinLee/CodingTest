package solving.solve_0914;
//BOJ G5 2504 괄호의 값

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(calc(input));
    }

    private static int calc(String input) {
        Stack<Character> st = new Stack<>();
        int tmp = 1;
        int sum = 0;
        for(int i=0; i<input.length(); i++) {
            if(input.charAt(i) == '(') {
                st.push(input.charAt(i));
                tmp *= 2;
            }
            else if(input.charAt(i) == '[') {
                st.push(input.charAt(i));
                tmp *= 3;
            }
            else {
                if(input.charAt(i) == ')') {
                    if(st.isEmpty() || st.peek() != '(') {
                        return 0;
                    }
                    if(input.charAt(i-1) == '(') {
                        sum += tmp;
                    }
                    st.pop();
                    tmp /= 2;
                }
                else if(input.charAt(i) == ']') {
                    if(st.isEmpty() || st.peek() != '[') {
                        return 0;
                    }
                    if(input.charAt(i-1) == '[') {
                        sum += tmp;
                    }
                    st.pop();
                    tmp /= 3;
                }
            }
        }
        if(!st.isEmpty()) {
            return 0;
        }
        return sum;
    }
}
