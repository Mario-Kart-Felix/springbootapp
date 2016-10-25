package com.mastek.dna.model;

import com.mastek.dna.model.validator.Api;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "individual_profile")
public class Profile {
    private Integer id;
    private Integer individualId;
    private String fingerPrintData;
    private String retinaScanData;

    @Null(groups = Api.class)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "individual_profile_seq")
    @SequenceGenerator(name = "individual_profile_seq", sequenceName = "individual_profile_seq", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    @NotNull
    public Integer getIndividualId() {
        return individualId;
    }

    public void setIndividualId(Integer individualId) {
        this.individualId = individualId;
    }
    @NotNull
    public String getFingerPrintData() {
        return fingerPrintData;
    }

    public void setFingerPrintData(String fingerPrintData) {
        this.fingerPrintData = fingerPrintData;
    }
    @NotNull
    public String getRetinaScanData() {
        return retinaScanData;
    }

    public void setRetinaScanData(String retinaScanData) {
        this.retinaScanData = retinaScanData;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
