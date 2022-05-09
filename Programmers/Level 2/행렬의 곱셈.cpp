#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<int>> solution(vector<vector<int>> arr1, vector<vector<int>> arr2) {
	vector<vector<int>> answer;
	for (int i = 0; i < arr1.size(); i++) {
		vector<int> tmp;
		for (int j = 0; j < arr2[0].size(); j++) {
			int sum = 0;
			for (int k = 0; k < arr1[0].size(); k++) {
				sum += arr1[i][k] * arr2[k][j];
			}
			tmp.push_back(sum);
		}
		answer.push_back(tmp);
	}
	return answer;
}

int main(void) {
	vector<vector<int>> arr1 = { {1, 4}, {3, 2}, {4, 1} };
	vector<vector<int>> arr2 = { {3, 3}, {3, 3} };
	vector<vector<int>> result = solution(arr1, arr2);
	for (int i = 0; i < result.size(); i++) {
		for (int j = 0; j < result[i].size(); j++) {
			cout << result[i][j] << " ";
		}
		cout << "\n";
	}
	return 0;
}