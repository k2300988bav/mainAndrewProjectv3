package goodstest;

import io.restassured.RestAssured;
import wiremock.net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TestHelper {

    public static ArrayList<Goods> getGods() {
        String localUrl = "http://localhost:8080/api/goods";
        JSONObject resp = RestAssured.given().get(localUrl)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().as(JSONObject.class);
        resp.get("goods");

        ArrayList<LinkedHashMap> response = (ArrayList<LinkedHashMap>) resp.get("goods");
        ArrayList<Goods> goodsTestData = new ArrayList<>();
        for (LinkedHashMap respo : response) {
            String name = (String) respo.get("name");
            String code = (String) respo.get("code");
            String price = (String) respo.get("price");
            String availability = respo.get("availability").toString();
            goodsTestData.add(new Goods(name, price, availability, code));
        }
        return goodsTestData;
    }

    public static class Goods {

        public String name;
        public String price;
        public String availability;
        public String code;

        public Goods(String name, String price, String availability, String code) {
            this.name = name;
            this.code = code;
            this.price = price;
            this.availability = availability;
        }

        public String toString() {
            return "\nGoods" +
                    "\nname = " + this.name +
                    "\nprice = " + this.price +
                    "\navailability =  " + this.availability +
                    "\ncode = " + this.code;
        }
    }
}
