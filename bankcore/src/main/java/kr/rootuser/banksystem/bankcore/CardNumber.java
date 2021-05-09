package kr.rootuser.banksystem.bankcore;

public class CardNumber {
	private String number;

	public CardNumber(String number) {
		setNumber(number);
	}

	public String getNumber() {
		return number;
	}

	private void setNumber(String number) {
		this.number = number;
	}
}
