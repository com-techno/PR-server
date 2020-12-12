package util;

import java.io.InputStream;
import java.util.Scanner;

public class StreamUtils {

    public static String readStream(InputStream is){
        Scanner in = new Scanner(is);
        StringBuilder json = new StringBuilder();
        while (in.hasNextLine())
            json.append(in.nextLine());
        return json.toString();
    }

}
