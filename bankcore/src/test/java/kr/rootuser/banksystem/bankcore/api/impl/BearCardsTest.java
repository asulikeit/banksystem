package kr.rootuser.banksystem.bankcore.api.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import kr.rootuser.banksystem.bankcore.CardNumber;
import kr.rootuser.banksystem.bankcore.PinNumber;
import kr.rootuser.banksystem.bankcore.api.Cards;

public class BearCardsTest {

	/**
	 * verification the pin for the card
	 */
	@Test
	public void checkPin() {
		Cards card = new BearCards(new CardNumber("CARD12345"));

		PinNumber pinNumber = new PinNumber("2345");
		boolean isCorrect = card.checkPin(pinNumber);
		assertThat(isCorrect).isTrue();

		PinNumber wrongPin = new PinNumber("2346");
		boolean wrongResult = card.checkPin(wrongPin);
		assertThat(wrongResult).isFalse();
	}

}
