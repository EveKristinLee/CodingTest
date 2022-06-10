#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
	vector<int> answer;
	for (int i = 1; i <= yellow ; i++) {
		if (yellow % i == 0) {
			int tmpCnt = 0;
			tmpCnt += (i + 2) * 2;
			tmpCnt += (yellow / i) * 2;
			if (tmpCnt == brown) {
				answer.push_back(i + 2);
				answer.push_back((yellow / i) + 2);
				break;
			}
		}
	}
	sort(answer.begin(), answer.end(), greater<int>());
	return answer;
}

int main(void) {
	int brown = 8;
	int yellow = 1;
	vector<int> result = solution(brown, yellow);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}