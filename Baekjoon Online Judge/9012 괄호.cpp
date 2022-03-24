#include <iostream>
#include <stack>
#include <string>

using namespace std;

bool isVPS(string s) {
	stack<char> st;

	for (int i = 0; i < s.size(); i++) {
		if (s[i] == '(') {
			st.push(s[i]);
		}
		else {
			if (!st.empty()) {
				st.pop();
			}
			else {
				return false;
			}
		}
	}
	if (st.empty()) {
		return true;
	}
	else {
		return false;
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int T;
	cin >> T;
	for (int t = 0; t < T; t++) {
		string input;
		cin >> input;
		if (isVPS(input) == true) {
			cout << "YES" << "\n";
		}
		else {
			cout << "NO" << "\n";
		}
	}
}