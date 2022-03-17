#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<int> sticker)
{
	int answer = 0;
	vector<int> dpZero(sticker.size(), 0);
	vector<int> dpFirst(sticker.size(), 0);
	if (sticker.size() == 1) {
		return sticker[0];
	}
	dpZero[0] = sticker[0];
	dpZero[1] = sticker[0];
	dpFirst[0] = 0;
	dpFirst[1] = sticker[1];
	
	for (int i = 2; i < sticker.size(); i++) {
		if (i != sticker.size() - 1) {
			dpZero[i] = max(dpZero[i - 2] + sticker[i], dpZero[i - 1]);
			answer = dpZero[i];
		}
		dpFirst[i] = max(dpFirst[i - 2] + sticker[i], dpFirst[i - 1]);
	}
	
	answer = max(answer, dpFirst[sticker.size() -1]);
	return answer;
}

int main(void) {
	vector<int> sticker = { 14, 6, 5, 11, 3, 9, 2, 10 };
	int result = solution(sticker);
	cout << result << "\n";
	return 0;
}