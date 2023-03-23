#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> numbers) {
	vector<int> answer;
	for (int i = 0; i < numbers.size(); i++) {
		for (int j = 0; j < numbers.size(); j++) {
			if (i != j) {
				answer.push_back(numbers[i] + numbers[j]);
			}
		}
	}

	sort(answer.begin(), answer.end());
	answer.erase(unique(answer.begin(), answer.end()), answer.end());
	return answer;
}

int main(void) {
	vector<int> numbers = { 2, 1, 3, 4, 1 };
	vector<int> result = solution(numbers);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << "\n";
	}
	return 0;
}