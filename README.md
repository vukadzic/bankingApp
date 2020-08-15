# Documentation for assignment 

Documentation contains informations about services:

 - [Creating bank](#Creating-bank)

 - [Updating bank info](#Updating-bank-info)
 
 - [Deactivating bank](#Deactivating-bank)

 - [Creating user](#Creating-user)

 - [Creating bank account](#Creating-bank-account)
 
 - [User transaction](#User-transaction)
 

# Creating bank

Service for creating new bank in database

**URL** : `/admin/bank`

**Method** : `POST`

**Auth required** : NO

**Permissions required** : None

**Data constraints**

```json
{
    "name": "[1 to 100 chars]",
    "address": "[1 to 100 chars]"
}
```

## Success Response

**Code** : `201 CREATED`

**Input Content examples**

For a Bank with name "Erste Banka" and address "Cetinjski put br. 3"

```json
{
    "name": "Erste Banka",
    "address": "Cetinjski put br. 3"
}
``` 

**Response Content examples**

Service returns created object.

```json
{
    "id": 2,
    "name": "Erste Banka",
    "address": "Cetinjski put br. 3",
    "bankAccounts": [],
    "active": true"
}
```


## Fail Response

**Code** : `400 BAD_REQUEST`

## Notes

* Bank ID will be generated by DB, and is_active field will be set to true by default
when creating a bank. Data of bank which we want to create is
sent in json format in http body.

# Updating bank info

Service for updating existing bank info.

**URL** : `/admin/bank`

**Method** : `PATCH`

**Auth required** : NO

**Permissions required** : None

**Data constraints**

```json
{
    "id": [Integer value],
    "name": "[1 to 100 chars]",
    "address": "[1 to 100 chars]"
}
```

## Success Response

**Code** : `200 OK`

**Content examples**

For a Bank with id=1.

```json
{
    "id": 1,
    "name": "Erste Banka - updated",
    "address": "Cetinjski put br. 3 - updated"
}
```

or

```json
{
    "id": 1,
    "address": "Cetinjski put br. 3 - updated"
}
```

## Fail Response

**Code** : `400 BAD_REQUEST`

## Notes

* This service support partial or complete update of bank info, id is mandatory and other info is optional.Data of bank which we want to update is
sent in json format in http body.

# Deactivating bank

Service for deactivating a bank (setting is_acive field to false)

**URL** : `/admin/bank/deactivate/{id}`

**Method** : `PUT`

**Auth required** : NO

**Permissions required** : None

**Path variable** : `id:[Integer value]`

## Success Response

**Code** : `200 OK`

## Fail Response

**Code** : `404 NOT_FOUND`

## Notes

* This service deactivates the bank using bank id provided as path variable in url.

# Creating user

Service for creating new user in database

**URL** : `/admin/create-user`

**Method** : `POST`

**Auth required** : NO

**Permissions required** : None

**Data constraints**

```json
{
    "fullName":"[1 to 60 chars]",
    "jmbg":"[13 char, can only contain numbers(0-9)]",
    "password":"[1 to 45 char]",
    "address":"[1 to 100 char]",
    "birthDate":"YYYY-MM-DD",
    "email":"[max 100 char]",
    "phoneNumber": "[max 30 char]"
}
```

## Success Response

**Code** : `201 CREATED`

**Input Content examples**

```json
{
    "fullName":"Vuk Adzic",
    "jmbg":"1907997666666",
    "password":"123456",
    "address":"Zagoric Ulica 13, br 5",
    "birthDate":"1997-07-19",
    "email":"adzic820@gmail.com",
    "phoneNumber": "068006780"
}
```

**Response Content examples**

Service returns created object.

```json
{
    "id": 6,
    "fullName": "Vuk Adzic",
    "jmbg": "1907997666666",
    "password": "123456",
    "address": "Zagoric Ulica 13, br 5",
    "birthDate": "1997-07-19T00:00:00.000+00:00",
    "email": "adzic820@gmail.com",
    "phoneNumber": "067006780",
    "createdAt": "2020-08-14T11:52:10.272+00:00",
    "bankAccounts": []
}
```


## Fail Response

**Code** : `400 BAD_REQUEST`

## Notes

* Field createdAt is filled automatically by db with current timestamp, and user entity also contains list od bank accounts of user. Data of user which we want to create is
sent in json format in http body.

# Creating bank account

Service for creating new bank account in database. One user can have multiple bank accounts and one account can belong only to one user.

**URL** : `/admin/create-account?jmbg=?&bankId=?`

**Method** : `POST`

**Auth required** : NO

**Permissions required** : None

**Data constraints (query params)**

jmbg:[13 numbers],
bankId: Integer value

**Data constraints (body)**

```json
{
    "accountNumber":"[1 to 45 char]",
    "accountType":"[1 to 45 char]",
    "currentBalance":Double value (default 0.00)
}
```

## Success Response

**Code** : `201 CREATED`

**Input Content examples**

Query params:

jmbg=1907997666666
bankId=1

JSON body:

```json
{
    "accountNumber":"53000000559880",
    "accountType":"Ziro racun",
    "currentBalance":0.00
}
```

**Response Content examples**

Service returns created object.

```json

{
    "id": 7,
    "accountNumber": "530-000005598-80",
    "accountType": "Tekuci racun",
    "currentBalance": 0.0,
    "bank": {
        "id": 1,
        "name": "Erste banka CG-updated",
        "address": "Cetinjski put br. 3",
        "active": true
    },
    "user": {
        "id": 2,
        "fullName": "Vuk Adzic",
        "jmbg": "1907997666666",
        "password": "123456",
        "address": "Zagoric Ulica 13, br 5",
        "birthDate": "1997-07-18T22:00:00.000+00:00",
        "email": "adzic820@gmail.com",
        "phoneNumber": "6800678",
        "createdAt": "2020-08-13T16:11:55.000+00:00"
    }
}

```


## Fail Response

**Code** : `400 BAD_REQUEST`

## Notes

* In response we have info about the account, id that db automaticlly added and inforamtion about which bank does the account
belong and user who owns the account. When account is created, in query params, the jmbg of user who owns account has to be sent
as well as id of bank that account belongs to.

# User transaction

Service for transfering money from one bank account to other. Data needed for transaction need to be
provided in body of request.

**URL** : `/user/transfer`

**Method** : `PUT`

**Auth required** : NO

**Permissions required** : None

**Data constraints**

```json
{
    "userId":[Integer value],
    "userPassword":"[1 to 45 char]",
    "amount":Double value,
    "fromAccountNumber":"1 to 45 char",
    "toAccountNumber":"1 to 45 char"
}
```

## Success Response

**Code** : `200 OK`

**Input Content examples**

```json
{
    "userId":1,
    "userPassword":"123456",
    "amount":550.00,
    "fromAccountNumber":"530-00024894-80",
    "toAccountNumber":"530-00024891-80"
}
```

## Fail Response

**Code** : `400 BAD_REQUEST`

## Notes

* For money transaction, user has to provide id of user who is making transaction (userId), password that is added when user
is created (userPassword), amount of funds that user wants to transfer (amount),number of account from which user wants to transfer funds (fromAccountNumber) and number of account to which funds
should be transfered (toAccountNumber). Service checks is password correct and does account from which funds are being transfered
belong to user who is making the transfer.