#include <iostream>

using namespace std;

int main(void) {
	int T;
	cin >> T;
	for (int t = 1; t <= T; t++) {
		int n;
		cin >> n;
		int cnt = 0;
		if (n >= 3) {
			cnt = n / 3;
		}
		cout << "#" << t << " " << cnt << "\n";
	}
	return 0;
}