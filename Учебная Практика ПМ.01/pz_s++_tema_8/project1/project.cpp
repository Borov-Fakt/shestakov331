#include <iostream>
#include <fstream>
#include <string>
using namespace std;
int main() {
    setlocale(LC_ALL, "Russian");

    ifstream inFile;
    inFile.open("input.txt");
    if (!inFile.is_open()) {
        cerr << "Ошибка: Файл input.txt не найден. Создайте его!" << endl;
        return 0;
    }

    string words[40];
    int count = 0;

    while (count < 40 && inFile >> words[count]) {
        if (words[count].length() > 80) {
            words[count] = words[count].substr(0, 80);
        }
        count++;
    }

    inFile.close();

    for (int i = 0; i < count - 1; i++) {
        for (int j = 0; j < count - i - 1; j++) {
            if (words[j] > words[j + 1]) {
                string temp = words[j];
                words[j] = words[j + 1];
                words[j + 1] = temp;
            }
        }
    }

    ofstream outFile;
    outFile.open("output.txt");
    if (!outFile.is_open()) {
        cerr << "Ошибка создания файла output.txt" << endl;
        return 0;
    }

    for (int i = 0; i < count; i++) {
        outFile << words[i] << endl;
    }

    outFile.close();
    cout << "Задание 1 выполнено. Результат в output.txt" << endl;

    return 0;
}