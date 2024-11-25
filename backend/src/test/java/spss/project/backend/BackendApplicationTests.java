package spss.project.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spss.project.backend.configuration.system.SystemConfigService;
import spss.project.backend.order.OrderService;
import spss.project.backend.printer.PrinterService;
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
	private OrderService orderService;

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

		adminService.save(
				"admin@example.com",
				"Admin",
				"User");

		spsoService.save(
				"spso@example.com",
				"SPSO",
				"User");

		systemConfigService.saveCurrentSystemConfig(
				7,
				"admin",
				"https://cloud.example.com",
				Arrays.asList("application/pdf"));

		printerService.save(
				"HP LaserJet 5000",
				"High-speed monochrome printer",
				"Main Campus",
				"Engineering Building",
				"101A",
				true);

		orderService.save(
				"student123",
				"printer456",
				"document789",
				"A4",
				Arrays.asList(1, 2, 3, 5, 7),
				2,
				true,
				LocalDateTime.now(),
				false);
	}

}
