package mycode.flashwork2.jobs.exceptions;

import mycode.flashwork2.constants.JobConstant;

public class JobNotAvailableException extends RuntimeException {
    public JobNotAvailableException() {
        super(JobConstant.JOB_NOT_AVAILABLE);
    }
}