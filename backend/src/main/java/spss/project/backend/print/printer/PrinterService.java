package spss.project.backend.print.printer;

import org.springframework.beans.factory.annotation.Autowired;

public class PrinterService {
    @Autowired
    private PrinterRepository repo;

    public PrinterRepository getRepo() {
        return repo;
    }
}
