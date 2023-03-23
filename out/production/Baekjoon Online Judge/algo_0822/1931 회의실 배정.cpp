#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<pair<int, int>> com; //ȸ�ǽð�
int cnt = 0; //�ִ� ȸ�� ��

//�ִ� ȸ�Ǽ��� ���ؾ��ϱ� ������ ���۽ð��� �ƴ� ����ð��� �������� ����
//����ð��� �������� ������������ ����
//����ð��� ���� ��� ���۽ð��� �������� ������������ �����Ѵ�.
bool comp(pair<int, int> a, pair<int, int> b) {
	if (a.second != b.second) {
		return b.second > a.second;
	}
	else {
		return b.first > a.first;
	}
}

int main(void) {
	int N; //��ü ȸ�Ǽ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int start, end;
		cin >> start >> end;
		com.push_back(make_pair(start, end));
	}
	//ȸ�ǽð� ����
	sort(com.begin(), com.end(), comp);
	int endTime = com[0].second;
	cnt = 1;
	//�� ȸ���� ���� �ð����� ū ���۽ð��̶�� ȸ�� ���� ����
	for (int i = 1; i <= N; i++) {
		if (com[i].first >= endTime) {
			cnt++;
			endTime = com[i].second;
		}
	}
	cout << cnt << "\n";
	return 0;
}