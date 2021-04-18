# Zadanie nr 2 z APRO2

## Tomasz Mycielski (304248)

### Zbiór danych

Jako zbiór danych wybrałem spis transakcji sprzedaży na rynku nieruchomości w hrabstwie Sacramento w Kalifornii w
trzecim tygodniu maja 2008. Plik `Sacramentorealestatetransactions.csv`, który przetwarzam ma 12 kolumn danych:

street | city | zip | state | beds | baths | sq__ft | type | sale_date | price | latitude | longitute
---|---|---|---|---|---|---|---|---|---|---|---

W pliku tym opisane są 942 transakcje.

### Klasa `CSVReader`

Do wczytania pliku utworzona została klasa `CSVReader`. Obiekt tej klasy bierze jako parametr konstruktora ścieżkę do
pliku. Pierwszy wiersz pliku odczytywany jest jako nagłówek, a wartości jego pól danych zapisywane są do tablicy
`String[] headerRow`.

Metoda `streamValues()` w klasie `CSVReader` zwraca strumień wartości wszystkich pól danych z pliku csv. Wartości
odczytywane są za pomocą klasy `Scanner` z wykorzystaniem znaku `,` jako ogranicznika:
```java
        Scanner scanner = new Scanner(new File(filepath)); // filepath to ścieżka do pliku
        scanner.useDelimiter(",");
```
Wartości odczytywane wiersz po wierszu dodawane są do stream buildera aby po odczytaniu całego pliku zostały zwrócone w formie strumienia.
```java
        Stream.Builder<String> builder = Stream.builder();
        while (scanner.hasNextLine()) {
            for (String string :
                scanner.nextLine().split(",")) {
                builder.add(string);
            }
        }
        return builder.build();
```

Metoda `toString()` zwraca informację o zawartości pierwszego wiersza pliku (nagłówka) oraz o liczbie wierszy w pliku.

Ponadto w klasie są jeszcze metody `getColumns()` i `countLines()`, które robią dokładnie to na co wskazuje ich nazwa.

### Klasa `Main`

Po wczytaniu pliku i wypisaniu na konsolę podstawowych informacji o nim wartości jego pól danych zapisywane są ze strumienia do ArrayListy: 
```
ArrayList<String> list = (ArrayList<String>) csvReader.streamValues().collect(Collectors.toList());
```
W takim stanie lista nie nadaje się do wykonywania na niej operacji z danymi. Należy najpierw usunąć pierwsze 
dwanaście pól danych, czyli wiersz nagłówkowy pliku: 
```
list.subList(0, csvReader.getColumns()).clear();
```
Następnie z kolumny `city` wszystkie wartości zapisuję do zbioru `set`. Dzięki temu będę miał wszystkie miasta zapisane w jednej strukturze danych bez powtórzeń.

Aby obliczyć statystyczne dane dla każdego z miast tworzę `HashMap<String, int[]>`. Jej kluczem będą nazwy miast, a wartością tablica trzech integerów (kolejno): 
- sumie kosztu wszystkich zakupionych nieruchomości, 
- ilości transakcji w tym mieście,
- sumie stóp kwadratowych wszystkich zakupionych nieruchomości.
Ceny w pliku podane są jako liczby całkowite, można więc korzystać z typu `int` do przedstawiania ich w programie.
  
