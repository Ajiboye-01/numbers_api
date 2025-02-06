**Number Classifier API**

**Overview**

This is a API that takes a number as a query parameter and returns its mathematical properties along with a fun fact.

**Features**

Checks if a number is prime

Checks if a number is perfect

Determines if the number is an Armstrong number

Identifies if the number is odd or even

Calculates the sum of its digits

Fetches a fun fact about the number from the Numbers API

Handles errors for invalid inputs

CORS enabled for public access

**API Endpoint**

GET /api/classify-number

**Request:**

GET <your-domain>/api/classify-number?number=371

Response (200 OK):

{
    "number": 371,
    "is_prime": false,
    "is_perfect": false,
    "properties": ["armstrong", "odd"],
    "digit_sum": 11,
    "fun_fact": "371 is an Armstrong number because 3^3 + 7^3 + 1^3 = 371"
}

Response (400 Bad Request for invalid input):

{
    "number": "alphabet",
    "error": true
}

**How to Run Locally**

Clone the repository

Navigate to the project directory

Build and run the application:

./mvnw spring-boot:run

The API will be available at:

http://localhost:8080/api/classify-number?number=371
