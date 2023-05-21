package com.labexample.services;

import com.labexample.DTO.DownloadFileDTO;

import java.util.List;

public interface ExcelWriter {
    <T> DownloadFileDTO writeToExcel(String fileName, String contentType, List<T> data);
    String generateExcelFileName(Integer depth);
}
