package kr.rootuser.banksystem.bankcore.api.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import kr.rootuser.banksystem.bankcore.CardNumber;
import kr.rootuser.banksystem.bankcore.Constants;
import kr.rootuser.banksystem.bankcore.api.Accounts;

public class BearAccounts implements Accounts {

	private Random rand = new Random();
	
	public BearAccounts(CardNumber cardNumber) {
		
	}

	@Override
	public List<String> list() {
		List<String> accounts = Stream.of(Constants.Accounts.values()).
				map(Enum::name).
				collect(Collectors.toList());
		return accounts;
	}

	@Override
	public int get(Constants.Accounts account) {
		return Math.abs(rand.nextInt());
	}
}
