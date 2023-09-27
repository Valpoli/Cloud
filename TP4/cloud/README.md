##  Commande permettant de construire l'image docker

* docker build -t telematics ../cloud/

## Commande permettant de lancer le serveur REST

* ./mvnw spring-boot:run

## Adresse utilisée par Nifi pour envoyer ses données au service REST

* `http://springboot:8080/telematics`

## TP5 : Persistance avec JPA et spring-boot

* getAllVehicleTypes: This method retrieves and returns all vehicle types present in the database.
Query example : `http://localhost:8081/allVehicleTypes`

* getCompanyByVin: This method searches for a company based on a given vehicle's VIN and returns the name of the company associated with that VIN.
Query example : `http://localhost:8081/CompanyByVin`
Body : VF7VDA55662202434

* getNbVehicleType: This method counts the number of vehicle types that match a given vehicle type name and returns this count.
Query example : `http://localhost:8081/NbVehicleType`
Body : Tramway

* putVehicleType: This method updates the details of an existing vehicle type based on the vehicle type ID, vehicle type name, and passenger number.
Query example : `http://localhost:8081/putVT?VtID=1&vehicleTypeName=test&passengerNumber=5`

* postVT: This method creates a new vehicle type based on the provided information such as name, capacity, manufacturer, and company ID.
Query example : `http://localhost:8081/postVT`
Body : 
{
"name": "Test1",
"capacity": 5,
"manufacturer": "FabricantDuVehicule",
"companyId": 1
}

* getAllCompany: This method retrieves and returns all companies present in the database.
Query example : `http://localhost:8081/allCompany`

* getCompanyByID: This method retrieves a company by its ID and returns its details.
Query example : `http://localhost:8081/companyByID`
Body : 1

* putCompany: This method updates the name of an existing company based on its ID.
Query example : `http://localhost:8081/putCompany?cID=1&companyName=test`

* postCompany: This method creates a new company with the provided name.
Query example : `http://localhost:8081/postCompany?companyName=test`

* deleteCompany: This method deletes a company and all associated entities (vehicle types and vehicles) based on the company's ID.
Query example : `http://localhost:8081/deleteCompany?cID=1`