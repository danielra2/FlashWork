package mycode.flashwork2.employerProfile.exceptions;

public class EmployerProfileNotFoundException extends RuntimeException {
    public EmployerProfileNotFoundException() {
        super("Profilul angajatorului nu a fost găsit");
    }

    public EmployerProfileNotFoundException(String message) {
        super(message);
    }
}
