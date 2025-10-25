package com.mah.hrservice.service.impl;

import com.mah.hrservice.domain.SalaryConfirmation;
import com.mah.hrservice.repository.SalaryConfirmationRepository;
import com.mah.hrservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SalaryConfirmationRepository salaryConfirmationRepository;

    @Override
    public Workbook generateFinancialReport(YearMonth month) {
        List<SalaryConfirmation> confirmations = salaryConfirmationRepository.findByMonth(month);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Financial Report");

        int rowNum = 0;
        Row header = sheet.createRow(rowNum++);
        header.createCell(0).setCellValue("Employee Name");
        header.createCell(1).setCellValue("Total Salary");
        header.createCell(2).setCellValue("Employee Confirmed");
        header.createCell(3).setCellValue("Manager Confirmed");

        for (SalaryConfirmation sc : confirmations) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(sc.getEmployee().getFirstName() + " " + sc.getEmployee().getLastName());
            row.createCell(1).setCellValue(sc.getTotalSalary());
            row.createCell(2).setCellValue(sc.isEmployeeConfirmed());
            row.createCell(3).setCellValue(sc.isManagerConfirmed());
        }

        // Autosize columns
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }

    @Override
    public Workbook generateTaxReport(YearMonth month) {
        List<SalaryConfirmation> confirmations = salaryConfirmationRepository.findByMonth(month);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tax Report");

        int rowNum = 0;
        Row header = sheet.createRow(rowNum++);
        header.createCell(0).setCellValue("Employee Name");
        header.createCell(1).setCellValue("Gross Salary");
        header.createCell(2).setCellValue("Tax Amount");
        header.createCell(3).setCellValue("Insurance Amount");

        for (SalaryConfirmation sc : confirmations) {
            Row row = sheet.createRow(rowNum++);
            double tax = sc.getTotalSalary() * 0.1; // example 10% tax
            double insurance = sc.getTotalSalary() * 0.07; // example 7% insurance

            row.createCell(0).setCellValue(sc.getEmployee().getFirstName() + " " + sc.getEmployee().getLastName());
            row.createCell(1).setCellValue(sc.getTotalSalary());
            row.createCell(2).setCellValue(tax);
            row.createCell(3).setCellValue(insurance);
        }

        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }
}
