import org.fusesource.lmdbjni.Database;
import org.fusesource.lmdbjni.Env;

import java.io.File;

import static org.fusesource.lmdbjni.Constants.bytes;
import static org.fusesource.lmdbjni.Constants.string;

public class Main {
    public static void main(String[] args) {
        new File("mydb").mkdirs();

        try (Env env = new Env("mydb")) {
            try (Database db = env.openDatabase()) {
                db.put(bytes("Tampa"), bytes("rocks"));
                String value = string(db.get(bytes("Tampa")));
                System.out.println(value);
            }
        }
    }
}
