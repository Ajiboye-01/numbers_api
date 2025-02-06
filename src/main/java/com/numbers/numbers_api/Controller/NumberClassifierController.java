package com.numbers.numbers_api.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*") // Allows CORS for all origins
public class NumberClassifierController {

    @GetMapping("")
    public ResponseEntity<?> classifyNumber(@RequestParam String number) {
        try {
            int num = Integer.parseInt(number);
            Map<String, Object> response = new HashMap<>();
            response.put("number", num);
            response.put("is_prime", isPrime(num));
            response.put("is_perfect", isPerfect(num));
            response.put("properties", getProperties(num));
            response.put("digit_sum", getDigitSum(num));
            response.put("fun_fact", getFunFact(num));

            return ResponseEntity.ok(response);
        } catch (NumberFormatException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("number", number);
            errorResponse.put("error", true);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private boolean isPerfect(int num) {
        int sum = 1;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) sum += i;
        }
        return sum == num;
    }

    private List<String> getProperties(int num) {
        List<String> properties = new ArrayList<>();
        if (isArmstrong(num)) properties.add("armstrong");
        properties.add(num % 2 == 0 ? "even" : "odd");
        return properties;
    }

    private boolean isArmstrong(int num) {
        int sum = 0, temp = num, digits = String.valueOf(num).length();
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, digits);
            temp /= 10;
        }
        return sum == num;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    private String getFunFact(int num) {
        String apiUrl = "http://numbersapi.com/" + num + "/math";
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(apiUrl, String.class);
        } catch (Exception e) {
            return "Fun fact not available";
        }
    }
}
