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
		answer *= c.second + 1; //�ƹ��͵� ���� ���ϴ� ��츦 ������
	}
	answer--; //�ƹ��͵� ������ ��� ����
	return answer;
}

int main(void) {
	vector<vector<string>> clothes = { {"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"} };
	int result = solution(clothes);
	cout << result << "\n";
	return 0;
}