# APRO2 Lab5 Sprawozdanie

###### Tomasz Mycielski (304248)

## Zadanie 1

Na potrzeby zadania stworzyłem dwie klasy:
- `ConcatenatorPlusEquals`:
```java
package com.company.z1;

public class ConcatenatorPlusEquals {

    public static void main(String[] args) {
        String concatenation;
        concatenation = "String1" + "String2";
        System.out.println(concatenation);
    }
}
```
- `ConcatenatorStringBuilder`:
```java
package com.company.z1;

public class ConcatenatorStringBuilder {
    public static void main(String[] args) {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        stringBuilder.append("String1");
        stringBuilder.append("String2");
        String string;
        string = stringBuilder.toString();
        System.out.println(string);
    }
}
```

Wywołanie komendy `javap` na plikach `.class`, do których zostały skompilowane powyższe programy, wygenerowało następujący output:

- dla `ConcatenatorPlusEquals`:
```text
Compiled from "ConcatenatorPlusEquals.java"
public class com.company.z1.ConcatenatorPlusEquals {
  public com.company.z1.ConcatenatorPlusEquals();
  public static void main(java.lang.String[]);
}
```

- dla 'ConcatenatorStringBuilder':
```text
Compiled from "ConcatenatorStringBuilder.java"
public class com.company.z1.ConcatenatorStringBuilder {
  public com.company.z1.ConcatenatorStringBuilder();
  public static void main(java.lang.String[]);
}
```

Z powyższego wywnioskować można niewiele. Wiemy, że obydwa programy mają podobną strukturę — jest w nich tylko metoda `main(String[])`.

Program `javap` daje nam jednak do dyspozycji jeszcze opcję Disassemble the code, oznaczoną `-c`. Po wywołaniu komendy `javap -c` na tych samych plikach możemy dowiedzieć się o wiele więcej o programie:
- `ConcatenatorPlusEquals`
```text
Compiled from "ConcatenatorPlusEquals.java"
public class com.company.z1.ConcatenatorPlusEquals {
  public com.company.z1.ConcatenatorPlusEquals();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: ldc           #7                  // String String1
       2: astore_1
       3: aload_1
       4: invokedynamic #9,  0              // InvokeDynamic #0:makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
       9: astore_1
      10: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
      13: aload_1
      14: invokevirtual #19                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      17: return
}
```
- `ConcatenatorStringBuilder`
```text
Compiled from "ConcatenatorStringBuilder.java"
public class com.company.z1.ConcatenatorStringBuilder {
  public com.company.z1.ConcatenatorStringBuilder();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #7                  // class java/lang/StringBuilder
       3: dup
       4: invokespecial #9                  // Method java/lang/StringBuilder."<init>":()V
       7: astore_1
       8: aload_1
       9: ldc           #10                 // String String1
      11: invokevirtual #12                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      14: pop
      15: aload_1
      16: ldc           #16                 // String String2
      18: invokevirtual #12                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      21: pop
      22: aload_1
      23: invokevirtual #18                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      26: astore_2
      27: getstatic     #22                 // Field java/lang/System.out:Ljava/io/PrintStream;
      30: aload_2
      31: invokevirtual #28                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      34: return
}
```
Otrzymaliśmy kod bajtowy Javy. Korzystając z jego dokumentacji dostępnej na [stronie](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html) Oracle, możemy sporo dowiedzieć się o programie. Jednak już na pierwszy rzut oka widać, w której wersji programu wykorzystany został `StringBuilder`, a w której `+` do konkatenacji dwóch stringów. 

Moją szczególną uwagę zwrócił fakt, że w przypadku programu korzystającego ze `StringBuilder` możemy odczytać obydwa ciągi znaków, które ze sobą konkatenujemy, a w programie korzystającym z `+=` tylko drugi z nich.

Pliki `.class`, które podawałem do `javap` dostępne są w [lab5/out/production/lab5/com/company/z1/](./out/production/lab5/com/company/z1/).

## Zadanie 2

Do wykonania tego zadania wykorzystałem narzędzie `Procyon` na stronie [javadecompilers.com](javadecompilers.com). Po wysłaniu danego pliku na stronę dowiedziałem się, że program składa się z klas - `Coder` i `Main`:
- `Main.class`:
```java
package com.company;

import java.util.Date;
import java.time.Instant;
import java.text.SimpleDateFormat;

public class Main
{
    public static void main(final String[] array) {
        if (array.length != 1) {
            System.out.println("Wrong password!");
            return;
        }
        final String[] split = array[0].split("_");
        final int int1 = Integer.parseInt(new SimpleDateFormat("MM").format(Date.from(Instant.now())));
        if (split[0].length() == 7 && split[1].length() == 2) {
            if (split[0].equals(Coder.code("00PYe8m")) && Integer.parseInt(split[1]) == int1) {
                System.out.println("Good guess");
            }
            else {
                System.out.println("Wrong password!");
            }
        }
        else {
            System.out.println("Wrong password!");
        }
    }
}
```

- `Coder.class`:
```java
package com.company;

public class Coder
{
    static String code(final String s) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            sb.append((char)(s.charAt(i) + '\u0001'));
        }
        return sb.toString();
    }
}
```

Mając do dyspozycji powyższy kod, postarałem się odwzorować program w swoim IDE. Po analizie kodu ustaliłem, że hasło ma strukturę `[7 znaków] podkreślnik [2 znaki]`. Siedem pierwszych znaków to output metody `Coder.code("00PYe8m")`, a dwa ostatnie znaki to numer obecnego miesiąca.

Sprawdzam hasło:
```text
$ java -jar ./Tomasz_Jerzy_Mycielski_task_2.jar 11QZf9n_04
Good guess
```

## Zadanie 3