package util;

import objects.User;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;

public class HashUtils {

    static final long validTime = 2000000 * 1000;

    public static String getToken(User user) throws Exception {
        long expires = new Date().getTime() + validTime;
        return user.login + "@" + user.role + expires + "@" + genHash(user.login, expires, user.role);
    }

    public static String getURLEncodedToken(User user) throws Exception {
        return URLEncoder.encode(getToken(user));
    }

    public static boolean checkToken(String token) {
        String[] data = URLDecoder.decode(token).split("@");
        String data1 = data[1];
        int role = Integer.parseInt(String.valueOf(data1.charAt(0)));
        data1 = data1.substring(1);
        long expires = Long.parseLong(data1);
        long now = new Date().getTime();
        if (expires < now) return false;
        data[2] = URLDecoder.decode(data[2]);
        data[2] = data[2].replace(" ", "+");
        try {
            String trueHash = URLDecoder.decode(genHash(data[0], expires, role));
            if (data[2].equals(trueHash)) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getRoleFromToken(String token) {
        String[] data = URLDecoder.decode(token).split("@");
        String data1 = data[1];
        return Integer.parseInt(String.valueOf(data1.charAt(0)));
    }

    public static String getLoginFromToken(String token) {
        String[] data = URLDecoder.decode(token).split("@");
        return data[0];
    }

    private static String genHash(String username, long expires, int role) throws Exception {
        return URLEncoder.encode(new String(encode(username + role + expires + "techno salt")));
    }

    public static byte[] encode(String target) throws Exception {
        MessageDigest sha512 = MessageDigest.getInstance("sha-512");
        return sha512.digest((target)
                .replace(" ", "g")
                .replace("!", "A")
                .replace("*", "B")
                .replace("'", "C")
                .replace("(", "D")
                .replace(")", "E")
                .replace(";", "F")
                .replace(":", "G")
                .replace("@", "H")
                .replace("&", "I")
                .replace("=", "J")
                .replace("+", "K")
                .replace("$", "L")
                .replace(",", "M")
                .replace("/", "N")
                .replace("?", "O")
                .replace("#", "P")
                .replace("[", "Q")
                .replace("]", "R")
                .getBytes());
    }

}