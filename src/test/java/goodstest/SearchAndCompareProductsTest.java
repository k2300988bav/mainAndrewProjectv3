package goodstest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.MainPage;

import java.io.IOException;
import java.util.ArrayList;


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
        softAssert.assertEquals(mainPage.productBlock.findElement(By.xpath(".//span[@class=\"product-thumb__id\"]")).getText(), goods.code);
        softAssert.assertEquals(mainPage.productBlock.findElement(By.xpath(".//span[@class=\"product-thumb__availability product-thumb__availability--available\"]")).getText()
                , goods.availability);
        softAssert.assertEquals(mainPage.productBlock.findElement(By.xpath("//div[@class=\"product-thumb__inner\"]//span[@class=\"product-thumb__price\"]")).getText()
                , goods.price);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[] expectedGoods() {
        ArrayList<TestHelper.Goods> expectedGoods = TestHelper.getGods();
        TestHelper.Goods[] goods = new TestHelper.Goods[expectedGoods.size()];
        for (int i = 0; i < expectedGoods.size(); i++) {
            goods[i] = expectedGoods.get(i);
        }
        return goods;
    }
}
