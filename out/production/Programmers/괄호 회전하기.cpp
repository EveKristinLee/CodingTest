//[문자열 한칸 밀기 참고] https://unluckyjung.github.io/cpp/2020/04/10/Rotate/

#include <iostream>
#include <string>
#include <vector>
#include <stack>

using namespace std;

string shift(string s) {
	string newS = "";

	for (int i = 1; i < s.size(); i++) {
		newS += s[i];
	}
	newS += s[0];
	return newS;
}

int solution(string s) {
	int answer = 0;
	string tmp = s;
	for (int i = 0; i < s.size(); i++) {
		stack<char> st;
		cout << tmp << "\n";
		if (tmp[0] == ']' || tmp[0] == '}' || tmp[0] == ')') {
			tmp = shift(tmp);
			cout << "continue\n";
			continue;
		}
		for (int j = 0; j < s.size(); j++) {
			if (tmp[j] == '[' || tmp[j] == '{' || tmp[j] == '(') {
				st.push(tmp[j]);
			}
			else {
				if (!st.empty()) {
					if (tmp[j] == ']') {
						if (st.top() == '[') {
							st.pop();
						}
					}
					else if (tmp[j] == '}') {
						if (st.top() == '{') {
							st.pop();
						}
					}
					else if (tmp[j] == ')') {
						if (st.top() == '(') {
							st.pop();
						}
					}
				}
			}
		}
		if (st.empty()) {
			answer++;
		}
		else {
			tmp = shift(tmp);
			continue;
		}
		tmp = shift(tmp);
	}

	return answer;
}

int main(void) {
	string s = "}}}";
	int result = solution(s);
	cout << result << "\n";
	return 0;
}