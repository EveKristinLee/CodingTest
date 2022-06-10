#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> citations) {
	int answer = 0;
	sort(citations.begin(), citations.end(), greater<int>());
	if (citations[0] == 0) {
		return 0;
	}
	for (int i = 0; i < citations.size(); i++) {
		if (i < citations[i]) {
			answer++;
		}
	}
	return answer;
}

int main(void) {
	vector<int> citations = { 3, 0, 6, 1, 5 };
	int result = solution(citations);
	cout << result << "\n";
	return 0;
}