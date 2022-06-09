package com.UserApplication.demo.model.response;

public class SignupSuccessResponse {

    private String token;
    private boolean isExistingUser;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getIsExistingUser() {
        return isExistingUser;
    }

    public void setIsExistingUser(boolean isExistingUser) {
        this.isExistingUser = isExistingUser;
    }


}
