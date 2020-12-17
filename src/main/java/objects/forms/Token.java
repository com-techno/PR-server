package objects.forms;

import java.net.URLDecoder;

public class Token {

    private String username;
    private String expires;
    private String hash;


    public Token(String username, String expires, String hash) {
        this.username = username;
        this.expires = expires;
        this.hash = hash;
    }

    public Token(String token){
        String[] data = URLDecoder.decode(token).split("@");
        username = data[0];
        expires = data[1];
        hash = data[2];
    }

    public String getUsername() {
        return username;
    }

    public String getExpires() {
        return expires;
    }

    public String getHash() {
        return hash;
    }
}
