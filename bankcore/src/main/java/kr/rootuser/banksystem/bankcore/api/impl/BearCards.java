package kr.rootuser.banksystem.bankcore.api.impl;

import kr.rootuser.banksystem.bankcore.CardNumber;
import kr.rootuser.banksystem.bankcore.PinNumber;
import kr.rootuser.banksystem.bankcore.api.Cards;

public class BearCards extends CardInformation implements Cards {

	public BearCards(CardNumber cardNumber) {
		verifyCard(cardNumber);
		setCard(cardNumber);
	}

	@Override
	public CardNumber getCardNumber() {
		return super.getCardNumber();
	}

	@Override
	public boolean checkPin(PinNumber pinNumber) {
		return verifyPin(pinNumber);
	}

}
