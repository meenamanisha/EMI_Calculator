package com.company.serviceUtils;

import com.company.model.Loan;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CalculateEMI {

    Map<String, Loan> user_emi_mapping;

    public CalculateEMI() {
        this.user_emi_mapping = new HashMap<>();
    }

    // initiate the loan EMI
    public void getLoanEMI(Loan loan) {
        // register the user into the map
        user_emi_mapping.put(loan.getBorrower_name(), loan);

        int time = loan.getNo_of_years();
        double rate = loan.getRate_of_Interest();
        double principal = loan.getPrincipal();

        // calculate simple interest and total amount to be paid
        double simple_interest = calculateInterest(rate, time, principal);
        double total_pay = loan.getPrincipal() + simple_interest;
        loan.setTotal_amount(total_pay);

        // calculate monthly EMI and no of EMIs
        int total_months = time * 12;
        double monthly_emi = Math.ceil(total_pay / total_months);
        loan.setMonthly_emi(monthly_emi);
    }

    // update EMI with lump sum
    public void receivePayment(String bank_name, String borrower_name, double lump_sum, int emi_no) {
        Loan loan = user_emi_mapping.get(borrower_name);
        Map<Integer, Double> lump_sum_map = loan.getLump_sum_map();
        lump_sum_map.put(emi_no, lump_sum_map.getOrDefault(emi_no, 0.0) + lump_sum);
    }

    // updating the EMI
    public void getBalance(String bank_name, String borrower_name, int emi_no) {
        Loan loan = user_emi_mapping.get(borrower_name);

        //get amount paid so far
        double total_amount_paid_so_far = getTotalPaidAmount(loan, emi_no);

        //get remaining no of emis = remaining amount / monthly emi
        int no_of_emis = (int) Math.ceil((loan.getTotal_amount() - total_amount_paid_so_far) / loan.getMonthly_emi());

        display(loan, (int) total_amount_paid_so_far, no_of_emis);
    }

    // get total lump sum amount till given emi no
    private double getLumpSumTillGivenEMI(TreeMap<Integer, Double> lump_sum_map, int emi_no) {
        double total_amount_paid = 0;
        for (Map.Entry<Integer, Double> entry : lump_sum_map.entrySet()) {
            if (entry.getKey() > emi_no) break;
            total_amount_paid += entry.getValue();
        }
        return total_amount_paid;
    }

    //get amount paid so far
    private double getTotalPaidAmount(Loan loan, int emi_no) {
        double total_paid = loan.getMonthly_emi() * emi_no;

        //add lump sum
        total_paid += getLumpSumTillGivenEMI(loan.getLump_sum_map(), emi_no);

        //If last EMI is greater than the total pay, would need to consider the lesser  month not the whole EMI
        total_paid = Math.min(loan.getTotal_amount(), total_paid);
        return total_paid;
    }

    // Calculate Simple interest
    private double calculateInterest(double r, int t, double p) {
        return Math.ceil((r * t * p) / 100);
    }

    //display in following format: bank_name borrower_name total_amount_paid no_of_emis_left
    private void display(Loan loan, int total_paid, int no_of_emis_left) {
        System.out.println(loan.getBank_name() +
                " " + loan.getBorrower_name() +
                " " + total_paid +
                " " + no_of_emis_left);
    }
}
