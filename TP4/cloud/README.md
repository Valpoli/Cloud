##  Commande permettant de construire l'image docker

* docker build -t telematics ../cloud/

## Commande permettant de lancer le serveur REST

* ./mvnw spring-boot:run

## Adresse utilisée par Nifi pour envoyer ses données au service REST

* `http://springboot:8081/telematics`

## TP5 : Persistance avec JPA et spring-boot

* getAllVehicleTypes: This method retrieves and returns all vehicle types present in the database.
Query example : `http://localhost:8081/allVehicleTypes`
Response example : [{"id":1,"name":"T.E.R","capacity":342,"manufacturer":"Bombardier & Alstom","companyName":{"id":1,"name":"SNCF"}},{"id":2,"name":"Tramway","capacity":300,"manufacturer":"Alstom","companyName":{"id":2,"name":"TISSEO"}},{"id":3,"name":"Metro","capacity":224,"manufacturer":"Alstom","companyName":{"id":2,"name":"TISSEO"}},{"id":4,"name":"Bus standards","capacity":63,"manufacturer":"Heuliez","companyName":{"id":2,"name":"TISSEO"}},{"id":5,"name":"Bus articulés ","capacity":140,"manufacturer":"EvoBus","companyName":{"id":2,"name":"TISSEO"}},{"id":6,"name":"Mini Bus","capacity":20,"manufacturer":"Heuliez","companyName":{"id":2,"name":"TISSEO"}},{"id":7,"name":"Téléphérique","capacity":34,"manufacturer":"Poma","companyName":{"id":2,"name":"TISSEO"}},{"id":8,"name":"Independant","capacity":6,"manufacturer":"","companyName":{"id":3,"name":"Taxis"}}]

* getCompanyByVin: This method searches for a company based on a given vehicle's VIN and returns the name of the company associated with that VIN.
Query example : `http://localhost:8081/CompanyByVin`
Body : VF7VDA55662202434
Response example : TISSEO

* getNbVehicleType: This method counts the number of vehicle types that match a given vehicle type name and returns this count.
Query example : `http://localhost:8081/NbVehicleType`
Body : Tramway
Response example : 7

* putVehicleType: This method updates the details of an existing vehicle type based on the vehicle type ID, vehicle type name, and passenger number.
Query example : `http://localhost:8081/putVT?VtID=1&vehicleTypeName=test&passengerNumber=5`
Response example : {"id":1,"name":"test","capacity":5,"manufacturer":"Bombardier & Alstom","companyName":{"id":1,"name":"SNCF"}}

* postVT: This method creates a new vehicle type based on the provided information such as name, capacity, manufacturer, and company ID.
Query example : `http://localhost:8081/postVT`
Body : 
{
"name": "Test1",
"capacity": 5,
"manufacturer": "Manufacturer",
"companyId": 1
}
Response example : {"name":"Test1","capacity":5,"manufacturer":"Manufacturer","companyId":1}

* getAllCompany: This method retrieves and returns all companies present in the database.
Query example : `http://localhost:8081/allCompany`
Response example : [{"id":1,"name":"SNCF"},{"id":2,"name":"TISSEO"},{"id":3,"name":"Taxis"}]

* getCompanyByID: This method retrieves a company by its ID and returns its details.
Query example : `http://localhost:8081/companyByID`
Body : 1
Response example : {"id":1,"name":"SNCF"}

* putCompany: This method updates the name of an existing company based on its ID.
Query example : `http://localhost:8081/putCompany?cID=1&companyName=test`
Response example : {"id":1,"name":"test"}

* postCompany: This method creates a new company with the provided name.
Query example : `http://localhost:8081/postCompany?companyName=test2`
Response example : test2

* deleteCompany: This method deletes a company and all associated entities (vehicle types and vehicles) based on the company's ID.
Query example : `http://localhost:8081/deleteCompany?cID=1`
Response example : {"id":1,"name":"test"}