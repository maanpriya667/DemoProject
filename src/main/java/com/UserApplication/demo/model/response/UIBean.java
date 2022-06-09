package com.UserApplication.demo.model.response;

public class UIBean<T> {
    private T userData;

    public T getUserData() {
        return userData;
    }

    public void setUserData(T userData) {
        this.userData = userData;
    }
}
