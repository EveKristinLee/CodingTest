#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(vector<int> nums)
{
	int answer = 0;
	int cnt = nums.size() / 2; //가져갈 수 있는 마리수
	//배열을 정렬 한 후
	sort(nums.begin(), nums.end());
	//nuique함수로 중복된 숫자들을 맨 뒤로 보낸 후 거기서부터 삭제해줌
	nums.erase(unique(nums.begin(), nums.end()), nums.end());
	
	if (cnt <= nums.size()) {
		answer = cnt;
	}
	else {
		answer = nums.size();
	}

	return answer;
}

int main(void) {
	cout << solution({ 3, 1, 2, 3 }) << "\n";
	cout << solution({ 3, 3, 3, 2, 2, 4 }) << "\n";
	cout << solution({ 3, 3, 3, 2, 2, 2 }) << "\n";
	return 0;
}