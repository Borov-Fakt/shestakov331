#include <iostream>
#include <string>
#include "Bank.h"
using namespace std;

Bank::Bank()
{
    bankPercent = 0.0;
}

Bank::Bank(double precent)
{
    bankPercent = precent;
}

void Bank::setupBank(double percent) {
    bankPercent = percent;
}

double Bank::getBankPercent() {
    return bankPercent;
}

MicroBank::MicroBank(double miniPersent, double pasePersent)
{
    microPercent = miniPersent;
    setupBank(pasePersent);

}

void Bank::GetPercent(double sum) {
    double result = sum * (bankPercent / 100.0);
    cout << "Ваш процент: " << result << " (" << bankPercent << "%) от " << sum << endl;
}
void MicroBank::GetPercent(double sum) {
    double basePercent = getBankPercent();
    double totalPercent = basePercent + microPercent;
    double result = sum * (totalPercent / 100.0);
    cout << "Ваш процент по мини-банку: " << result << " (" << totalPercent << "%) от " << sum << endl;
}