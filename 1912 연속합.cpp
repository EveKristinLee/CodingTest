#include <iostream>
#include <algorithm>

using namespace std;
int n;
int arr[100001];
int dp[100001]{ 0 };
int main(void) {
	cin >> n;
	int ans = 0;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		dp[i] = arr[i];
	}
	ans = dp[0];
	for (int i = 1; i < n; i++) {
		dp[i] = max(dp[i], dp[i - 1] + arr[i]);
		if (ans < dp[i]) {
			ans = dp[i];
		}
	}
	cout << ans << "\n";
	return 0;
}