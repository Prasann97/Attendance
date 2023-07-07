package com.example.attendance.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Transaction implements Comparable<Transaction>{

	private int txnid;
	 @JsonFormat(pattern = "dd/MM/yyyy")
	private Date txDate;
	private char txType;
	private int acctId;
	private double amount;

	public int getTxnid() {
		return txnid;
	}

	public void setTxnid(int txnid) {
		this.txnid = txnid;
	}

	public Date getTxDate() {
		return txDate;
	}

	public void setTxDate(Date txDate) {
		this.txDate = txDate;
	}

	public char getTxType() {
		return txType;
	}

	public void setTxType(char txType) {
		this.txType = txType;
	}

	public int getAcctId() {
		return acctId;
	}

	public void setAcctId(int acctId) {
		this.acctId = acctId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [txnid=" + txnid + ", txDate=" + txDate + ", txType=" + txType + ", acctId=" + acctId
				+ ", amount=" + amount + "]";
	}

	@Override
	public int compareTo(Transaction o) {
		try {
			if(new SimpleDateFormat("yyyy-MM-dd").parse(txDate.toString())==new SimpleDateFormat("yyyy-MM-dd").parse(o.getTxDate().toString()))
				return 0;
			else if(txDate.after(getTxDate()))
				return 1;
			else
				return -1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public List<Transaction> getSorted(List<Transaction> transactionList)
	{
		for (int i = 0; i < transactionList.size(); i++) {
			for(int j=i+1;j<transactionList.size();j++)
			{
				if(transactionList.get(i).getTxDate().after(transactionList.get(j).getTxDate()))
				{
					
				}
			}
		}
		return null;
	}

}
