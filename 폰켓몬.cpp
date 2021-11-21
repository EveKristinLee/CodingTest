#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(vector<int> nums)
{
	int answer = 0;
	int cnt = nums.size() / 2; //������ �� �ִ� ������
	//�迭�� ���� �� ��
	sort(nums.begin(), nums.end());
	//nuique�Լ��� �ߺ��� ���ڵ��� �� �ڷ� ���� �� �ű⼭���� ��������
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