# Quarkus Core

This project uses Quarkus, the Supersonic Subatomic Java Framework.

## Tools

* Java
* Quarkus
* RestEasy (JAX-RS)
* JUnit
* Rest Assured
* Docker


## Running the application

You can run your application in dev mode that enables live coding using:
```shell script
mvn compile quarkus:dev
```

You can run your application in test mode:
```shell script
mvn test
```

You can run your application in a custom mode/profile:
```shell script
mvn quarkus:dev -Dquarkus.profile=<nameOfProfile>
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.
## Packaging and running the application

The application can be packaged using:
```shell script
mvn package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
mvn package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:
```shell script
mvn package -Pnative
```
You can run tests using the native executable using:
```shell script
mvn verify -Pnative
```
Or, if you don't have GraalVM installed, you can run the native executable build in a container using:
```shell script
mvn package -Pnative -Dquarkus.native.container-build=true
```
You can then execute your native executable with: `./target/rest-book-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Creating a Docker container

Add Docker extension using:
```shell script
mvn quarkus:add-extension -Dextensions="container-image-docker"
```
A Docker image using fast-jar:
```shell script
mvn package -Dquarkus.container-image.build=true -Dquarkus.package.type=jar -Dquarkus.container-image.tag=jvm
```

A Docker image using native binary file:
```shell script
mvn package -Dquarkus.container-image.build=true -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.tag=native
```
the -Dquarkus.native.container-build=true is only required if you are using a non-linux computer

Then run the image using:
```shell script
docker run -i --rm -p 8080:8080 louai/rest-book:native
```

## Related Guides

- RESTEasy Classic JSON-B ([guide](https://quarkus.io/guides/rest-json)): JSON-B serialization support for RESTEasy Classic

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

# Keycloak

## Running a Keycloak instance
Getting a Keycloak server running in a container could be done with the following command:

```shell script
docker run --name keycloak -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -p 8080:8080  quay.io/keycloak/keycloak:20.0.1 start-dev
```
> **Note:** Keycloak should usually be accompanied by a database and HTTPS certification for production. This docker container is just an easy way to quickly open a Keycloak instance to test and explore.

Access Keycloak through the localhost and login using the username and password that were set in the docker command ( admin & admin in this case ). Then create a new realm ( a space where users can login ), separate from the master realm ( the realm with the administrator account where other realms are configured ). After creating a new realm, create a new client and a new user inside the new realm. 

## API endpoints

After creating a realm, client, and user. Create a POST request, with the body variables:
* username
* password
* grant_type
* client_id

to the following endpoint:
http://localhost:8080/realms/{YOUR-REALM-NAME}/protocol/openid-connect/token
to **log in** and get back the bearer token for the user you provided the credentials of. 


## Bearer Token
Use the bearer token as an access token for authentication of any API calls that will be made

The following is an example of a request made to the Keycloak API:
**GET**  http://localhost:8080/admin/realms/{YOUR-REALM-NAME}/users   -  This will return the data of all the users in the specified realm

For all the possible Keycloak API calls, check the following link: https://www.keycloak.org/docs-api/20.0.1/rest-api/index.html#_overview

## Quarkus + Keycloak

https://quarkus.io/guides/security-keycloak-authorization

After configuration, quarkus will spin up a keycloak container when you run it

### Endpoints taken from my quarkus realm:

* issuer	"http://localhost:49158/realms/quarkus"
* authorization_endpoint	"http://localhost:49158/realms/quarkus/protocol/openid-connect/auth"
* token_endpoint	"http://localhost:49158/realms/quarkus/protocol/openid-connect/token"
* introspection_endpoint	"http://localhost:49158/realms/quarkus/protocol/openid-connect/token/introspect"
* userinfo_endpoint	"http://localhost:49158/realms/quarkus/protocol/openid-connect/userinfo"
* end_session_endpoint	"http://localhost:49158/realms/quarkus/protocol/openid-connect/logout"

