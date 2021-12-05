package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }


    public String getUrl() throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("src/main/resources/config.properties");
        prop.load(file);
        return prop.getProperty("mainUrl");
    }

    @FindBy(xpath = "//div[@class=\"top-block top-block--search\"]//input[@class=\"search-form__input\"]")
    private WebElement search;
    @FindBy(xpath = "//div[@class=\"top-block top-block--search\"]//button[@class=\"btn btn--search search-form__btn\"]")
    private WebElement searchBtn;
    @FindBy(xpath = "//div[@class=\"product-thumb__inner\"]")
    public WebElement productBlock;

    public void writeSearchQuery(String text) {
        if (search.isDisplayed()) {
            search.click();
            search.sendKeys(text);
        }
    }

    public void clickSearchBtn() {
        if (searchBtn.isDisplayed()) {
            searchBtn.click();
        }
    }
}
