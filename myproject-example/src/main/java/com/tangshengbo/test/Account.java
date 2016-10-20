package com.tangshengbo.test;

public class Account {

	private double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void addAmount(double amount) {
		
		balance += amount;

	}

	public void delAmount(double amount) {
		

		balance -= amount;

	}
}
