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

Zanim przystąpiłem do głębszej analizy danego pliku, próbowałem zgadnąć hasło:
```text
java -jar ./Tomasz_Jerzy_Mycielski_task_2.jar password
Wrong password!
```
Po sprawdzeniu tego trywialnego rozwiązania przystąpiłem do inżynierii wstecznej.

Do wykonania tego zadania wykorzystałem program Ghidra. Po zaimportowaniu danego pliku do Ghidry dowiedziałem się, że program składa się z klas - `Coder` i `Main`. Następnie otworzyłem obydwie klasy w narzędziu CodeBrowser po wykonaniu wszystkich dostępnych w nim automatycznych analiz. Dzięki temu uzyskłem poniższy kod:
- `Main.class`:
```java
/* Flags:
     ACC_PUBLIC
     ACC_STATIC
   
   public static void main(java.lang.String[])  */

void main_java.lang.String[]_void(void[] param1)

{
  PrintStream pPVar1;
  void[] ppvVar2;
  Instant pIVar3;
  Date pDVar4;
  String pSVar5;
  int iVar6;
  int iVar7;
  boolean bVar8;
  SimpleDateFormat objectRef;
  void objectRef_00;
  
  if (param1.length != 1) {
    pPVar1 = System.out;
    pPVar1.println("Wrong password!");
    return;
  }
  ppvVar2 = param1[0].split("_");
  pIVar3 = Instant.now();
  pDVar4 = Date.from(pIVar3);
  objectRef = new SimpleDateFormat("MM");
  pSVar5 = objectRef.format(pDVar4);
  iVar6 = Integer.parseInt(pSVar5);
  iVar7 = ppvVar2[0].length();
  if ((iVar7 == 7) && (iVar7 = ppvVar2[1].length(), iVar7 == 2)) {
    objectRef_00 = ppvVar2[0];
    pSVar5 = Coder.code("00PYe8m");
    bVar8 = objectRef_00.equals(pSVar5);
    if ((bVar8 != false) && (iVar7 = Integer.parseInt(ppvVar2[1]), iVar7 == iVar6)) {
      pPVar1 = System.out;
      pPVar1.println("Good guess");
      return;
    }
    pPVar1 = System.out;
    pPVar1.println("Wrong password!");
    return;
  }
  pPVar1 = System.out;
  pPVar1.println("Wrong password!");
  return;
}
```

- `Coder.class`:
```java
/* Flags:
     ACC_STATIC
   
   static String code(java.lang.String)  */

String code_java.lang.String_java.lang.String(void param1)

{
  int iVar1;
  char cVar3;
  String pSVar2;
  StringBuilder objectRef;
  int iVar4;
  int iVar5;
  StringBuilder objectRef_00;
  
  objectRef = new StringBuilder();
  iVar4 = 0;
  while( true ) {
    iVar5 = iVar4;
    iVar1 = param1.length();
    if (iVar1 <= iVar5) break;
    objectRef_00 = objectRef;
    cVar3 = param1.charAt(iVar4);
    objectRef_00.append(cVar3 + '\ux0001');
    iVar4 = iVar4 + 1;
  }
  pSVar2 = objectRef.toString();
  return pSVar2;
}
```

Mając do dyspozycji powyższy kod, postarałem się odwzorować program w swoim IDE.

## Zadanie 3