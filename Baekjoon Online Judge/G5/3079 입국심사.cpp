#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

long long solution(int n, vector<int> times) {
	long long answer = 0;
	long long front = 1;
	long long maxTime = *max_element(times.begin(), times.end());
	long long back = maxTime * n;

	while (front <= back) {
		long long mid = (front + back) / 2;
		long long cnt = 0;
		for (int i = 0; i < times.size(); i++) {
			cnt += mid / times[i];
		}

		if (cnt < n) {
			front = mid + 1;
		}
		else {
			if (mid <= back) {
				answer = mid;
			}
			back = mid - 1;
		}
	}
	return answer;
}

int main(void) {
	int n = 6;
	vector<int> times = { 7, 10 };
	long long result = solution(n, times);
	cout << result << "\n";
	return 0;
}