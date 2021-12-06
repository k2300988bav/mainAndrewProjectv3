package goodstest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class BaseTest {

    public WebDriver driver;

    @BeforeTest
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "C:\\MyPrograms\\WebDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //        FileSource filesRoot = new SingleRootFileSource("wiremock-jre8-standalone-2.31.0.jar");
//        "src/main/resources/mockData"
//        WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options()
//                .bindAddress("localhost")
//                .port(Integer.parseInt("8088")).usingFilesUnderDirectory("src/main/resources/mockData"));
//                .fileSource(filesRoot));
//        wireMockServer.start();
//        $ java -jar wiremock-jre8-standalone-2.31.0.jar

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
