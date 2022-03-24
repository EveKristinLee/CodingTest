//[참고] https://ongveloper.tistory.com/84

#include <iostream>
#include <string>
#include <vector>

using namespace std;

//집의 위치 (1, 1)
//학교의 위치 (m, n)

//m이 가로, n이 세로

int dp[101][101] = { 0 };

int solution(int m, int n, vector<vector<int>> puddles) {
	dp[1][1] = 1;

	for (int i = 0; i < puddles.size(); i++) {
		dp[puddles[i][1]][puddles[i][0]] = -1;
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			int a = 0;
			int b = 0;
			if (dp[i][j] == -1) {
				continue;
			}
			//왼쪽에서 오는 경로의 수
			if (dp[i][j - 1] != -1) {
				a = dp[i][j - 1];
			}
			//위쪽에서 오는 경로의 수
			if (dp[i - 1][j] != -1) {
				b = dp[i - 1][j];
			}

			dp[i][j] += (a + b) % 1000000007;
		}
	}
	return dp[n][m];
}

int main(void) {
	int m = 4;
	int n = 3;
	vector<vector<int>> puddles = { {2, 2} };

	int result = solution(m, n, puddles);
	cout << result << "\n";

	return 0;
}
