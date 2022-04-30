#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> d, int budget) {
	int answer = 0;
	int sum = 0;
	sort(d.begin(), d.end());
	for (int i = 0; i < d.size(); i++) {
		if (sum + d[i] <= budget) {
			answer++;
			sum += d[i];
		}
	}
	return answer;
}

int main(void) {
	vector<int> d = { 2, 2, 3, 3 };
	int budget = 10;
	int result = solution(d, budget);
	cout << result << "\n";
	return 0;
}