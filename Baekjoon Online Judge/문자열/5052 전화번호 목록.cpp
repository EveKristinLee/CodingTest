#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int main(void) {
	int T;
	cin >> T;
	while (T--) {
		int n;
		cin >> n;
		vector<string> number;
		for (int i = 0; i < n; i++) {
			string s;
			cin >> s;
			number.push_back(s);
		}

		bool flag = false;
		sort(number.begin(), number.end());
		for (int i = 1; i < number.size(); i++) {
			string first = number[i - 1];
			string sec = number[i];

			if (sec.substr(0, first.size()) == first) {
				flag = true;
				break;
			}
		}

		if (flag) {
			cout << "NO\n";
		}
		else {
			cout << "YES\n";
		}
	}

	return 0;
}