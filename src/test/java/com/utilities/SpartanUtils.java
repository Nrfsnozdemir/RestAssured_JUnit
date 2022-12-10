package com.utilities;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtils {

    public static Map<String,Object> randomPostRequest(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String gender = faker.demographic().sex();
        String phone = faker.numerify("##########");
        long phoneNumber = Long.parseLong(phone);
        Map<String, Object> randomJsonMap = new LinkedHashMap<>();
        randomJsonMap.put("name",firstName);
        randomJsonMap.put("gender",gender);
        randomJsonMap.put("phone",phoneNumber);
        return randomJsonMap;
    }
}
