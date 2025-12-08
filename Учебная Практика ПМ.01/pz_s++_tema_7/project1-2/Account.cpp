#include <iostream>
#include <string>
#include "Account.h"
using namespace std;

Account::Account()
{
	name = "";
	remainder = 0.0;
	expenses = 0.0;
	income = 0.0;
}

Account::Account(string& aName, double inRemainder)
{
	name = aName;
	remainder = inRemainder;
	expenses = 0.0;
	income = 0.0;

}

bool Account::addExpenses(double amount)
{
	if (amount > 0 && remainder >= amount) {
		expenses += amount;
		remainder -= amount;
		cout << "Счёт: " << name << " расход: " << amount << " остаток: " << remainder << endl;
		return true;
	}
	return false;
}

bool Account::addIncome(double amount)
{
	if (amount > 0) {
		income += amount;
		remainder += amount;
		cout << "Счёт: " << name << " доход: " << amount << " остаток: " << remainder << endl;
		return true;
	}
	return false;
}

double Account::getBalance()
{
	return remainder;
}

void Account::printDetails()
{
	cout << "Cчёт: " << name << endl;
	cout << "Всего остаток: " << remainder << endl;
	cout << "Все расходы: " << expenses << endl;
	cout << "Все доходы: " << income << endl;

}