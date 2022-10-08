#include <iostream>
#include <string>
#include <iostream>

using namespace std;

int tenTo2(string s) {
	int ans = 0;
	int base = 1;
	int mul = 2;
	for (int i = s.size() -1; i >= 0; i--) {
		int tmp = s[i] - '0';
		tmp *= base;
		base *= mul;
		ans += tmp;
	}
	return ans;
}

int main(void) {
	string s;
	cin >> s;
	string ans = "";
	string tmp = "";
	for (int i = s.size() - 1; i >= 0; i--) {
		if (tmp.size() < 3) {
			tmp += s[i];
		}
		if (tmp.size() == 3 || i == 0) {
			reverse(tmp.begin(), tmp.end());
			ans += to_string(tenTo2(tmp));
			tmp = "";
		}
	}
	reverse(ans.begin(), ans.end());
	cout << ans << "\n";
	return 0;
}