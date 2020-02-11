package com.security.service;

import com.security.model.Staff;
import com.security.repository.RoleRepository;
import com.security.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Staff staff) {
        staff.setPassword(bCryptPasswordEncoder.encode(staff.getPassword()));
        staff.setRoles(new HashSet<>(roleRepository.findAll()));
        staffRepository.save(staff);
    }

    @Override
    public Staff findByStaffLogin(String login) {
        return staffRepository.findByLogin(login);
    }


}
