#include <iostream>
#include <algorithm>

using namespace std;

int dp[100001] = { 0 };

int main(void) {
	int n;
	cin >> n;
	for (int i = 0; i <= n; i++) {
		dp[i] = i;
	}

	for (int i = 4; i <= n; i++) {
		for (int j = 2; j <= i; j++) {
			int jj = j * j;
			if (jj > i) {
				break;
			}
			dp[i] = min(dp[i], dp[i - jj] + 1);
		}
	}
	cout << dp[n] << "\n";
	return 0;
}