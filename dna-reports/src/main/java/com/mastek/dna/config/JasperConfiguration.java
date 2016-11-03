package com.mastek.dna.config;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasperConfiguration
{
    private static final String DNA_MATCH_REPORT_JRXML = "/dna-match-report.jrxml";

    @Bean
    JasperReport jasperReport() throws JRException
    {
        return JasperCompileManager.compileReport(getClass().getResourceAsStream(DNA_MATCH_REPORT_JRXML));
    }
}
