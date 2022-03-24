#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(vector<int> absolutes, vector<bool> signs) {
	int answer = 0;
	for (int i = 0; i < absolutes.size(); i++) {
		if (signs[i] == true) {
			answer += absolutes[i];
		}
		else {
			answer -= absolutes[i];
		}
	}
	return answer;
}

int main(void) {
	cout << solution({ 1,2,3 }, { false,false,true }) << "\n";
	return 0;
}