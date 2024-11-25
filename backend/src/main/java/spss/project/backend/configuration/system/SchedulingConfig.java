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

@Configuration
@EnableScheduling
public class SchedulingConfig implements SchedulingConfigurer {
    int supplyDay = LocalDateTime.now().getDayOfMonth();

    @Autowired
    private SystemConfigService service;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ReportService reportService;

    private static final Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(1));
        taskRegistrar.addTriggerTask(new Runnable() {

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

    @Scheduled(cron = "0 0 0 1 * *")
    public void createMonthlyReport() {
        try {

            LocalDateTime now = LocalDateTime.now();
            reportService.createReport(now.minusMonths(1), now);

        } catch (Exception e) {
            logger.error("Error creating monthly report", e);
        }
    }

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
