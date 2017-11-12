package com.stefano.briky.json;

public class LoginSuccessJson {

    private String token;
    private UserJson user;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserJson getUser() {
        return user;
    }

    public void setUser(UserJson user) {
        this.user = user;
    }

}
