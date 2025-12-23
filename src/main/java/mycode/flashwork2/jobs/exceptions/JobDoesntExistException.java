package mycode.flashwork2.jobs.exceptions;

import mycode.flashwork2.constants.JobConstant;

public class JobDoesntExistException extends RuntimeException {
    public JobDoesntExistException() {
        super(JobConstant.JOB_DOESNT_EXIST);
    }
}
