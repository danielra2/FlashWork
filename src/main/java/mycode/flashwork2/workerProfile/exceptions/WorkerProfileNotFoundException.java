package mycode.flashwork2.workerProfile.exceptions;

import mycode.flashwork2.constants.WorkerProfileConstant;

public class WorkerProfileNotFoundException extends RuntimeException {
    public WorkerProfileNotFoundException() {
        super(WorkerProfileConstant.WORKER_PROFILE_NOT_FOUND);
    }
}
