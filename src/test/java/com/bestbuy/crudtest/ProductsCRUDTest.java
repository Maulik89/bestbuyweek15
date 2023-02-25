package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;

import io.restassured.response.Response;

import org.junit.Test;


import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBase_Products {

    @Test // Get all list
    public void getAllList() {
        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);
    }


    // Post new and retrieve ID
    @Test
    public void postNewRetrieveID() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Apple");
        productPojo.setType("Ceramic");
        productPojo.setPrice(String.valueOf(new BigDecimal("1099")));
        productPojo.setUpc("123456");
        productPojo.setShipping(String.valueOf(50));
        productPojo.setDescription("The iPhone is a series of smartphones made by Apple Inc since 2007.");
        productPojo.setManufacturer("Foxconn");
        productPojo.setModel("iPhone 14 Pro - 128GB");
        productPojo.setURL("https://www.apple.com/uk/iphone-14-pro/");
        productPojo.setImage("https://www.apple.com/euro/iphone-14-pro/a/screens_alt/images/overview/design/design_startframe__cffzwjeyro2q_large.jpg");

//        List<String> categories = new ArrayList<>();
//        categories.add("iPhone14Pro");
//        categories.add("iPhone 14 Pro");
//        categories.add("2023-02-24 06:00 abc");
//        categories.add("2023-02-24 05:55 abc");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .post();
        response.then().log().all().statusCode(201);
    }

    @Test // Update Id via patch
    public void updateID() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setManufacturer("Foxconn");

        Response response = given()
                .header("content-Type","Application/json")
                .pathParams("id","12345")
                .body(productPojo)
                .patch("/{id}");
        // response.then().log().all().statusCode(200);

    }

    @Test // Put test
    public void PutTest(){

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Apple Inc");
        productPojo.setType("Ceramic D");
        productPojo.setPrice(String.valueOf(new BigDecimal("1099")));
        productPojo.setUpc("012345678910");
        productPojo.setShipping(String.valueOf(50));
        productPojo.setDescription("Switch from Android to iPhone");
        productPojo.setManufacturer("Pegatron");
        productPojo.setModel("iPhone 14 Pro");
        productPojo.setURL("https://www.apple.com/uk/iphone-14-pro/");
        productPojo.setImage("https://www.apple.com/euro/iphone-14-pro/a/screens_alt/images/overview/design/design_startframe__cffzwjeyro2q_large.jpg");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParams("id","12345")
                .body(productPojo)
                .put("/{id}");
        // response.then().log().all().statusCode(200);

    }

    @Test // Delete ID
    public void DeleteID() {
        Response response = given()
                .pathParams("id","78965")
                .when()
                .delete("/{id}");
        response.then().log().all().statusCode(404);
        response.prettyPrint();
    }

    @Test // Retrieve ID and validate
    public void RetrieveIdAndValidate() {
        Response response = given()
                .pathParams("id","0123456")
                .when()
                .get("/{id}");
        response.then().statusCode(404);

    }
}



