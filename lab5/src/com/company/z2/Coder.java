package com.company.z2;

public class Coder {

    /* Flags:
     ACC_STATIC

   static String code(java.lang.String)  */

    public static void main(String[] args){
        System.out.println(code("00PYe8m")); // 4949819010257110
    }

    public static String code(String param1) {
        int iVar1;
        char cVar3;
        String pSVar2;
        StringBuilder objectRef;
        int iVar4;
        int iVar5;
        StringBuilder objectRef_00;

        objectRef = new StringBuilder();
        iVar4 = 0;
        while (true) {
            iVar5 = iVar4;
            iVar1 = param1.length();
            if (iVar1 <= iVar5) break;
            objectRef_00 = objectRef;
            cVar3 = param1.charAt(iVar4);
            objectRef_00.append(cVar3 + '\1');
            iVar4 = iVar4 + 1;
        }
        pSVar2 = objectRef.toString();
        return pSVar2;
    }
}
