#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main(void) {
	int n;
	cin >> n;
	string answer = "";
	for (int i = 1; i <= n; i++) {
		bool flag = false;
		string tmp = to_string(i);
		for (int j = 0; j < tmp.size(); j++) {
			if ((tmp[j] - '0') % 3 == 0 && tmp[j] != '0') {
				answer += "-";
				flag = true;
			}
		}
		if (!flag) {
			answer += tmp;
		}
		answer += " ";
	}
	cout << answer << "\n";
	return 0;
}