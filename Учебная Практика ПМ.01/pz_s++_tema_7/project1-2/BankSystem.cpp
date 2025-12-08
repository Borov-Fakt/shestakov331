#include <iostream>
#include <string>
#include "BankSystem.h"
using namespace std;

BankSystem::BankSystem()
{
	totalReg = 0.0;
	correntAccount = 0;
}

void BankSystem::createAccount(string aName, double inRemainder)
{
	if (correntAccount < maxAccount) {
		accounts[correntAccount] = Account(aName, inRemainder);
		correntAccount++;
		totalReg += inRemainder;
	}
	else
	{
		cout << "Ошибка" << endl;
	}
}

void BankSystem::processIncome(int index, double amount)
{
	if (index >= 0 && index < correntAccount) {
		if (accounts[index].addIncome(amount)) {
			totalReg += amount;
		}
	}
	else
	{
		cout << "Ошибка" << endl;
	}
}

void BankSystem::processExpenses(int index, double amount)
{
	if (index >= 0 && index < correntAccount) {
		if (accounts[index].addExpenses(amount)) {
			totalReg -= amount;
		}
	}
	else
	{
		cout << "Ошибка" << endl;
	}
}

double BankSystem::getTotal()
{
	return totalReg;
}

void BankSystem::printAccountDetails()
{
	cout << "Все счета: " << endl;
	for (int i = 0; i < correntAccount; i++) {
		accounts[i].printDetails();
	}
}