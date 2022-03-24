#include <iostream>

using namespace std;

int main(void) {
	int cnt[3] = { 0 };
	int t;
	cin >> t;

	if (t % 10 != 0) {
		cout << -1 << "\n";
	}
	else {
		while (t != 0) {
			if (t >= 300) {
				t -= 300;
				cnt[0]++;
			}
			else if (t >= 60) {
				t -= 60;
				cnt[1]++;
			}
			else {
				t -= 10;
				cnt[2]++;
			}
		}
		for (int i = 0; i < 3; i++) {
			cout << cnt[i] << " ";
		}
	}
	cout << "\n";
	return 0;
}