package com.example.demo;

import lombok.Data;

@Data
public class CsvDto {
    private int id;
    private String title;
    private String licenseOrgan;
    private String esRegdt;

    public CsvDto(int id,
                  String title,
                  String licenseOrgan,
                  String esRegdt)
    {
        this.id = id;
        this.title = title;
        this.licenseOrgan = licenseOrgan;
        this.esRegdt = esRegdt;
    }
}
