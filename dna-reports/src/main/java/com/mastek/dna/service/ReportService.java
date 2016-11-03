package com.mastek.dna.service;

import com.mastek.dna.model.MatchType;
import com.mastek.dna.model.ProfileMatch;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportService
{

    @Autowired
    private JasperReport jasperReport;


    public void generateReport(OutputStream os) throws JRException
    {
        List<ProfileMatch> matches = new ArrayList<>();
        //TODO: integrate with Database
        addDummyData(matches);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(matches);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint,os);
    }

    private void addDummyData(List<ProfileMatch> matches)
    {
        for (int i = 1; i < 101; i++)
        {
            ProfileMatch p = new ProfileMatch();
            p.setId(i);
            p.setIndividualId(getRandom());
            int random = (int)Math.floor(Math.random() * 2);
            p.setMatchType(MatchType.values()[random]);
            p.setProfileId(getRandom());
            p.setSampleId(getRandom());
            System.out.println(p);
            matches.add(p);
        }
    }

    private int getRandom(){
        return  (int )(Math.random() * 1000000000 + 1);
    }
}
