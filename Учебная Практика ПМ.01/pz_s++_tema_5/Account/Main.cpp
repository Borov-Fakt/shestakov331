#include <iostream>
#include <string>
#include "Account.h"
using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");
	Account acc("счёт", 1000);

	acc.print();

	++acc;
	acc.print();

	--acc;
	acc.print();

	acc + 500;
	acc.print();

	acc + (-150);

	acc.print();

	return 0;
}