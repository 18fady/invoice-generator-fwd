package controller;

import model.InvoiceHeader;
import model.InvoiceLine;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperation {
   //reading the files
    public ArrayList<InvoiceHeader> readFile() {
        ArrayList<InvoiceHeader> invoicelist = new ArrayList<InvoiceHeader>();

        try {
            String fileHeader = "InvoiceHeader.csv";
            String fileItem = "InvoiceLine.csv";

            FileInputStream fis = new FileInputStream(fileHeader);
            Scanner scHeader = new Scanner(fis);    //file to be scanned
            InvoiceHeader header = new InvoiceHeader();
            while (scHeader.hasNextLine()) {
                String[] headerLine = scHeader.nextLine().split(",");
                header = new InvoiceHeader(Integer.parseInt(headerLine[0]), headerLine[1], headerLine[2], new ArrayList<>());
                FileInputStream fisItem = new FileInputStream(fileItem);
                Scanner scItem = new Scanner(fisItem);
                while (scItem.hasNextLine()) {
                    String[] item = scItem.nextLine().split(",");
                    if (Integer.parseInt(item[0]) == header.getInvoicenum()) {
                        InvoiceLine invoiceLine = new InvoiceLine(Integer.parseInt(item[0]), item[1], Double.parseDouble(item[2]), Integer.parseInt(item[3]));
                        header.lines.add(invoiceLine);
                    }
                }
                invoicelist.add(header);
            }

        } catch (Exception e) {
            System.out.print(e);
        }
        return invoicelist;
    }
}