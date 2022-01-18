import org.sec.payload.Payload;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class CalcLength {
    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(Paths.get("test.ser"));
        byte[] base64 = Base64.getEncoder().encode(data);
        System.out.println(new String(base64).length());
        Payload.deserialize(data);
    }
}
