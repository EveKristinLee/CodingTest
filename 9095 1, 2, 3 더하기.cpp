#include <iostream>

using namespace std;

int num[12];
int main(void) {
	int T;
	cin >> T;
	num[1] = 1;
	num[2] = 2;
	num[3] = 4;
	for (int t = 0; t < T; t++) {
		int n;
		cin >> n;
		for (int i = 4; i <= n; i++) {
			num[i] = num[i - 1] + num[i - 2] + num[i - 3];
		}
		cout << num[n] << "\n";
	}
	return 0;
}