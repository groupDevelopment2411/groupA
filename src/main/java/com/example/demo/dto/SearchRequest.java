package com.example.demo.dto;

//検索条件を保持するDTO,Springの@ModelAttributeを使って、入力フォームの値をまとめて受け取るためのクラス

public class SearchRequest {
    private Integer id;
    private String name;
    private Integer ageFrom;
    private Integer ageTo;
    private String startDateFrom;
    private String startDateTo;
    private String endDateFrom;
    private String endDateTo;

    //  ゲッター・セッター
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAgeFrom() { return ageFrom; }
    public void setAgeFrom(Integer ageFrom) { this.ageFrom = ageFrom; }

    public Integer getAgeTo() { return ageTo; }
    public void setAgeTo(Integer ageTo) { this.ageTo = ageTo; }

    public String getStartDateFrom() { return startDateFrom; }
    public void setStartDateFrom(String startDateFrom) { this.startDateFrom = startDateFrom; }

    public String getStartDateTo() { return startDateTo; }
    public void setStartDateTo(String startDateTo) { this.startDateTo = startDateTo; }

    public String getEndDateFrom() { return endDateFrom; }
    public void setEndDateFrom(String endDateFrom) { this.endDateFrom = endDateFrom; }

    public String getEndDateTo() { return endDateTo; }
    public void setEndDateTo(String endDateTo) { this.endDateTo = endDateTo; }
}
