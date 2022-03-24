#include <iostream>

using namespace std;

int main(void) {
	int n;
	int ans = 0;
	string input = "";

	cin >> n;
	for (int i = 0; i < n; i++) {
		bool alpha[27] = { false, };
		bool group = true;
		cin >> input;
		for (int j = 0; j < input.size(); j++) {
			if (input[j] != input[j + 1]) {
				if (alpha[input[j] - 'a']) {
					group = false;
					break;
				}
				else {
					alpha[input[j] - 'a'] = true;
				}
			}
		}
		if (group == true) {
			ans++;
		}
	}
	cout << ans++ << "\n";
	return 0;
}