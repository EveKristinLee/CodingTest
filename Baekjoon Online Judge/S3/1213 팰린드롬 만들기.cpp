#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main(void) {
	string name;
	cin >> name;

	int alpha[26] = { 0 };
	for (int i = 0; i < name.size(); i++) {
		alpha[name[i] - 'A']++;
	}

	int cnt = 0;
	int odd_idx = -1;
	for (int i = 0; i < 26; i++) {
		if (alpha[i] != 0 && alpha[i] % 2 != 0) {
			cnt++;
			odd_idx = i;
		}
	}
	if (cnt > 1) {
		cout << "I'm Sorry Hansoo\n";
	}
	else {
		string result = "";
		string tmp;
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < alpha[i] / 2; j++) {
				result += i + 'A';
			}
		}
		tmp = result;
		reverse(tmp.begin(), tmp.end());
		if (odd_idx != -1) {
			result += odd_idx + 'A';
		}
		result += tmp;
		cout << result << "\n";
	}

	return 0;
}