package com.company.model;

import java.util.TreeMap;

public class Loan {
    private String bank_name;
    private String borrower_name;
    private Integer principal;
    private Integer no_of_years;
    private Integer no_of_emi;
    private Integer rate_of_Interest;
    private Integer total_amount;
    private Integer monthly_emi;
    private TreeMap<Integer, Integer> lump_sum_map;

    public TreeMap<Integer, Integer> getLump_sum_map() {
        if(this.lump_sum_map==null)
            this.lump_sum_map = new TreeMap<>();
        return lump_sum_map;
    }

    public void setLump_sum_map(TreeMap<Integer, Integer> lump_sum_map) {
        if(this.lump_sum_map==null)
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

    public Integer getPrincipal() {
        return principal;
    }

    public void setPrincipal(Integer principal) {
        this.principal = principal;
    }

    public Integer getNo_of_years() {
        return no_of_years;
    }

    public void setNo_of_years(Integer no_of_years) {
        this.no_of_years = no_of_years;
    }

    public Integer getRate_of_Interest() {
        return rate_of_Interest;
    }

    public void setRate_of_Interest(Integer rate_of_Interest) {
        this.rate_of_Interest = rate_of_Interest;
    }

    public Integer getNo_of_emi() {
        return no_of_emi;
    }

    public void setNo_of_emi(Integer no_of_emi) {
        this.no_of_emi = no_of_emi;
    }

    public Integer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Integer total_amount) {
        this.total_amount = total_amount;
    }

    public Integer getMonthly_emi() {
        return monthly_emi;
    }

    public void setMonthly_emi(Integer monthly_emi) {
        this.monthly_emi = monthly_emi;
    }

    public void print(){
        System.out.println("Principal: " + this.principal + " Monthly EMI: " + this.monthly_emi +
                " TotalAmount: " + this.total_amount+" Remaining amount: "+
                "No of EMIs " +this.no_of_emi);
    }
}
