#include <iostream>
#include <vector>
#include <algorithm>
#define INF 987654321

using namespace std;

int coin[101];
int dp[10001];

int main(void) {
	int n, k;
	cin >> n >> k;

	for (int i = 0; i < n; i++) {
		cin >> coin[i];
	}

	for (int i = 0; i <= k; i++) {
		dp[i] = INF;
	}
	dp[0] = 0;

	for (int i = 0; i < n; i++) {
		for (int j = coin[i]; j <= k; j++) {
			dp[j] = min(dp[j], dp[j - coin[i]] + 1);
		}
	}
	if (dp[k] == INF) {
		cout << "-1\n";
	}
	else {
		cout << dp[k] << "\n";
	}
	return 0;
}