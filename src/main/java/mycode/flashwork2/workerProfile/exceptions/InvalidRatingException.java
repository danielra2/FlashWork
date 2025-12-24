package mycode.flashwork2.workerProfile.exceptions;

import mycode.flashwork2.constants.WorkerProfileConstant;

public class InvalidRatingException extends RuntimeException {
    public InvalidRatingException() {
        super(WorkerProfileConstant.INVALID_RATING_EXCEPTION);
    }
}
