#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> numbers;
int target;
int cnt = 0;

void dfs(vector<int> numbers, int sum, int idx, int target) {
	if ((sum == target) && (idx == numbers.size())) {
		cnt++;
		return;
	}
	if ((sum != target) && (idx == numbers.size())) {
		return;
	}

	dfs(numbers, sum + numbers[idx], idx + 1, target);
	dfs(numbers, sum - numbers[idx], idx + 1, target);
}

int solution(vector<int> numbers, int target) {
	dfs(numbers, 0, 0, target);
	return cnt;
}

int main(void) {
	
	numbers = { 1, 1, 1, 1, 1 };
	target = 3;

	int result = solution(numbers, target);
	cout << result << "\n";

	return 0;
}