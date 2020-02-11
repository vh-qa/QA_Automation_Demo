package com.test;

import com.google.gson.Gson;
import com.test_demo.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTest extends BaseTest {
    public ApiTest(){}

    @Test(testName = "Api Tests Chrome")
    public void testGet(){

        given()
                .when().get("https://jsonplaceholder.typicode.com/posts/1")
                .then().assertThat()
                .statusCode(200);


/*
        RequestSpecification request = RestAssured.given();
        Response response = request.get("https://jsonplaceholder.typicode.com/posts/1");
        ResponseBody responseBody = response.body();

        int statusCode = response.statusCode();

        JsonPath jsonPath = responseBody.jsonPath();
        int userId = jsonPath.getInt("userId");
        int id = jsonPath.getInt("id");
        String title = jsonPath.get("title");
        String body = jsonPath.get("body");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(userId, 1);
        Assert.assertEquals(id, 1);
        Assert.assertEquals(title, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        Assert.assertEquals(body, "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto");
*/

    }
}

