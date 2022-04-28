#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(vector<int> numbers) {
	int answer = -1;
	int total = 45;
	int sum = 0;
	for (int i = 0; i <numbers.size(); i++) {
		sum += numbers[i];
	}
	answer = total - sum;
	return answer;
}

int main(void) {
	int ans = solution({ 5,8,4,0,6,7,9 });
	cout << ans << "\n";
	return 0;
}