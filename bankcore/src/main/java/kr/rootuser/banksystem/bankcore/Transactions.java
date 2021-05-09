package kr.rootuser.banksystem.bankcore;

import kr.rootuser.banksystem.bankcore.api.Cards;

public class Transactions {
	private String tractionsId;
	private Cards bearCards;
	private boolean isVerified;

	public Transactions(Cards bearCards) {
		this.bearCards = bearCards;
		this.tractionsId = setNewTransaction();
		this.isVerified = false;
	}

	public void verifyPin() {
		this.isVerified = true;
	}

	public String getTractionsId() {
		return tractionsId;
	}

	public Cards getBearCards() {
		return bearCards;
	}

	public boolean isVerified() {
		return isVerified;
	}

	private String setNewTransaction() {
		String cardNum = bearCards.getCardNumber().getNumber();
		return "abc" + cardNum.substring(cardNum.length() - 4);
	}
}
