#include <iostream>
#include <vector>

using namespace std;

int main(void) {
	int T;
	cin >> T;
	for (int i = 1; i <= T; i++) {
		int n, d;
		cin >> n >> d;
		int cnt = 0;
		int once = (d * 2) + 1;
		if (n % once != 0) {
			cnt = (n / once) + 1;
		}
		else {
			cnt = n / once;
		}
		cout << "#" << i << " ";
		cout << cnt << "\n";
	}
	return 0;
}