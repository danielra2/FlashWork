package mycode.flashwork2.jobs.exceptions;

import mycode.flashwork2.constants.JobConstant;

public class JobAlreadyExistsException extends RuntimeException {
    public JobAlreadyExistsException() {
        super(JobConstant.JOB_ALREADY_EXISTS);
    }
}
