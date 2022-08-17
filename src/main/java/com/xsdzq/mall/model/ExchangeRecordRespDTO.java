package com.xsdzq.mall.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ExchangeRecordRespDTO {
    private String clientId;
    private String prizeName;
    private Date endTime;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
