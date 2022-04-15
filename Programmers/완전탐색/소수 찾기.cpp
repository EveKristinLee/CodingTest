#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> combi;
int visit[8] = { false };

void getCombi(string numbers, int cnt, string tmp) {
	if (tmp.size() == cnt) {
		combi.push_back(stoi(tmp));
		return;
	}
	for (int i = 0; i < numbers.size(); i++) {
		if (!visit[i]) {
			visit[i] = true;
			getCombi(numbers, cnt, tmp + numbers[i]);
			visit[i] = false;
		}
	}
}

bool isPrime(int num) {
	for (int i = 2; i < num; i++) {
		if (num % i == 0) {
			return false;
		}
	}
	return true;
}

int solution(string numbers) {
	int answer = 0;

	for (int i = 1; i <= numbers.size(); i++) {
		getCombi(numbers, i, "");
	}

	sort(combi.begin(), combi.end());
	combi.erase(unique(combi.begin(), combi.end()), combi.end());

	for (int i = 0; i < combi.size(); i++) {
		if (isPrime(combi[i]) && combi[i] > 1) {
			answer++;
		}
	}

	return answer;
}

int main(void) {
	string numbers = "17";
	int result = solution(numbers);
	cout << result << "\n";
	return 0;
}