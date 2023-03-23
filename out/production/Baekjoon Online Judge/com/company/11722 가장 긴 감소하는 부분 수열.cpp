#include <iostream>

using namespace std;

int dp[1001];
int num[1001];

int main(void) {
	int n;
	cin >> n;
	int maxLen = 0;
	for (int i = 1; i <= n; i++) {
		cin >> num[i];
	}

	for (int i = 1; i <= n; i++) {
		dp[i] = 1;
		for (int j = 1; j < i; j++) {
			if (num[j] > num[i] && dp[i] < dp[j] + 1) {
				dp[i] = dp[j] + 1;
			}
		}
		if (maxLen < dp[i]) {
			maxLen = dp[i];
		}
	}
	cout << maxLen << "\n";
	return 0;
}