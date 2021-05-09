package kr.rootuser.banksystem.bankcore.api.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import kr.rootuser.banksystem.bankcore.CardNumber;
import kr.rootuser.banksystem.bankcore.Constants;
import kr.rootuser.banksystem.bankcore.api.Accounts;

public class BaearAccountsTest {

	/**
	 * get list of accounts
	 */
	@Test
	public void listTest() {
		Accounts account = new BearAccounts(new CardNumber("CARD12345"));
		List<String> accList = account.list();
		assertThat(accList.size()).isGreaterThan(0);
	}

	/**
	 * get a specific account balance
	 */
	@Test
	public void getTest() {
		Accounts account = new BearAccounts(new CardNumber("CARD12345"));
		int balance = account.get(Constants.Accounts.BALANCE);
		assertThat(balance).isGreaterThan(-1);
	}
}
