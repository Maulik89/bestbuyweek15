package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StoresExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }

    //1. Extract the limit
    @Test
    public void test01() {
        int limit = response.extract().path("limit");
        System.out.println("value of limit is : " + limit);
        Assert.assertEquals(10, limit); //one way of assertion
        response.body("limit", equalTo(10)); // second way of assertion


    }

    //2. Extract the total
    @Test
    public void test02() {
        int total = response.extract().path("total");
        System.out.println("value of total is : " + total);
        Assert.assertEquals(1561, total); //one way of assertion
        response.body("total", equalTo(1561)); // second way of assertion


    }

    //3. Extract the name of 5th store
    @Test
    public void test03() {
        String nameofstore = response.extract().path("data[4].name");
        System.out.println(nameofstore);


    }

    //4. Extract the names of all the store
    @Test
    public void test04() {
        List<String> storeNames = response.extract().path("data.name");
        System.out.println("List of store names :" + storeNames);

    }

    //5. Extract the storeId of all the store
    @Test
    public void test05() {

        List<String> storeID = response.extract().path("data.id");
        System.out.println("List of storeID names :" + storeID);


    }

    //6. Print the size of the data list
    @Test
    public void test06() {
        List<String> size = response.extract().path("data");
        System.out.println("size of data list:" + size);


    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test07() {

        List<String> storenamecloud = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("St Cloud value:" + storenamecloud);

    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test08() {
        List<String> addressRochester = response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("Rochester address :" + addressRochester);

    }

    //9. Get all the services of 8th store
    @Test
    public void test09() {
        List<String> services8thstore = response.extract().path("data[7].services");
        System.out.println("8th store data:" + services8thstore);


    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<String> windowsstore = response.extract().path("data.services.findAll{it.name=='Windows store'}");
        System.out.println("windows store data:" + windowsstore);


    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        List<String> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println(storeId);

    }

    //12. Get id of all the store
    @Test
    public void test012() {
        List<String> storeId = response.extract().path("data.id");
        System.out.println(storeId);
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<String> storeName = response.extract().path("data.findAll{it.state =='ND'}.name");
        System.out.println(storeName);


    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {

        List<Map<String, ?>> servicesRoch = response.extract().path("data[8].services");
        int size = servicesRoch.size();
        System.out.println(size);
    }


    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<String> createdAt = response.extract().path("data.services.findAll{it.name == 'Windows store'}.createAt");
        System.out.println(createdAt);

    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<String> servicesFargo = response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println(servicesFargo);
    }


    //17. Find the zip of all the store
    @Test
    public void test017() {
        List<String> allZips = response.extract().path("data.zip");
        System.out.println(allZips);

    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<String> RosevilleZips = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println(RosevilleZips);

    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<String> homeTheater= response.extract().path("data.services");
        System.out.println(homeTheater);

    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {
        List<String> storeLat = response.extract().path("data.lat");
        System.out.println(storeLat);

    }
}
