package com.mah.hrservice.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.time.YearMonth;

public interface ReportService {

    Workbook generateFinancialReport(YearMonth month);

    Workbook generateTaxReport(YearMonth month);
}
