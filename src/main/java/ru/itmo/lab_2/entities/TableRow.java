package ru.itmo.lab_2.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class TableRow implements Serializable {
    private BigDecimal R;
    private BigDecimal X;
    private BigDecimal Y;
    private String resultHit;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss.SSSSZ")
    private OffsetDateTime clientDate;
    private BigDecimal executionTime;

    public TableRow(BigDecimal R, BigDecimal X, BigDecimal Y, String resultHit,
                    OffsetDateTime clientDate, BigDecimal executionTime) {
        this.R = R;
        this.X = X;
        this.Y = Y;
        this.resultHit = resultHit;
        this.clientDate = clientDate;
        this.executionTime = executionTime;
    }

    public BigDecimal getR() {
        return R;
    }

    public BigDecimal getX() {
        return X;
    }

    public BigDecimal getY() {
        return Y;
    }

    public String getResultHit() {
        return resultHit;
    }

    public OffsetDateTime getClientDate() {
        return clientDate;
    }

    public BigDecimal getExecutionTime() {
        return executionTime;
    }

    @Override
    public String toString() {
        return "TableRow{" +
                "R=" + R +
                ", X=" + X +
                ", Y=" + Y +
                ", resultHit='" + resultHit + '\'' +
                ", clientDate=" + clientDate +
                ", executionTime=" + executionTime +
                '}';
    }

    public List<TableRow> toList() {
        return List.of(this);
    }
}
