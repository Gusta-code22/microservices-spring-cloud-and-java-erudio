package github.io.Gusta_code22.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;



public class Exchange implements Serializable {
    private static final long serialVersionUID = 1L;


    // Getters e Setters
    private Long id;


    private String from;


    private String to;


    private BigDecimal conversionFactor;


    private Double convertedValue;

    private String environment;

    // Construtor padrão (Obrigatório para JPA)
    public Exchange() {
    }

    // Construtor completo (Equivalente ao do Record)
    public Exchange(Long id, String from, String to, BigDecimal conversionFactor,
                    Double convertedValue, String environment) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionFactor = conversionFactor;
        this.convertedValue = convertedValue;
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionFactor() {
        return conversionFactor;
    }

    public Double getConvertedValue() {
        return convertedValue;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setConversionFactor(BigDecimal conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public void setConvertedValue(Double convertedValue) {
        this.convertedValue = convertedValue;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}