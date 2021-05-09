package kr.rootuser.banksystem.bankcore.api;

import java.util.List;

import kr.rootuser.banksystem.bankcore.Constants;

public interface Accounts {

	List<String> list();

	int get(Constants.Accounts account);

}