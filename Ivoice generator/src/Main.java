import controller.FileOperation;
import model.InvoiceHeader;
import model.InvoiceLine;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        FileOperation fo = new FileOperation();
        ArrayList<InvoiceHeader> invoiceList = fo.readFile();
        print(invoiceList);
        int id = 0;
        String datenonw = null;
        String username;
        int count;
        double itemprice = 0;
        String itemname;
        PrintWriter pw = new PrintWriter(new FileWriter("InvoiceHeader.csv", true));
        Scanner scanner = new Scanner(System.in);
        pw.append("\n");
        //a declare to flag to ensure that id in number
        boolean isValid3 = false;
        //a declare to flag to ensure that date in right pattern
        boolean isValid4 = false;
        while (isValid3 == false)
        {
            try
            {
                System.out.println("enter your id");
                id = Integer.parseInt(scanner.next());

                pw.append(id + ",");
                isValid3 = true;

            }
            catch (NumberFormatException e)
            {
                System.out.println("wrong data format enter id in numbers ");
            }

        }
        while (isValid4 == false) {

            System.out.println("Enter a date in dd-mm-yyyy format ");
            datenonw = scanner.next();
            if (datevalidation(datenonw) == true)
            {
                System.out.println("Date is valid");
                isValid4 = true;
            }
            else {
                System.out.println("Date is invalid try again");
            }

        }
        pw.append(datenonw + ",");
        System.out.println("enter your name ");
        username = scanner.next();
        pw.append(username.substring(0,1).toUpperCase()+username.substring(1));
        pw.close();
        PrintWriter pw2 = new PrintWriter(new FileWriter("InvoiceLine.csv", true));
        Scanner scanner2 = new Scanner(System.in);
       // pw2.append("\n");
        boolean isValid = false;
        //a declare to flag to ensure that the price not negative
        boolean isValid2 = false;
        char a = 'y';
        while (a == 'y' || a == 'Y')  // to ensure that the user press y or Y to add more purchases
        {
            while (isValid == false)
            {
                try
                {
                    pw2.append(id + ",");
                    isValid = true;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("wrong data format enter id in numbers ");
                }
            }
            System.out.println("enter your purchase ");
            itemname = scanner2.next();
            pw2.append(itemname.substring(0,1).toUpperCase()+itemname.substring(1)+",");
            while (isValid2 == false)// a declared value to ensure the price not in negative way
            {
                try
                {
                    System.out.println("enter price ");
                    itemprice = Integer.parseInt(scanner2.next());
                    if (itemprice <= 0)
                    {
                        throw new IllegalArgumentException("Only Positive Numbers & no Letters Please!");
                    } else
                    {
                        pw2.append(itemprice + ",");
                        isValid2 = true;
                    }
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());

                }
            }
            System.out.println("enter count ");
            count = Integer.parseInt(scanner2.next());
            pw2.append(count + " ");
            System.out.println("Continue(Y/N)");
            String resp = scanner2.next();
            a = resp.charAt(0);
            if (a == 'y' || a == 'Y') // condition that ask user if he write Y or y he will add more purchases
            {
                isValid = false;
                isValid2 = false;
            }

             pw2.append("\n");
        }
        pw2.close();

        print(invoiceList);
    }
    // function that introduce the date pattern should be
    private static  boolean datevalidation(String datenow)
    {
        boolean status = false;
        if (checkDate(datenow))
        {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            try
            {
                dateFormat.parse(datenow);
                status = true;
            }
            catch (Exception e)
            {
                status = false;
            }
        }
        return status;

    }
    // check the input date match the pattern
    static boolean checkDate(String date)
    {
        String pattern = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[0-2])-([0-9]{4})";
        boolean flag = false;
        if (date.matches(pattern))
        {
            flag = true;
        }
        return flag;

    }


// printing the files
    public static void print(ArrayList<InvoiceHeader> invoicelist) {

        for (InvoiceHeader header : invoicelist) {
            System.out.println("Invoice "+header.getInvoicenum());
            System.out.println("{");
            System.out.println(header.getInvoiceDate() + "," + header.getCustomerName());
            for (InvoiceLine invoiceLine : header.getLines())
                System.out.println(invoiceLine.getItemName() + "," + invoiceLine.getItemPrice() + "," + invoiceLine.getCount());
            System.out.println("}");
        }
    }
}