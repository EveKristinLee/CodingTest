#include <iostream>
#include <string>

using namespace std;

string alter(int num) {
	string ans = "";
	while (num != 0) {
		if (num % 2 == 0) {
			ans += '0';
		}
		else {
			ans += '1';
		}
		num /= 2;
	}
	if (ans.size() < 3) {
		int cnt = 3 - ans.size();
		while (cnt--) {
			ans += '0';
		}
	}
	reverse(ans.begin(), ans.end());
	return ans;
}

int main(void) {
	string ans;
	string s;
	cin >> s;
	if (s == "0") {
		cout << "0" << "\n";
	}
	else {
		for (int i = 0; i < s.size(); i++) {
			ans += alter(s[i] - '0');
		}
		int cnt = 0;
		for (int i = 0; i < ans.size(); i++) {
			if (ans[i] == '0') {
				cnt++;
			}
			if (ans[i] == '1') {
				break;
			}
		}
		if (cnt != 0) {
			ans.erase(0, cnt);
		}
		cout << ans << "\n";
	}
	return 0;
}