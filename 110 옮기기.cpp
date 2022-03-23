#include <iostream>
#include <string>
#include <vector>
#include <stack>

using namespace std;

vector<string> solution(vector<string> str) {
	vector<string> answer;
	for (auto s : str) {
		int cnt110 = 0;
		stack<int> st;
		for (int i = 0; i < s.size(); i++) {
			st.push(s[i]);
			if (st.size() >= 3) {
				char three = st.top();
				st.pop();
				char two = st.top();
				st.pop();
				char one = st.top();
				st.pop();

				if (one == '1' && two == '1' && three == '0') {
					cnt110++;
					continue;
				}
				else {
					st.push(one);
					st.push(two);
					st.push(three);
				}
			}
		}
		if (cnt110 == 0) {
			answer.push_back(s);
			continue;
		}

		string a = "";
		while (!st.empty()) {
			a += st.top();
			st.pop();
		}
		reverse(a.begin(), a.end());

		bool flag = false;
		int idx0 = 0;
		for (int i = a.size() - 1; i >= 0; i--) {
			if (a[i] == '0') {
				idx0 = i;
				flag = true;
				break;
			}
		}
		if (!flag) {
			for (int i = 0; i < cnt110; i++) {
				a.insert(0, "110");
			}
		}
		else {
			for (int i = 0; i < cnt110; i++) {
				a.insert(idx0 + 1, "110");
			}
		}
		answer.push_back(a);
	}
	return answer;
}

int main(void) {
	vector<string> s = { "1110","100111100","0111111010" };
	vector<string> result = solution(s);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}