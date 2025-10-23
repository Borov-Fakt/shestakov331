#include <iostream>
using namespace std;

int main() {
    setlocale(LC_ALL, "Russian");
    int numbers[10] = { 15, 4, 88, 42, 1, 9, 10, 23, 16, 77 };
    int count = 10;
    int find;
    bool found = false;
    int position = 0;

    for (int i = 0; i < count - 1; i++) {
        for (int j = 0; j < count - i - 1; j++) {
            if (numbers[j] > numbers[j + 1]) {
                int temp = numbers[j];
                numbers[j] = numbers[j + 1];
                numbers[j + 1] = temp;
            }
        }
    }

    cout << "Отсортированный массив: ";
    for (int i = 0; i < count; i++) {
        cout << numbers[i] << " ";
    }
    cout << endl;

    cout << "Введите число для поиска: ";
    cin >> find;

    for (int i = 0; i < count; i++) {
        if (numbers[i] == find) {
            found = true;
            position = i + 1;
            break;
        }
    }

    if (found) {
        cout << "Число найдено его позиция в массиве: " << position << endl;
    }
    else {
        cout << "Число не найдено." << endl;
    }

    return 0;
}