package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;


import static io.restassured.RestAssured.given;

public class ProductsExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

//21. Extract the limit

    @Test
    public void test021() {
        int limit = response.extract().path("limit");
        System.out.println(" limit is : " + limit);


    }

    //22. Extract the total
    @Test
    public void test022() {
        int total = response.extract().path("total");
        System.out.println("total is:" + total);

    }

    //23. Extract the name of 5th product
    @Test
    public void test023() {
        String name = response.extract().path("data[4].name");
        System.out.println(name);

    }

    //24. Extract the names of all the products
    @Test
    public void test024() {
        List<String> name = response.extract().path("data.name");
        System.out.println(name);


    }

    //25. Extract the productId of all the products
    @Test
    public void test025() {
        List<String> productid = response.extract().path("data.id");
        System.out.println(productid);

    }

    //26. Print the size of the data list
    @Test
    public void test026() {
        List<String> size = response.extract().path("data");
        System.out.println(size);

    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test027() {
        HashMap<?, ?> productData = response.extract().path("data[3]");
        System.out.println(productData);


    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test028() {
        String data = response.extract().path("data[8].model");
        System.out.println("Model of Energizer - N Cell E90 Batteries (2-Pack): " + data);

    }

    //29. Get all the categories of 8th products
    @Test
    public void test029() {
        List<String> eighthProduct = response.extract().path("data[7].categories");
        System.out.println(eighthProduct);
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test030() {
        List<String> a50115 = response.extract().path("data[3].categories");
        System.out.println(a50115);

    }

    //31. Get all the descriptions of all the products
    @Test
    public void test0() {
        List<String> descriptionsAllProducts = response.extract().path("data.description");
        System.out.println(descriptionsAllProducts);

    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test032() {
        List<String> allIdsAllProducts = response.extract().path("data.categories.id");
        System.out.println(allIdsAllProducts);

    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test033() {
        List<String> typeHardGood = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println(typeHardGood);
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034() {
        List<String> totalCategory = response.extract().path("data[1].categories");
        int totalSize = totalCategory.size();
        System.out.println(totalSize);


    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test035() {
        List<String> createdAtLess = response.extract().path("data.findAll{it.price <= 5.49}.createdAt");
        System.out.println(createdAtLess);


    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test036() {
        List<HashMap<?,?>> nameCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println(nameCategories);

    }

    //37. Find the manufacturer of all the products
    @Test
    public void test037() {
        List<String> manufacturerAllProduct = response.extract().path("data.manufacturer");
        System.out.println(manufacturerAllProduct);

    }

    //38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test038() {
        List<String> imagesViaManufacturer = response.extract().path("data.findAll{it.name.startsWith('Energizer')}.image");
        System.out.println(imagesViaManufacturer );

    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test039() {
        List<String> createdAtMore = response.extract().path("data.findAll{it.price >= 5.99}.createdAt");
        System.out.println(createdAtMore);
    }


    //40. Find the url of all the products
    @Test
    public void test040() {
        List<String> urlOfAll= response.extract().path("data.url");
        System.out.println(urlOfAll);

    }
}
