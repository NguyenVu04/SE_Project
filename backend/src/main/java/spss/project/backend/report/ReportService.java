package spss.project.backend.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.model.GridFSFile;

import spss.project.backend.Environment;
import spss.project.backend.configuration.system.SystemConfig;
import spss.project.backend.configuration.system.SystemConfigService;
import spss.project.backend.history.printing.PrintingHistoryItem;
import spss.project.backend.history.printing.PrintingHistoryService;

/**
 * This class provides methods for creating and retrieving reports. Reports are
 * stored in GridFS using the GridFSOperations class. The reports are created
 * using the XSSFWorkbook class.
 * <p>
 * The createReport method takes two parameters, from and to, which are used to
 * specify the period of time for which the report should be generated. The
 * method first retrieves all printing history items and system configurations
 * for the given period of time. It then creates a new XSSFWorkbook and creates
 * two sheets, one for the printing history and one for the system
 * configurations. The printing history sheet has columns for the printing
 * history item's ID, student ID, printer ID, document ID, paper size, number
 * of pages, number of copies, single sided, time ordered, time received, time
 * printed, and successful. The system configurations sheet has columns for the
 * configuration's ID, created at, created by, paper supply date, default
 * number of pages, and file types. The method then writes the workbook to a
 * ByteArrayOutputStream and stores the resulting byte array in GridFS.
 * <p>
 * The getReport method takes a report name as a parameter and returns the
 * corresponding report. The method retrieves the report from GridFS and
 * returns it as a byte array.
 * <p>
 * The getAllReports method returns a list of all reports in GridFS. The
 * method queries GridFS for all files with a filename that starts with
 * "report_" and ends with ".xlsx". The method then creates a list of maps
 * containing the filename, URL, file size, and upload date for each report.
 */
@Service
public class ReportService {
    /**
     * Protected constructor to prevent direct instantiation.
     */
    protected ReportService() {}

    @Autowired
    private GridFsOperations operations;

    @Autowired
    private PrintingHistoryService printingHistoryService;

    @Autowired
    private SystemConfigService systemConfigService;

    /**
     * Creates a report for the given period of time.
     * @param from the start of the period of time
     * @param to the end of the period of time
     * @return the name of the report
     * @throws Exception if an error occurs while creating the report
     */
    public String createReport(LocalDateTime from, LocalDateTime to) throws Exception {
        List<PrintingHistoryItem> history = printingHistoryService.getPrintingHistory(from, to);
        List<SystemConfig> configs = systemConfigService.getSystemConfigHistory(from, to);

        String reportName = "report_" + from.toString().replace(":", "-") + "_" + to.toString().replace(":", "-") + ".xlsx";

        Workbook wb = new XSSFWorkbook();
        Sheet printingSheet = wb.createSheet("Printing History");
        Sheet configSheet = wb.createSheet("System Configurations");

        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 144, (byte) 238, (byte) 144}));
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeight((short) 260);
        headerFont.setFontName("Times New Roman");
        headerStyle.setFont(headerFont);

        CellStyle bodyStyle = wb.createCellStyle();
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderRight(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.THIN);
        Font bodyFont = wb.createFont();
        bodyFont.setFontHeight((short) 260);
        bodyFont.setFontName("Times New Roman");
        bodyStyle.setFont(bodyFont);

        Row printingHeader = printingSheet.createRow(0);
        
        printingHeader.createCell(0).setCellValue("ID");
        printingHeader.createCell(1).setCellValue("Student ID");
        printingHeader.createCell(2).setCellValue("Printer ID");
        printingHeader.createCell(3).setCellValue("Document ID");
        printingHeader.createCell(4).setCellValue("Paper Size");
        printingHeader.createCell(5).setCellValue("Number of Pages");
        printingHeader.createCell(6).setCellValue("Number of Copies");
        printingHeader.createCell(7).setCellValue("Single Sided");
        printingHeader.createCell(8).setCellValue("Time Ordered");
        printingHeader.createCell(9).setCellValue("Time Received");
        printingHeader.createCell(10).setCellValue("Time Printed");
        printingHeader.createCell(11).setCellValue("Successful");
        
        for (int i = 0; i < 12; i++) {
            printingHeader.getCell(i).setCellStyle(headerStyle);
        }

        for (int i = 0; i < history.size(); i++) {
            Row row = printingSheet.createRow(i + 1);
            
            row.createCell(0).setCellValue(history.get(i).getId());
            row.createCell(1).setCellValue(history.get(i).getStudentId());
            row.createCell(2).setCellValue(history.get(i).getPrinterId());
            row.createCell(3).setCellValue(history.get(i).getDocumentId());
            row.createCell(4).setCellValue(history.get(i).getPaperSize());
            row.createCell(5).setCellValue(history.get(i).getPageNumbers().size());
            row.createCell(6).setCellValue(history.get(i).getNumberOfCopies());
            row.createCell(7).setCellValue(history.get(i).isSingleSided());
            row.createCell(8).setCellValue(history.get(i).getTimeOrdered().toString());
            row.createCell(9).setCellValue(history.get(i).getTimeReceived().toString());
            row.createCell(10).setCellValue(history.get(i).getTimePrinted().toString());
            row.createCell(11).setCellValue(history.get(i).isSuccessful());

            for (int j = 0; j < 12; j++) {
                row.getCell(j).setCellStyle(bodyStyle);
            }
        }

        for (int i = 0; i < 12; i++) {
            printingSheet.autoSizeColumn(i);
        }

        Row configHeader = configSheet.createRow(0);
        
        configHeader.createCell(0).setCellValue("ID");
        configHeader.createCell(1).setCellValue("Created At");
        configHeader.createCell(2).setCellValue("Created By");
        configHeader.createCell(3).setCellValue("Paper Supply Date");
        configHeader.createCell(4).setCellValue("Default Number of Pages");
        configHeader.createCell(5).setCellValue("File Types");
        
        for (int i = 0; i < 6; i++) {
            configHeader.getCell(i).setCellStyle(headerStyle);
        }

        for (int i = 0; i < configs.size(); i++) {
            Row row = configSheet.createRow(i + 1);
            
            row.createCell(0).setCellValue(configs.get(i).getId());
            row.createCell(1).setCellValue(configs.get(i).getCreatedAt().toString());
            row.createCell(2).setCellValue(configs.get(i).getCreatedBy());
            row.createCell(3).setCellValue(configs.get(i).getPaperSupplyDay());
            row.createCell(4).setCellValue(configs.get(i).getDefaultNumberOfPages());
            row.createCell(5).setCellValue(configs.get(i).getFileTypes().toString());

            for (int j = 0; j < 6; j++) {
                row.getCell(j).setCellStyle(bodyStyle);
            }
        }

        for (int i = 0; i < 6; i++) {
            configSheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        wb.write(bos);
        byte[] buffer = bos.toByteArray();
        bos.close();

        operations.store(new ByteArrayInputStream(buffer), reportName, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        wb.close();

        return reportName;
    }

    /**
     * Retrieves a report from GridFS.
     * @param reportName the name of the report to retrieve
     * @return the report as a byte array
     * @throws Exception if an error occurs while retrieving the report
     */
    public byte[] getReport(String reportName) throws Exception {
        return operations.getResource(reportName)
                .getInputStream()
                .readAllBytes();
    }

    /**
     * Retrieves all reports from GridFS.
     * @return a list of maps containing the filename, URL, file size, and
     * upload date for each report
     * @throws Exception if an error occurs while retrieving the reports
     */
    public List<Map<String, Object>> getAllReports() throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("filename").regex("^report_.*\\.xlsx$"));
        MongoCursor<GridFSFile> files = operations.find(query).iterator();
        List<Map<String, Object>> reports = new ArrayList<>();

        while (files.hasNext()) {
            GridFSFile file = files.next();
            Map<String, Object> report = Map.of(
                    "filename", file.getFilename(),
                    "url", Environment.CLOUD_URL + "/report?filename=" + file.getFilename(),
                    "fileSize", file.getLength(),
                    "uploadDate", file.getUploadDate());
            reports.add(report);
        }

        return reports;
    }
}
