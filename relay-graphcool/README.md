# Sri Relay Web Template

![Demo](demo.gif)

### GraphCool 
 
 * Create a graph.cool account : https://www.graph.cool/
 
 * Got to console nd create a new project ( name : Instagram)
 
 * In Schema Editor add following type.
   
   
   ```scala
type Post  {
  description: String!
  imageUrl: String!
}
```
 now it will ask for preview changes click on it and then approve changes.
 
 *  Go to PlayGround - > select API to Relay and then run the following mutation 
 
 ```js
mutation superCar {
  createPost(input:{description:"Super Car",imageUrl:"http://www.wonderslist.com/wp-content/uploads/2015/04/Bugatti-Veyron-Fastest-Supercars.jpg",clientMutationId:""}) {
    post {
      id
      imageUrl
      description
    }
  }
}
```

*  Click on ENDPOINTS and get the Relay API URL 

*  Store Relay URL in environment variable `SRI_APP_RELAY_URL`
  Example : for mac `open ~/.bash_profile` and then add `export SRI_APP_RELAY_URL=YOUR_RELAY_URL` and make sure you reload current shell session using `. ~/.bash_profile`


## How to Run 


***One Time Tasks*** 

```scala
npm install -g get-graphql-schema
npm install -g relay-compiler
npm install -g graphql-sjs-models
```


***Start Dev..***

```scala

npm run getOrUpdateSchema // make sure you run this again whenever you update your schema in future.

sbt ~web:dev //DEV
   or
sbt ~web:prod //Prod   

//open new terminal

yarn install or npm install

npm start

//open http://localhost:8090/ in browser

```

