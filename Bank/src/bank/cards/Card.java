package bank.cards;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Calendar;

import bank.accounts.Transaction;

public abstract class Card {
	
	private String cardNumber;
	private int csv;
	private Date expireDate;
	private ArrayList<Transaction> transactions;
	
	public Card(String num, int csv, Date expire) {
		setCardNumber(num);
		setCsv(csv);
		setExpireDate(expire);
		setTransactions(new ArrayList<Transaction>());
	}
	
	public Card() {
		//TODO: Generate card
		
		//Generates Valid CC or Debit Card Number
		//Uses Luhn's Algorithm to check for validity
		//Restarts the process if number is invalid
		Random rand = new Random();
		final int numOfDigits = 16;
		String cNumber = "";
		boolean isValid = false;
		int nSum = 0;
		
		//If the number is not Valid
		while(!isValid)
		{
			for(int i = 0; i < numOfDigits; i++)
			{
				int digit = rand.nextInt(9) + 1; //Add the random digit to the cNumber
				cNumber = cNumber + (digit);
				
				if(i % 2 == 0) //If we are in an even position of the sequence
				{
					digit *= 2; //double the digit
				}
				
				//Add digit to the sum of all digits
				nSum += digit / 10; //If digit is a 2-digit number, 
				nSum += digit % 10; // add both digits to the sum of all digits
				
			}
			
			//After the loop
			if (nSum % 10 == 0) // If the sum of all digits is divisible by 10
				isValid = true; //Luhn's Algorithm gave us a valid number, thus we exit the loop
			else
			{
				nSum = 0 //We have an invalid number and must restart the process
				cNumber = "";
			}
		}
		
		cardNumber = cNumber; // initialize cardNumber field with result
		
		//Generating the CSV number
		int csvNum = 0; 
		for(int i = 0; i < 3; i++)
		{
			cvsNum *= 10; //Adds a right most digit to the number
			csvNum += rand.nextInt(9) + 1; //fill that space with a random number
		}
		
		csv = csvNum;
		
		//Generating exp. date
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault()); //create instance of Calendar
        	Date date = new Date(); //create Current Date object
        	localCalendar.setTime(date); //Set localCalendar to current date
        	localCalendar.add(Calendar.YEAR, 5); //Add five years for expiration to local calendar

        	expireDate = localCalendar.getTime(); //Add the new time to expire date
	}
	
	/**
	 * @return The 16 digit credit card number
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * @param cardNumber The 16 digit creditcard number
	 */
	private void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber; 
	}
	
	/**
	 * Add a transaction to the account
	 * @param transaction Transaction object
	 * @return 0 if successful, 1 if invalid funds, 2 for SQLException
	 */
	public int addTransaction(Transaction transaction) {
		//TODO:
		getTransactions().add(transaction);
		return 0;
	}
	
	/**
	 * @return Retrieve all transactions
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions Set the transactions retrieved from database
	 * @apiNote Private because this should not be changed outside
	 */
	private void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	/**
	 * @return The 3 digit security code
	 */
	public int getCsv() {
		return csv;
	}
	
	/**
	 * @param csv The 3 digit security code
	 */
	public void setCsv(int csv) {
		this.csv = csv;
	}

	/**
	 * @return The date when the card is expired
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * @param expireDate The date when the card is expired
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	

}
