#Zadanie nr 2 z APRO2
##Tomasz Mycielski (304248)
###Zbiór danych
Jako zbiór danych wybrałem spis transakcji sprzedaży na rynku nieruchomości w hrabstwie Sacramento w Kalifornii w 
trzecim tygodniu maja 2008. Plik .csv, który przetwarzam ma 12 kolumn danych:

street | city | zip | state | beds | baths | sq__ft | type | sale_date | price | latitude | longitute 
---|---|---|---|---|---|---|---|---|---|---|---

W pliku tym opisane są 942 transakcje.

###Wczytywanie pliku .csv
Do wczytania pliku utworzona została klasa `CSVReader`. 