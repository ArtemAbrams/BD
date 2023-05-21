package com.labexample.controller;

import com.labexample.DTO.DataWrapper;
import com.labexample.enums.Color;
import com.labexample.enums.Transport;
import com.labexample.enums.Type;
import com.labexample.repository.ClientRepository;
import com.labexample.repository.DeliveryRepository;
import com.labexample.repository.OrderRepository;
import com.labexample.repository.ProductRepository;
import com.labexample.services.ExcelWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.labexample.services.impl.ExcelWriterImpl.DEFAULT_DEPTH;
import static com.labexample.services.impl.ExcelWriterImpl.EXCEL_MIME_TYPE;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;
    private final ExcelWriter excelWriter;
    @GetMapping("/index")
    public String index() {
        return "start";
    }
    @PostMapping("/download-clients")
    public ResponseEntity<Resource> downloadClients() {
        final var crews = clientRepository.fetchAll();
        final var file = excelWriter.writeToExcel("clients.xlsx", EXCEL_MIME_TYPE, crews);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(file.getResource());
    }
    @PostMapping("/download-orders")
    public ResponseEntity<Resource> downloadCrewMembers() {
        final var crewMembers = orderRepository.fetchAll();
        final var file = excelWriter.writeToExcel("orders.xlsx", EXCEL_MIME_TYPE, crewMembers);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(file.getResource());
    }
    @PostMapping("/download-products")
    public ResponseEntity<Resource> downloadTrains() {
        final var trains = productRepository.fetchAll();
        final var file = excelWriter.writeToExcel("products.xlsx", EXCEL_MIME_TYPE, trains);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(file.getResource());
    }

    @PostMapping("/query1")
    public ResponseEntity<Resource> query1(@RequestParam String year,
                                           @RequestParam String month,
                                           @RequestParam String day,
                                           @RequestParam Transport transport) {
        String errors;
        try {
            LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0, 0);
            final var clients = clientRepository.specialQuery1(dateTime, transport);
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, clients);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }
    @PostMapping("/query2")
    public ResponseEntity<Resource> query2(@RequestParam String colors,
                                           @RequestParam String productMaxSize) {
        String errors;
        try {
            List<Color> list = Arrays.stream(colors.split(",")).map(Color::valueOf).toList();
            final var analytics = deliveryRepository.specialQuery2(list, Double.parseDouble(productMaxSize));
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, analytics);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }
    @PostMapping("/query3")
    public ResponseEntity<Resource> query3(@RequestParam Type type,
                                           @RequestParam String name) {
        String errors;
        try {
            final var list = clientRepository.specialQuery3(type, name);
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, list);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }
    @PostMapping("/query4")
    public ResponseEntity<Resource> query4(@RequestParam String name) {
        String errors;
        try {
            final var list = deliveryRepository.specialQuery4(name)
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, list);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }
    @PostMapping("/query5")
    public ResponseEntity<Resource> query5(@RequestParam String count) {
        String errors;
        try {
            final var list = clientRepository.specialQuery5(Integer.parseInt(count));
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, list);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }
    @PostMapping("/query6")
    public ResponseEntity<Resource> query6(@RequestParam String orderId) {
        String errors;
        try {
            final var list = orderRepository.specialQuery6(Long.parseLong(orderId))
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, list);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }
    @PostMapping("/query7")
    public ResponseEntity<Resource> query7(@RequestParam String orderId) {
        String errors;
        try {
            final var list = orderRepository.specialQuery7(Long.parseLong(orderId))
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, list);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }
    @PostMapping("/query8")
    public ResponseEntity<Resource> query8(@RequestParam String orderId) {
        String errors;
        try {
            final var list = orderRepository.specialQuery8(Long.parseLong(orderId))
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, list);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }
    @PostMapping("/query9")
    public ResponseEntity<Resource> query9(@RequestParam String orderId) {
        String errors;
        try {
            final var list = orderRepository.specialQuery9(Long.parseLong(orderId))
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, list);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }
}
