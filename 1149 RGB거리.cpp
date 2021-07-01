#include <iostream>
#include <algorithm>

using namespace std;

int main(void) {
	int cost[1001][3];  //�ּҺ��
	int color[1001][3]; //�� ���� ���
	int N;
	cin >> N;
	
	//�� �������� �� ��� �Է�
	for (int i = 1; i <= N; i++) {
		cin >> color[i][0] >> color[i][1] >> color[i][2];
	}
	//���� ���������� ĥ�������� ���� ���� �Ķ����̳� �ʷϻ����� ĥ������ �ϰ�
	//�ʷϻ����� ĥ�������� ���� ���� �������̳� �Ķ������� ĥ������ �ϰ�
	//�Ķ������� ĥ�������� ���� ���� �������̳� �ʷϻ����� ĥ������ �Ѵ�.
	cost[1][0] = color[1][0];
	cost[1][1] = color[1][1];
	cost[1][2] = color[1][2];
	for (int i = 2; i <= N; i++) {
		cost[i][0] = color[i][0] + min(cost[i - 1][1], cost[i - 1][2]);
		cost[i][1] = color[i][1] + min(cost[i - 1][0], cost[i - 1][2]);
		cost[i][2] = color[i][2] + min(cost[i - 1][1], cost[i - 1][0]);
	}
	cout << min(cost[N][0], min(cost[N][1], cost[N][2]));
	return 0;
}