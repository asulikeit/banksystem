package kr.rootuser.banksystem.bankcore.api.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.rootuser.banksystem.bankcore.CardNumber;
import kr.rootuser.banksystem.bankcore.Constants;
import kr.rootuser.banksystem.bankcore.PinNumber;
import kr.rootuser.banksystem.bankcore.api.Bank;

public class BearBankTest {

	private Bank bank;

	@Before
	public void setup() {
		bank = new BearBank();
	}

	/**
	 * 1) insert card (4 or more characters)
	 * 2) enter pin number (the last 4 digits	for card number - to keep simple)
	 * 3) list accounts
	 * 4) get an account
	 */
	@Test
	public void testNormalFlow() {
//		1) insert card
		CardNumber cardNumber = new CardNumber("CARD12345");
		String transactionId = bank.insertCard(cardNumber);
		assertThat(transactionId).isNotEmpty();
		assertThat(transactionId.length()).isGreaterThan(0);

//		2) enter pin number
		PinNumber pinNumber = new PinNumber("2345");
		boolean isCorrect = bank.enterPin(transactionId, pinNumber);
		assertThat(isCorrect).isTrue();

//		3) list accounts
		List<String> accList = bank.listAccounts(transactionId);
		assertThat(accList.size()).isGreaterThan(0);

//		4) get an account
		int account = bank.getAccount(transactionId, Constants.Accounts.valueOf(accList.get(0)));
		assertThat(account).isGreaterThan(-1);
	}

	/**
	 * verification with card number and pin number
	 */
	@Test
	public void checkPin() {
		CardNumber cardNumber = new CardNumber("CARD12345");
		PinNumber pinNumber = new PinNumber("2345");
		boolean isCorrect = bank.checkPin(cardNumber, pinNumber);
		assertThat(isCorrect).isTrue();

		PinNumber wrongPin = new PinNumber("2346");
		boolean wrongResult = bank.checkPin(cardNumber, wrongPin);
		assertThat(wrongResult).isFalse();
	}
}
