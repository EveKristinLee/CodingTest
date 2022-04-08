#include <iostream>
#include <string>
#include <stack>
using namespace std;

int solution(string s)
{
	int answer = 1;
	stack<int> st;
	for (int i = 0; i < s.size(); i++) {
		if (!st.empty()) {
			if (st.top() == s[i]) {
				st.pop();
			}
			else {
				st.push(s[i]);
			}
		}
		else {
			st.push(s[i]);
		}
	}

	if (!st.empty()) {
		answer = 0;
	}
	return answer;
}

int main(void) {
	string s = "baabaa";
	int result = solution(s);
	cout << result << "\n";
	return 0;
}