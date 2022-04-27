package com.company.model;

import java.util.TreeMap;

public class Loan {
    private String bank_name;
    private String borrower_name;
    private double principal;
    private Integer no_of_years;
    private double rate_of_Interest;
    private double total_amount;
    private double monthly_emi;
    private TreeMap<Integer, Double> lump_sum_map;

    public TreeMap<Integer, Double> getLump_sum_map() {
        if (this.lump_sum_map == null)
            this.lump_sum_map = new TreeMap<>();
        return lump_sum_map;
    }

    public void setLump_sum_map(TreeMap<Integer, Double> lump_sum_map) {
        if (this.lump_sum_map == null)
            this.lump_sum_map = lump_sum_map;
        this.lump_sum_map.putAll(lump_sum_map);
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBorrower_name() {
        return borrower_name;
    }

    public void setBorrower_name(String borrower_name) {
        this.borrower_name = borrower_name;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public Integer getNo_of_years() {
        return no_of_years;
    }

    public void setNo_of_years(Integer no_of_years) {
        this.no_of_years = no_of_years;
    }

    public double getRate_of_Interest() {
        return rate_of_Interest;
    }

    public void setRate_of_Interest(double rate_of_Interest) {
        this.rate_of_Interest = rate_of_Interest;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public double getMonthly_emi() {
        return monthly_emi;
    }

    public void setMonthly_emi(double monthly_emi) {
        this.monthly_emi = monthly_emi;
    }

    public void print() {
        System.out.println("Principal: " + this.principal + " Monthly EMI: " + this.monthly_emi +
                " TotalAmount: " + this.total_amount + " Remaining amount: ");
    }
}
