package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTestclasses {

    public class StoresCURDTest extends TestBase_Stores {

        @Test // Get all list
        public void getAllList() {
            given()
                    .when()
                    .log().all()
                    .get()
                    .then().log().all().statusCode(200);
        }

        @Test // Post new and retrieve the ID
        public void PostNewRetrieveID() {

            StorePojo storesPojo = new StorePojo();
            storesPojo.setName("Apple Store");
            storesPojo.setType("Chain retail store");
            storesPojo.setAddress("400 Oxford Street, London");
            storesPojo.setAddress2("235 Regent Street, London");
            storesPojo.setCity("London");
            storesPojo.setState("Central London");
            storesPojo.setZip("W1C");
            storesPojo.setLat((int) 38.188862);
            storesPojo.setLng((int) 15.543990);
            storesPojo.setHours("Mon - Fri: 10am-9pm; Sat: 10am-8pm; Sun: 12pm-6pm");

            // List<String> services = new ArrayList<>();
            // services.add("iPhone 14 Pro");

            Response response = given()
                    .header("Content-Type", "application/json")
                    .body(storesPojo)
                    .post();
            response.then().log().all().statusCode(201);
        }


        @Test // Update ID via patch
        public void UpdateID() {
            StorePojo storesPojo = new StorePojo();
            storesPojo.setName("Apple Store UK");
            storesPojo.setType("Dynamic chain retail store");

            Response response = given()
                    .header("content-Type", "Application/json")
                    .pathParams("id", "4")
                    .body(storesPojo)
                    .patch("/{id}");
            response.then().log().all().statusCode(200);

        }

        @Test // Put test
        public void PutDataTest() {
            StorePojo storesPojo = new StorePojo();
            storesPojo.setName("Apple Store");
            storesPojo.setType("Chain retail store");
            storesPojo.setAddress("400 Oxford Street, London");
            storesPojo.setAddress2("");
            storesPojo.setCity("London");
            storesPojo.setState("Central London");
            storesPojo.setZip("W1C");
            storesPojo.setLat((int) 38.188862);
            storesPojo.setLng((int) 15.543990);
            storesPojo.setHours("Mon - Fri: 10am-10pm; Sat: 11am-8pm; Sun: Closed");

            // List<String> services = new ArrayList<>();
            // services.add("iPhone 14 Pro");

            Response response = given()
                    .header("Content-Type", "application/json")
                    .body(storesPojo)
                    .post();
            response.then().log().all().statusCode(201);

        }

        @Test // Delete ID
        public void DeleteID() {
            Response response = given()
                    .pathParams("id", "10")
                    .when()
                    .delete("/{id}");
            response.then().log().all().statusCode(404);
        }
    }
}
