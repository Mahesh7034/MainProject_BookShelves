package com.urbanLadder.reRun;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
 
public class RetryAnalyzer implements IRetryAnalyzer {
	private int retryCount = 0;
	private static final int maxRetryCount = 1; // Customize retry limit
 
	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess() && retryCount < maxRetryCount) {
			retryCount++;
			System.out.println("Retrying failed scenario: " + result.getName() + " | Attempt #" + retryCount);
			return true;
		}
		return false;
	}
 
}
