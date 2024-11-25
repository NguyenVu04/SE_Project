package spss.project.backend.configuration.system;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import spss.project.backend.report.ReportService;
import spss.project.backend.user.student.StudentService;

/**
 * Configuration class for scheduling tasks.
 */
@Configuration
@EnableScheduling
public class SchedulingConfig implements SchedulingConfigurer {
    /**
     * Protected constructor for SchedulingConfig.
     * 
     * This constructor is protected to prevent direct instantiation of the 
     * SchedulingConfig class, ensuring that it is used only as a configuration 
     * class for scheduling tasks.
     */
    protected SchedulingConfig() {}

    /**
     * The day of the month when the students will be supplied with paper.
     */
    int supplyDay = 1;

    /**
     * The service for managing system configurations.
     */
    @Autowired
    private SystemConfigService service;

    /**
     * The service for managing students.
     */
    @Autowired
    private StudentService studentService;

    /**
     * The service for generating reports.
     */
    @Autowired
    private ReportService reportService;

    /**
     * The logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

    /**
     * Configures the scheduler for the tasks.
     *
     * @param taskRegistrar the task registrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(1));
        taskRegistrar.addTriggerTask(new Runnable() {

            /**
             * Runs the task to supply all students with paper.
             */
            @Override
            public void run() {
                try {

                    SystemConfig config = service.getCurrentSystemConfig();

                    if (config != null) {
                        supplyDay = config.getPaperSupplyDay();
                        studentService.supplyAllStudents(config.getDefaultNumberOfPages());
                    }

                } catch (Exception e) {
                    logger.error("Error scheduling task", e);
                }
            }

        },
                new Trigger() {

                    /**
                     * Calculates the next execution time for the task.
                     *
                     * @param arg0 the trigger context
                     * @return the next execution time
                     */
                    @Override
                    public Instant nextExecution(TriggerContext arg0) {
                        LocalDateTime temp = LocalDateTime.now()
                                .plusMonths(1);

                        int month = temp.getMonthValue();
                        int year = temp.getYear();

                        return LocalDateTime.of(
                                year,
                                month,
                                supplyDay,
                                0,
                                0,
                                0)
                                .atZone(ZoneId.systemDefault())
                                .toInstant();
                    }

                });
    }

    /**
     * Creates a monthly report.
     */
    @Scheduled(cron = "0 0 0 1 * *")
    public void createMonthlyReport() {
        try {

            LocalDateTime now = LocalDateTime.now();
            reportService.createReport(now.minusMonths(1), now);

        } catch (Exception e) {
            logger.error("Error creating monthly report", e);
        }
    }

    /**
     * Creates a yearly report.
     */
    @Scheduled(cron = "0 0 0 1 1 *")
    public void createYearlyReport() {
        try {

            LocalDateTime now = LocalDateTime.now();
            reportService.createReport(now.minusYears(1), now);

        } catch (Exception e) {
            logger.error("Error creating yearly report", e);
        }
    }
}
