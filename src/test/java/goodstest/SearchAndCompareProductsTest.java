package goodstest;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.MainPage;

import java.io.IOException;


public class SearchAndCompareProductsTest extends BaseTest {

    MainPage mainPage;

    @BeforeTest
    public void prepareData() {
        mainPage = PageFactory.initElements(driver, MainPage.class);
    }

    @Test(dataProvider = "expectedGoods")
    public void checkingTheConformityOfGoods(TestHelper.Goods goods) throws IOException {
        driver.get(mainPage.getUrl());
        mainPage.writeSearchQuery(goods.name);
        mainPage.clickSearchBtn();
        System.out.println(goods.name);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(mainPage.getProductCode(), goods.code);
        softAssert.assertEquals(mainPage.getProductAvailability(), goods.availability);
        softAssert.assertEquals(mainPage.getProductPrise(), goods.price);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[] expectedGoods() {
        return TestHelper.getGods().toArray();
    }
}
