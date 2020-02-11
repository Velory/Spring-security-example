package com.security.service;

import com.security.model.Staff;

public interface StaffService {

    void save(Staff staff);

    Staff findByStaffLogin(String login);

}
