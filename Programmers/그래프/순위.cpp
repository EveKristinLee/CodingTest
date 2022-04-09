#include<iostream>
#include <string>
#include <vector>

using namespace std;

int solution(int n, vector<vector<int>> results) {
	int answer = 0;
	vector<vector<bool>> fight(n + 1, vector<bool>(n + 1, false));
	
	for (int i = 0; i < results.size(); i++) {
		fight[results[i][0]][results[i][1]] = true;
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			for (int k = 1; k <= n; k++) {
				if (fight[j][i] && fight[i][k]) {
					fight[j][k] = true;
				}
			}
		}
	}

	for (int i = 1; i <= n; i++) {
		int cnt = 0;
		for (int j = 1; j <= n; j++) {
			if (fight[i][j] || fight[j][i]) {
				cnt++;
			}
		}
		if (cnt == n - 1) {
			answer++;
		}
	}
	return answer;
}

int main(void) {
	int n = 5;
	vector<vector<int>> results = { {4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5} };
	int result = solution(n, results);
	cout << result << "\n";
	return 0;
}