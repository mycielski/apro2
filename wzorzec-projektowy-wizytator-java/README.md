#Wizytator (Odwiedzający)

Wizytator jest używany, gdy chcemy wykonać jakąś operację na grupie podobnych obiektów. Przy pomocy wizytatora możemy przenieść logikę danej operacji poza obiekty, których ona dotyczy.

W załączonym przykładzie wizytator został wykorzystany do pobrania cen odwiedzanych produktów i zwrócenia ich po naliczeniu odpowiedniego rabatu. 

Interfejs ```Dessert``` jest implementowany przez klasy opisujące produkty, które może odwiedzać wizytator.

Klasy ```Cake``` i ```Cookie``` opisują produkty w cukierni i implementują ```Dessert```.

Interfejs ```SweetsBoughtVisitor``` zawiera metody ```visit``` dla argumentu ```Cake``` oraz ```Cookie```.

Klasa ```PricesWithDiscount``` implementuje powyższy interfejs, oraz zawiera rabaty przyznawane niektórym produktom w metodach ```visit``` z flagą ```@Override```.

Wreszcie w klasie ```Main``` znajduje się kod prezentujący działanie tej implementacji wizytatora.