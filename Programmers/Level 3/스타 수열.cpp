#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>

using namespace std;

int solution(std::vector<int> a) {
	int answer = 0;

	if (a.size() < 2) {
		return 0;
	}

	unordered_map<int, int> um;
	for (int i = 0; i < a.size(); i++) {
		um[a[i]]++;
	}

	for (auto data : um) {
		int key = data.first;
		int num = data.second;
		if (num * 2 < answer) {
			continue;
		}

		int len = 0;
		for (int i = 1; i < a.size(); i++) {
			if (a[i - 1] == a[i]) {
				continue;
			}
			if (a[i - 1] != key && a[i] != key) {
				continue;
			}
			len += 2;
			i++;
		}
		answer = max(answer, len);
	}
	return answer;
}

int main(void) {
	vector<int> a = { 0,3,3,0,7,2,0,2,2,0 };
	int result = solution(a);
	cout << result << "\n";

	return 0;
}