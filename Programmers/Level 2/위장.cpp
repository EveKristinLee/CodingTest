#include <iostream>
#include <string>
#include <vector>
#include <map>

using namespace std;

int solution(vector<vector<string>> clothes) {
	int answer = 1;
	map<string, int> cloth;
	for (int i = 0; i < clothes.size(); i++) {
		cloth[clothes[i][1]]++;
	}
	for (auto c : cloth) {
		answer *= c.second + 1; //아무것도 선택 안하는 경우를 더해줌
	}
	answer--; //아무것도 안입은 경우 빼줌
	return answer;
}

int main(void) {
	vector<vector<string>> clothes = { {"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"} };
	int result = solution(clothes);
	cout << result << "\n";
	return 0;
}