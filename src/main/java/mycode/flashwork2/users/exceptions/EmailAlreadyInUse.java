package mycode.flashwork2.users.exceptions;

import mycode.flashwork2.constants.UserConstant;

public class EmailAlreadyInUse extends RuntimeException {
    public EmailAlreadyInUse() {
        super(UserConstant.EMAIL_ALREADY_IN_USE);
    }
}
