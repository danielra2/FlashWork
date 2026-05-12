package mycode.flashwork2.employerProfile.exceptions;

import mycode.flashwork2.constants.EmployerProfileConstant;

public class EmployerProfileNotFoundException extends RuntimeException {
    public EmployerProfileNotFoundException() {
        super(EmployerProfileConstant.EMPLOYER_FINAL_DOESNT_EXIST);
    }
}