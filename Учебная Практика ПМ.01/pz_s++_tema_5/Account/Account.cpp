#include <iostream>
#include <string>
#include "Account.h"
using namespace std;

Account::Account(string names, double remainders) {
	name = names;
	remainder = remainders;
	expense = 0;
	income = 0;
}
void Account::print() {
	cout << "Название: " << name << endl;
	cout << "Баланс: " << remainder << endl;
	cout << "Расход: " << expense << endl;
	cout << "Доход: " << income << endl;
 }

Account Account::operator++() {
	income += 1;
	remainder += 1;
	return *this;
}

Account Account::operator--() {
	expense += 1;
	remainder -= 1;
	return *this;
}

void Account::operator+(double value) {
	if (value > 0) {
		income += value;
		remainder += value;
	}
	else if (value < 0) {
		double valueTwo = value * -1;
		expense += valueTwo;
		remainder += value;
	}
}