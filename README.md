# Jolly Rancher Banking Application
## At Jolly Rancher Bank, we make banking silly and hilarious!
- There are API routes that allow end-users to:
    - Create a new bank account for a customer, with an initial deposit amount. A
      single customer may have multiple bank accounts.
    - Transfer amounts between any two accounts, including those owned by
      different customers.
    - Retrieve transfer history for a given account.
- Throw proper HTTP response codes from each REST service
- Write tests for your business logic

The application is written in Java and uses the SpringBoot framework.

### API
1. Create a new bank account for a customer, with an initial deposit amount. A
      single customer may have multiple bank accounts.  Note this is also how you create a new customer.
        It is planned to expand this api, but for now all updates to a customer 
        will be done through the account creation api.
    - POST /account
    - Request Body Example: 
   ```json
   {
      "id": 5,
      "customer": {
      "id": 2,
      "firstName": "Leopoldina",
      "lastName": "Toast"
      },
      "balance": 123.45
      }
    - Response: 200
    - Response Body: Account created
2. Transfer amounts between any two accounts, including those owned by
      different customers.
   - POST /transfer/{fromAccountId}/{toAccountId}/{amount}
   - Request Body: none
   - Response: 200
   - Response Body Example:
   ```json
   {
     "id": 1,
     "sourceAccount": 1,
     "sourceBeginningBalance": 1234.0,
     "sourceEndingBalance": 1211.0,
     "targetAccount": 2,
     "targetBeginningBalance": 300.0,
     "targetEndingBalance": 323.0
     }
3. Retrieve transfer history for a given account.
    - GET /reports/transferHistoryByAccount/{sourceAccount}
    - Request Body example for account 1:
    ```json
    [
    {
        "id": 1,
        "sourceAccount": 1,
        "sourceBeginningBalance": 1096.0,
        "sourceEndingBalance": 1073.0,
        "targetAccount": 2,
        "targetBeginningBalance": 438.0,
        "targetEndingBalance": 461.0
    }
   ]



### How to setup testing the transactional behavior on a locally installed mongodb server.
##### (note:  this can be done once and the local database will be setup through recycling of the database) 
1. Install mongodb on your local machine.
2. Install mongosh on your local machine.
3. alter mondodb mongod.cfg to have a replica set named.
```javascript
replication:
  replSetName: testReplicationSet
```
4. restart mongodb service in windows services.
5. run the following command in mongosh to create a replica set.
```javascript
 rs.initiate({ 
            _id: "testReplicationSet", 
            members: [ 
                { _id: 0, host: "localhost:27017" } 
            ] 
        }); 
```
6. The above command should return something like the following:
```javascript
{
        "ok" : 1,
        "$clusterTime" : {
                "clusterTime" : Timestamp(1633660001, 1),
                "signature" : {
                        "hash" : BinData(0,"AAAAAAAAAAAAAAAAAAAAAAAAAAA="),
                        "keyId" : NumberLong(0)
                }
        },
        "operationTime" : Timestamp(1633660001, 1)
}
```