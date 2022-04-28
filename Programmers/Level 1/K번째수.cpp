#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;


vector<int> solution(vector<int> array1, vector<vector<int>> commands) {
	vector<int> answer;

	for (int t = 0; t < commands.size(); t++) {
		int i = commands[t][0] - 1;
		int j = commands[t][1] - 1;
		int k = commands[t][2] - 1;
		vector<int> tmp;
		for (int a = i; a <= j; a++) {
			tmp.push_back(array1[a]);
		}
		sort(tmp.begin(), tmp.end());

		answer.push_back(tmp[k]);
	}

	return answer;
}

int main(void) {
	vector<int> a = solution({ 1, 5, 2, 6, 3, 7, 4 }, { {2, 5, 3}, {4, 4, 1}, {1, 7, 3} });
	cout << "[";
	for (int i = 0; i < a.size(); i++) {
		if (i < a.size() - 1) {
			cout << a[i] << "," << " ";
		}
		else {
			cout << a[i] << "]";
		}
	}
	return 0;
}