package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseClass implements ITestListener {
	String filePath = null;
	ExtentTest test;
	//We need to make test object ThreadSafe. Initializing ThreadLocal object for that, and type of test is ExtentTest.
	ThreadLocal<ExtentTest> threadSafe = new ThreadLocal<ExtentTest>();
	ExtentReports extent = ExtentReporting.getReportObjecct();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		//Adding test object to ThreadSafe 
		threadSafe.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// threadSafe.get() is simply test object with threadsafe enabled
		threadSafe.get().log(Status.PASS, "Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		threadSafe.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadSafe.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		//onTestFailure(result);
	}

	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
