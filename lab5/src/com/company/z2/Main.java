package com.company.z2;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Main {

    /* Flags:
     ACC_PUBLIC
     ACC_STATIC

   public static void main(java.lang.String[])  */

    public static void main(String[] param1) {
        PrintStream pPVar1;
        String[] ppvVar2;
        Instant pIVar3;
        Date pDVar4;
        String pSVar5;
        int iVar6;
        int iVar7;
        boolean bVar8;
        SimpleDateFormat objectRef;
        String objectRef_00;

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
        if ((iVar7 == 7) && (iVar7 = ppvVar2[1].length(),iVar7 == 2)){
            objectRef_00 = ppvVar2[0];
            pSVar5 = Coder.code("00PYe8m");
            bVar8 = objectRef_00.equals(pSVar5);
            if ((bVar8) && (iVar7 = Integer.parseInt(ppvVar2[1]),iVar7 == iVar6)){
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
}
