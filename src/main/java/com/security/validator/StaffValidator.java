package com.security.validator;

import com.security.model.Staff;
import com.security.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StaffValidator implements Validator {

    @Autowired
    private StaffService staffService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Staff.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Staff staff = (Staff) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty");
        if (staff.getLogin().length() < 3 || staff.getName().length() > 10) {
            errors.rejectValue("login", "Size.staffForm.login");
        }

        if (staffService.findByStaffLogin(staff.getLogin()) != null) {
            errors.rejectValue("login", "Duplicate.staffForm.login");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (staff.getPassword().length() < 8 || staff.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.staffForm.password");
        }

        if (!staff.getPasswordConfirm().equals(staff.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.staffForm.passwordConfirm");
        }


    }
}
