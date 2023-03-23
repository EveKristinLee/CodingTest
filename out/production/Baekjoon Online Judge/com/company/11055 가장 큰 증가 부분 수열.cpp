#include <iostream>
#include <vector>

using namespace std;

int dp[1001] = { 0 };
int nums[1001] = { 0 };

int main(void) {
	int n;
	cin >> n;
	int maxSum = 0;
	for (int i = 1; i <= n; i++) {
		cin >> nums[i];
	}
	for (int i = 1; i <= n; i++) {
		dp[i] = nums[i];
		for (int j = 1; j < i; j++) {
			if (nums[j] < nums[i] && dp[i] < nums[i] + dp[j]) {
				dp[i] = nums[i] + dp[j];
			}
		}
		if (maxSum < dp[i]) {
			maxSum = dp[i];
		}
	}

	cout << maxSum << "\n";
	return 0;
}