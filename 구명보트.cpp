//[Âü°í] https://hwan-shell.tistory.com/200

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> people, int limit) {
	int answer = 0;
	sort(people.begin(), people.end());
	int idx = 0; //ÃÖ¼Ú°ª idx
	while (people.size() > idx) {
		int back = people.back();
		people.pop_back();

		if (people[idx] + back <= limit) {
			answer++;
			idx++;
		}
		else {
			answer++;
		}
	}
	return answer;
}

int main(void) {
	vector<int> people = { 70, 50, 80, 50 };
	int limit = 100;
	int result = solution(people, limit);
	cout << result << "\n";
	return 0;
}