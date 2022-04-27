package com.company.serviceUtils;

import com.company.model.Loan;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CalculateEMI {

    Map<String, Loan> userEmiMapping;

    public CalculateEMI() {
        this.userEmiMapping = new HashMap<>();
    }

    // initiate the loan EMI
    public void getLoanEMI(Loan loan) {

        // register the user into the map
        userEmiMapping.put(loan.getBorrower_name(), loan);

        int time = loan.getNo_of_years();
        int rate = loan.getRate_of_Interest();
        int principal = loan.getPrincipal();

        // calculate simple interest and total amount to be paid
        double simpleInterest = calculateInterest(rate, time, principal);
        double totalPay = loan.getPrincipal() + simpleInterest;
        loan.setTotal_amount((int) totalPay);

        // calculate monthly EMI and no of EMIs
        double totalMonths = time * 12;
        int monthlyEMI = (int) Math.ceil(totalPay / totalMonths);
        loan.setMonthly_emi(monthlyEMI);
        loan.setNo_of_emi((int) totalMonths);
    }


    // update EMI with lump sum
    public void receivePayment(String bankName, String borrowerName, int lumpSum, int emiNo) {
        Loan loan = userEmiMapping.get(borrowerName);
        Map<Integer, Integer> lump_sum_map = loan.getLump_sum_map();
        lump_sum_map.put(emiNo, lump_sum_map.getOrDefault(emiNo, 0) + lumpSum);
    }

    // updating the EMI
    public void getBalance(String bankName, String borrowerName, int emiNo) {
        Loan loan = userEmiMapping.get(borrowerName);

        int totalPaid;
        int no_of_emis = 0;
        if (loan.getLump_sum_map().size() < 1) {
            totalPaid = loan.getMonthly_emi() * emiNo;
            no_of_emis = loan.getNo_of_emi() - emiNo;
        } else {
            totalPaid = calculateTotalPaidSoFar(loan.getLump_sum_map(), emiNo);
            int remainingAmount = loan.getTotal_amount() - totalPaid;
//            if(remainingAmount<loan.getMonthly_emi())
//                no_of_emi
        }
        display(loan, totalPaid, no_of_emis);
    }

    private int calculateTotalPaidSoFar(TreeMap<Integer, Integer> lump_sum_map, int emi) {
        int prevEmi = 1;
        int totalAmountPaid = 0;
        for (Map.Entry<Integer, Integer> entry : lump_sum_map.entrySet()) {
            totalAmountPaid += (Math.min(emi, entry.getValue()) - prevEmi + 1) * entry.getValue();
            if (entry.getKey() > emi) break;
            prevEmi = entry.getKey();
        }
        return totalAmountPaid;

    }

    private double calculateInterest(int r, int t, int p) {
        return Math.ceil((r * t * p) / 100.0);
    }

    private void display(Loan loan, int totalPaid, int emis) {
        System.out.println("BankName\tBorrower Name\t    Amount Paid\t\t\t EMIs Left");
        System.out.println(loan.getBank_name() +
                "  \t\t" + loan.getBorrower_name() +
                "    \t\t\t" + totalPaid +
                "\t\t\t\t " + emis);
    }
}
