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

### How to run the application


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