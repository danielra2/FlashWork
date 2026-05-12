package mycode.flashwork2.enrollment.exceptions;

import mycode.flashwork2.constants.EnrollmentConstant;

public class EnrollmentNotFoundException extends RuntimeException {
  public EnrollmentNotFoundException() {
    super(EnrollmentConstant.ENROLLMENT_NOT_FOUND_EXCEPTION);
  }
}
