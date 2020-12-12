package objects;

import java.util.Arrays;

public class User {
    public String login;
    public String passHash;
    public String email;
    public int admin;

    public User(String username, String passHash, String email) throws Exception {
        if (username == null || passHash == null) throw new Exception("Form is incomplete");
        this.login = username;
        this.passHash = passHash;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.login.equals(user.login) &&
                this.passHash.equals(user.passHash);
    }

}
