//[Âü°í] https://ongveloper.tistory.com/65

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int dp[501][501];


int solution(vector<vector<int>> triangle) {
	int answer = 0;
	dp[0][0] = triangle[0][0];
	int height = triangle.size();
	for (int i = 1; i < triangle.size(); i++) {
		for (int j = 0; j < triangle[i].size(); j++) {
			if (j == 0) {
				dp[i][j] = dp[i - 1][j] + triangle[i][j];
			}
			if (j == triangle[i].size() - 1) {
				dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
			}
			else {
				dp[i][j] = triangle[i][j] + max(dp[i - 1][j - 1], dp[i - 1][j]);
			}
		}
	}

	for (int i = 0; i < triangle[height -1].size(); i++) {
		answer = max(answer, dp[height - 1][i]);
	}
	return answer;
}

int main(void) {
	vector<vector<int>> triangle = { {7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5} };
	int result = solution(triangle);
	cout << result << "\n";
	return 0;
}