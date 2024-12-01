package spss.project.backend;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spss.project.backend.configuration.system.SystemConfigService;
import spss.project.backend.printer.PrinterService;
import spss.project.backend.report.ReportService;
import spss.project.backend.user.admin.AdminService;
import spss.project.backend.user.spso.SPSOService;
import spss.project.backend.user.student.StudentService;

@SpringBootTest
class BackendApplicationTests {
	@Autowired
	private StudentService studentService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private SPSOService spsoService;

	@Autowired
	private SystemConfigService systemConfigService;

	@Autowired
	private PrinterService printerService;

	@Autowired
	private ReportService reportService;

	@Test
	void databaseSeeding() throws Exception {

		studentService.save(
				"random.student@example.com",
				"Random",
				"Student",
				LocalDate.of(2000, 1, 1),
				500);

		studentService.save(
				"random2.student@example.com",
				"Random2",
				"Student2",
				LocalDate.of(2000, 1, 1),
				500);

		studentService.save(
				"laughingjack750@gmail.com",
				"Random2",
				"Student2",
				LocalDate.of(2000, 1, 1),
				500);

		adminService.save(
				"laughingjack750@gmail.com",
				"Admin",
				"User");

		spsoService.save(
				"laughingjack750@gmail.com",
				"SPSO",
				"User");

		systemConfigService.saveCurrentSystemConfig(
				10,
				7,
				"admin",
				Arrays.asList("application/pdf"));

		printerService.save(
				"HP",
				"HP LaserJet 5000",
				"High-speed monochrome printer",
				"Main Campus",
				"Engineering Building",
				"101A",
				true);

		printerService.save(
				"HP",
				"HP LaserJet 5000",
				"High-speed monochrome printer",
				"Main Campus",
				"Engineering Building",
				"102A",
				true);

		printerService.save(
				"HP",
				"HP LaserJet 5000",
				"High-speed monochrome printer",
				"Main Campus",
				"Engineering Building",
				"104A",
				true);
	}

	@Test
	void testReport() throws Exception {
		String reportName = reportService.createReport(
				LocalDateTime.parse("2022-01-01T00:00"),
				LocalDateTime.parse("2025-01-02T00:00"));

		Files.write(Path.of("D:\\Project\\" + reportName), reportService.getReport(reportName));
	}
}
