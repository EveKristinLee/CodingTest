//BOJ G4
package solving.solve_1028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 문자열_폭발_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        String pattern = br.readLine();
        int len = pattern.length();

        Stack<Character> st = new Stack<>();
        for(int i=0; i<origin.length(); i++) {
            st.push(origin.charAt(i));
            if(st.size() >= len) { //스택에 있는 문자의 수가 폭발 문자열의 길이보다 크다면
                boolean eq = true;
                for(int j=0; j<len; j++) {
                    //폭발 문자열과 다르다면
                    if(st.get(st.size() - len + j) != pattern.charAt(j)) {
                        eq = false;
                        break;
                    }
                }

                if(eq) {
                    for(int j=0; j<len; j++) {
                        st.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<st.size(); i++) {
            sb.append(st.get(i));
        }
        if(st.size() == 0) {
            System.out.println("FRULA");
        }
        else{
            System.out.println(sb);
        }
    }
}
