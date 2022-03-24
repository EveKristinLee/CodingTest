#include <iostream>
#include <algorithm>

using namespace std;

int main(void) {
	int n;
	int arr[1001];
	int dp[1001];
	int maxLen = 0;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		dp[i] = 1; //자기 자신의 길이 = 1
	}
	//i번째 값보다 더 작은 숫자들이 같은 부분수열의 길이중 
	//가장 긴 부분수열의 길이 + 1 == 가장 긴 증가하는 부분수열의 길이
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) {
			if (arr[i] > arr[j]) {
				dp[i] = max(dp[i], dp[j] + 1);
			}
		}
	}
	//가장 긴 길이 찾기
	for (int i = 0; i < n; i++) {
		maxLen = max(maxLen, dp[i]);
	}
	cout << maxLen << "\n";
	return 0;
}