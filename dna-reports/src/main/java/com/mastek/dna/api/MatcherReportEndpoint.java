package com.mastek.dna.api;
import com.mastek.dna.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MatcherReportEndpoint {

    private static final String ROOT_URL = "/report";

    @Autowired
    private ReportService reportService;

    @GetMapping(ROOT_URL)
    public void generateReport(HttpServletResponse response) throws IOException, JRException
    {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", String.format("inline; filename=simple-jasper-report.pdf"));
        reportService.generateReport(response.getOutputStream());
    }
}
