#include <iostream>
#include <string>
#include "PerishableGoodsLot.h"

void showMenu() {
    std::cout << "1. prodat 1 " << std::endl;
    std::cout << "2. prodat ukazannoe kolichestvo" << std::endl;
    std::cout << "3. spisat 1 " << std::endl;
    std::cout << "4. spisat ukazannoe kolichestvo" << std::endl;
    std::cout << "5. novyy den " << std::endl;
    std::cout << "6. pokazat ostatok" << std::endl;
    std::cout << "7. pokazat polnyy otchet" << std::endl;
    std::cout << "0. vykhod" << std::endl;
    std::cout << "Vash vybor: ";
}

int main() {
    std::string name;
    int quantity, day, month, year, shelf_life;

    std::cout << "vedite naimenovanie tovara: ";
    std::getline(std::cin, name);
    std::cout << "vedite kolichestvo v partii: ";
    std::cin >> quantity;
    std::cout << "vedite datu postavki (den mesyats god): ";
    std::cin >> day >> month >> year;
    std::cout << "vedite srok godnosti v dnyah: ";
    std::cin >> shelf_life;

    PerishableGoodsLot lot;
    lot.init(name, quantity, day, month, year, shelf_life);

    int choice;
    do {
        showMenu();
        std::cin >> choice;
        int amount;

        switch (choice) {
        case 1:
            lot.sell();
            break;
        case 2:
            std::cout << "vedite kolichestvo dlya prodazhi: ";
            std::cin >> amount;
            lot.sell(amount);
            break;
        case 3:
            lot.writeOff();
            break;
        case 4:
            std::cout << "vedite kolichestvo dlya spisaniya: ";
            std::cin >> amount;
            lot.writeOff(amount);
            break;
        case 5:
            lot.nextDay();
            break;
        case 6:
            lot.printRemainder();
            break;
        case 7:
            lot.printFullReport();
            break;
        case 0:
            std::cout << "zavershenie programmy." << std::endl;
            break;
        default:
            std::cout << "nevernyy vybor. poprobuyte eshche raz." << std::endl;
            break;
        }
    } while (choice != 0);

    return 0;
}