#include <iostream>
#include <string>
#include "BankSystem.h"
using namespace std;

int main() {

	setlocale(LC_ALL, "Russian");

	BankSystem setBank;

	setBank.createAccount("Ñ÷¸ò1", 15000);
	setBank.createAccount("Ñ÷¸ò2", 25000);

	setBank.processExpenses(0, 5000);
	setBank.processIncome(1, 5000);


	setBank.printAccountDetails();

	setBank.getTotal();

	return 0;

}