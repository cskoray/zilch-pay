# Swagger Hub URL
https://app.swaggerhub.com/organizations/CSKORAY_1/projects/zilch-project

# Open API URL
http://localhost:8085/swagger-ui/index.html

# K8s folder has all the yaml files for the deployment
### api-zilch-pay-deployment.yaml

### Zilch API

Add new Zilch user/customer

localhost:8085/v1/api/zilch/users/register

```
{
"name": "horay",
"email": "ga@g.com",
"cardNumber": "1111222233334444",
"expiryDate": "0525",
"cvv": "275"
}
```
response has new Zilch Card
```
{
"paymentToken": "6165e18a-81d8-4c47-8358-d864ab978fe6",
"cardNumber": "5169824574275218",
"expiryDate": "0428",
"cvv": "537",
"creditLimit": 100
}
```

Add new Zilch brand

localhost:8085/v1/api/zilch/brands/register
```
{
  "name": "Nike"
}
```
Reponse
```
{
  "status": "success"
}
```

Zilch pay for an order

localhost:8085/v1/api/zilch/payment/pay

```
{
  "paymentToken": "6165e18a-81d8-4c47-8358-d864ab978fe6",
  "paymentType": "ONE",
  "merchantName": "Shopping Mall",
  "productKeys":[
    {
        "productKey":"2bdbe2a8-d81d-48fe-a67a-335cd543b46d",
        "productKey":"cd3fed50-66b0-4ba4-a1f3-67a7ed6966ce"
    }
  ]
}
```
response has new Zilch Cashback added to User credit Limit
```
{
    "status": "success",
    "cashback": 14.995,
    "apr": "0%"
}
```