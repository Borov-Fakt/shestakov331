#include <iostream>
#include <string>
#include "Phones.h"
using namespace std;

int main() {
	IPhone ip(2.3, 33);
	Xiaomi xi(2.1, 3);

	string(IPhone::*ShowI)() = &IPhone::ShowIphone;
	string(Xiaomi::*ShowX)() = &Xiaomi::ShowXiaomi;

	cout << (ip.*ShowI)() << endl;
	cout << (xi.*ShowX)() << endl;

	return 0;
}