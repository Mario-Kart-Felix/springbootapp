package com.mastek.dna.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ProfileMatch
{
    private Integer id;
    private Integer individualId;
    private Integer profileId;
    private Integer sampleId;
    private MatchType matchType;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getIndividualId()
    {
        return individualId;
    }

    public void setIndividualId(Integer individualId)
    {
        this.individualId = individualId;
    }

    public Integer getProfileId()
    {
        return profileId;
    }

    public void setProfileId(Integer profileId)
    {
        this.profileId = profileId;
    }

    public Integer getSampleId()
    {
        return sampleId;
    }

    public void setSampleId(Integer sampleId)
    {
        this.sampleId = sampleId;
    }

    public MatchType getMatchType()
    {
        return matchType;
    }

    public void setMatchType(MatchType matchType)
    {
        this.matchType = matchType;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
