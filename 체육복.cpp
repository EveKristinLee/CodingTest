#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
	int answer = 0;
	vector<int> cloth(n, 1);
	//여분을 가지고 있는 사람
	for (int i = 0; i < reserve.size(); i++) {
		cloth[reserve[i] - 1]++;
	}
	//체육복을 잃어버린 사람
	for (int i = 0; i < lost.size(); i++) {
		cloth[lost[i] - 1]--;
	}
	//체육복 빌려주기
	for (int i = 0; i < cloth.size(); i++) {
		if (cloth[i] == 0) {
			//앞사람 먼저 확인
			if (i && cloth[i - 1] == 2) {
				cloth[i - 1]--;
				cloth[i]++;
			}
			//뒷사람 확인
			else if (i != cloth.size() - 1 && cloth[i + 1] == 2) {
				cloth[i + 1]--;
				cloth[i]++;
			}
		}
	}
	for (int i = 0; i < cloth.size(); i++) {
		if (cloth[i] != 0) {
			answer++;
		}
	}
	return answer;
}

int main(void){
	cout << solution(3, { 3 }, { 1 }) << "\n";
	return 0;
}