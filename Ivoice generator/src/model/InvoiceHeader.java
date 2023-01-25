package model;

import java.util.ArrayList;

public class InvoiceHeader
{
    private int invoicenum;
    private String invoiceDate;
    private String customerName;
    public ArrayList<InvoiceLine> lines;

    public InvoiceHeader()
    {
        this.invoicenum = 0;
        this.invoiceDate = "";
        this.customerName = "";
        this.lines = new ArrayList<>();
    }
    public InvoiceHeader(int invoiceNum, String invoiceDate, String customerName,ArrayList<InvoiceLine> lines )
    {
        this.invoicenum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.lines = lines;
    }
    public ArrayList<InvoiceLine> getLines() {
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines)
    {
        this.lines = lines;
    }

    public int getInvoicenum() {
        return invoicenum;
    }
    public void setInvoicenum(int invoicenum) {
        this.invoicenum = invoicenum;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
