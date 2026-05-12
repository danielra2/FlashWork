package mycode.flashwork2.enrollment.exceptions;

import mycode.flashwork2.constants.EnrollmentConstant;

public class EnrollmentAlreadyExistsException extends RuntimeException {
    public EnrollmentAlreadyExistsException() {
       super(EnrollmentConstant.ENROLLMENT_ALREADY_EXISTS);
    }
}
