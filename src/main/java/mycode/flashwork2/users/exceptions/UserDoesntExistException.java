package mycode.flashwork2.users.exceptions;

import mycode.flashwork2.constants.UserConstant;

public class UserDoesntExistException extends RuntimeException {
    public UserDoesntExistException() {
        super(UserConstant.USER_DOESNT_EXIST);
    }
}
