#include <iostream>

using namespace std;

int dp[100001] = { 0 };

int fibo(int num) {
	if (num == 0) {
		return 0;
	}
	if (num == 1) {
		return 1;
	}
	else if (num != 0 && dp[num] != 0) {
		return dp[num];
	}
	return dp[num] = (fibo(num - 2) + fibo(num - 1)) % 1234567;
}

int solution(int n) {
	int answer = 0;
	dp[0] = 0;
	dp[1] = 1;
	answer = fibo(n);
	answer %= 1234567;
	return answer;
}

int main(void) {
	int n = 5;
	int result = solution(n);
	cout << result << "\n";
	return 0;
}