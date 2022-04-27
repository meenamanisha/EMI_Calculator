package com.company.controller;

import com.company.model.Loan;
import com.company.serviceUtils.CalculateEMI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LedgerBoardController {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/com/company/input/emiInput.txt");
        Scanner sc = new Scanner(file);
        LedgerBoardController ledgerCo = new LedgerBoardController();

        CalculateEMI calculateEMI = new CalculateEMI();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] operations = line.split("\\s+");
//            System.out.println(operations[0]);
            switch (operations[0].toUpperCase()) {
                case "LOAN":
                    Loan loan = ledgerCo.createLoanModel(operations);
                    calculateEMI.getLoanEMI(loan);
                    break;
                case "PAYMENT":
                    calculateEMI.receivePayment(operations[1], operations[2], Integer.parseInt(operations[3]), Integer.parseInt(operations[4]));
                    break;
                case "BALANCE":
                    calculateEMI.getBalance(operations[1], operations[2], Integer.parseInt(operations[3]));
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        }

    }

    private Loan createLoanModel(String[] inputs) {
        Loan loan = new Loan();
        loan.setBank_name(inputs[1]);
        loan.setBorrower_name(inputs[2]);
        loan.setPrincipal(Integer.valueOf(inputs[3]));
        loan.setNo_of_years(Integer.valueOf(inputs[4]));
        loan.setRate_of_Interest(Integer.valueOf(inputs[5]));
        return loan;
    }
}
