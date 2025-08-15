package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;          // How many times we've retried
    private static final int maxRetryCount = 2; // Max retries allowed

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("🔄 Retrying test: " + result.getName() + 
                               " | Attempt " + (retryCount + 1));
            return true; // Retry the test
        }
        return false; // No more retries
    }
}
