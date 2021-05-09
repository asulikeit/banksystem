package kr.rootuser.banksystem.bankcore.api.impl;

import kr.rootuser.banksystem.bankcore.CardNumber;
import kr.rootuser.banksystem.bankcore.PinNumber;

abstract class CardInformation {

	protected String cardNumber;

	protected void setCard(CardNumber cardNumber) {
		this.cardNumber = cardNumber.getNumber();
	}

	protected CardNumber getCardNumber() {
		return new CardNumber(this.cardNumber);
	}

	protected boolean verifyCard(CardNumber cardNumber) {
		return true;
	}

	protected boolean verifyPin(PinNumber pinNumber) {
		return this.cardNumber.substring(this.cardNumber.length() - 4).
				equals(pinNumber.getNumber().substring(pinNumber.getNumber().length() - 4));
	}
}