package kr.rootuser.banksystem.bankcore;

public class PinNumber {
	private String number;

	public PinNumber(String number) {
		setNumber(number);
	}

	public String getNumber() {
		return number;
	}

	private void setNumber(String number) {
		this.number = number;
	}
}
