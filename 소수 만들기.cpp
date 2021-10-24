#include <vector>
#include <iostream>
using namespace std;

bool isPrime(int n) {
	if (n < 2) {
		return false;
	}
	else {
		//소수 판별은 n/2까지만 돌려도 판별 가능 -> 경계값
		for (int i = 2; i < n / 2; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}

int solution(vector<int> nums) {
	int answer = 0;
	for (int i = 0; i < nums.size(); i++) {
		for (int j = i + 1; j < nums.size(); j++) {
			for (int k = j + 1; k < nums.size(); k++) {
				int tmp = nums[i] + nums[j] + nums[k];
				if (isPrime(tmp) == true) {
					answer++;
				}
			}
		}
	}

	return answer;
}

int main(void) {
	cout << solution({ 1, 2, 7, 6, 4 }) << "\n";
	return 0;
}