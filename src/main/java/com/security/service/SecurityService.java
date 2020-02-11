package com.security.service;

public interface SecurityService {

    String findLoggedInStafflogin();

    void autoLogin(String login, String password);

}
