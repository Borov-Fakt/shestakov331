#include <iostream>
#include <string>
using namespace std;


int main()
{
    setlocale(LC_ALL, "Russian");
    string str = "sdkksdsdrrrsf3qq243a46ww32hgggdfjwess3633333zzzxxx7733jsssssss3eeeettyfcgvhulhhj998";
    char abs;
    int count = 0;

    cout << "Ведите символ для поиска: " << endl;
    cin >> abs;

    for (char i : str) {

        if (i == abs) {
            count++;
        }
    }

    cout << "сторка = " << str << endl;

    if (count > 0) {
        cout << "Символ встречается: " << count << endl;
    }
    else
    {
        cout << "Символ не встречается" << endl;
    }

    return 0;
}
