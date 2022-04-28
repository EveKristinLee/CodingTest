#include <string>
#include <vector>
#include <iostream>

using namespace std;

int ranking(int rank) {
	int result = 0;
	switch (rank)
	{
	case 6:
		result = 1;
		break;
	case 5:
		result = 2;
		break;
	case 4:
		result = 3;
		break;
	case 3:
		result = 4;
		break;
	case 2:
		result = 5;
		break;
	default:
		result = 6;
		break;
	}
	return result;
}

vector<int> solution(vector<int> lottos, vector<int> win_nums) {
	vector<int> answer;
	vector<pair<int, bool>> all_num;
	for (int i = 0; i <45 ; i++) {
		all_num.push_back(make_pair(i + 1, false));
	}
	for (int i = 0; i < 6; i++) {
		all_num[win_nums[i] - 1].second = true;
	}
	//ÃÖÀú
	int cnt = 0;
	int zero = 0;
	for (int i = 0; i < 6; i++) {
		if (lottos[i] != 0) {
			if (all_num[lottos[i] - 1].second == true) {
				cnt++;
			}
		}
		else {
			zero++;
		}

	}
	answer.push_back(ranking(cnt + zero));
	answer.push_back(ranking(cnt));
	return answer;
}

int main(void) {
	vector<int> ans;
	ans = solution({ 45, 4, 35, 20, 3, 9 }, { 20, 9, 3, 45, 4, 35 });
	for (int i = 0; i < ans.size(); i++) {
		cout << ans[i] << " ";
	}
	return 0;
}