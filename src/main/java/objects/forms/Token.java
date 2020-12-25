package objects.forms;

import java.net.URLDecoder;

public class Token {

    private String username;
    private String expires;
    private String role;
    private String hash;


    public Token(String username, String expires, String role, String hash) {
        this.username = username;
        this.expires = expires;
        this.role = role;
        this.hash = hash;
    }

    public Token(String token){
        String[] data = URLDecoder.decode(token).split("@");
        String data1 = data[1];
        role = String.valueOf(data1.charAt(0));
        data1 = data1.substring(1);
        username = data[0];
        expires = data1;
        hash = data[2];
    }

    public String getUsername() {
        return username;
    }

    public String getExpires() {
        return expires;
    }

    public String getRole() {
        return role;
    }

    public String getHash() {
        return hash;
    }
}
