#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool bs(vector<int> stones, int k, int mid) {
	int cnt = 0;
	for (int i = 0; i < stones.size(); i++) {
		if (stones[i] - mid <= 0) {
			cnt++;
		}
		else {
			cnt = 0;
		}
		if (cnt >= k) {
			return true;
		}
	}
	return false;
}

int solution(vector<int> stones, int k) {
	int front = 1;
	int back = *max_element(stones.begin(), stones.end());
	
	while (front <= back) {
		int mid = (front + back) / 2;

		if (bs(stones, k, mid)) {
			back = mid - 1;
		}
		else {
			front = mid + 1;
		}
		
	}

	return front;
}

int main(void) {
	vector<int> stones = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
	int k = 3;
	int result = solution(stones, k);
	cout << result << "\n";
	return 0;
}