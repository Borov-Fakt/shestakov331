#include <iostream>
#include <string>
#include "Bank.h"
using namespace std;

int main() {

	setlocale(LC_ALL, "Russian");

	double testSum = 50000.0;
	Bank* newBank1;
	Bank* newBank2;

	newBank1 = new Bank(10.0);

	newBank1->GetPercent(testSum);

	newBank2 = new Bank(15.0);

	newBank2->GetPercent(testSum);

	return 0;

}