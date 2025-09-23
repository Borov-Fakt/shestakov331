#include <iostream>
#include <string>
#include "CommodityLot.h"

void runTestScenario1(CommodityLot& lot) {
    std::cout << "\ntesta 1" << std::endl;
    lot.sell(10);
    lot.sell(5);
    lot.writeOff(2);
    lot.sell();
}

void runTestScenario2(CommodityLot& lot) {
    std::cout << "\ntesta 2" << std::endl;
    lot.sell(50);
    lot.writeOff(10);
    lot.sell(40);
    lot.writeOff();
}

void runTestScenario3(CommodityLot& lot) {
    std::cout << "\ntesta 3" << std::endl;
    lot.writeOff(5);
    lot.writeOff(5);
    lot.sell(2);
    lot.sell(8);
}

int main() {
    std::string name;
    int quantity, day, month, year;

    std::cout << "vedite naimenovanie tovara: ";
    std::getline(std::cin, name);
    std::cout << "vedite kolichestvo partii: ";
    std::cin >> quantity;
    std::cout << "vedite datu postavki (den mesyats god): ";
    std::cin >> day >> month >> year;

    CommodityLot lot;
    lot.init(name, quantity, day, month, year);

    int testNumber;
    std::cout << "\nviberite nomer testa dlya zapuska 1, 2, 3: ";
    std::cin >> testNumber;

    switch (testNumber) {
    case 1:
        runTestScenario1(lot);
        break;
    case 2:
        runTestScenario2(lot);
        break;
    case 3:
        runTestScenario3(lot);
        break;
    default:
        std::cout << "nevernyy nomer" << std::endl;
        break;
    }

    std::cout << "\nitogovye dannye" << std::endl;
    lot.printRemainder();
    lot.printFullReport();

    return 0;
}
