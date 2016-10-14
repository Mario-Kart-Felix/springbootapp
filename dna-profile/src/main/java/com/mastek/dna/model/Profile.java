package com.mastek.dna.model;

import com.mastek.dna.model.validator.Create;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

public class Profile {

    private Integer id;
    private List<String> fingerPrint;
    private List<String> retinaScan;

    @Null(groups = Create.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Valid
    @NotNull
    public List<String> getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(List<String> fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    @Valid
    @NotNull
    public List<String> getRetinaScan() {
        return retinaScan;
    }

    public void setRetinaScan(List<String> retinaScan) {
        this.retinaScan = retinaScan;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
