import axios from 'axios';
const apiUrl = 'http://localhost:8081/';


class VehiculeTypeService {

    getAllVehicleTypes(){
        try {
            return axios.get(apiUrl + "allVehicleTypes");
        }
        catch (error) {
            console.error('Error fetching all vehicle types:', error);
        }
    }

    getNbVehicleType (vehicleTypeName){
        try {        
            return axios.get(apiUrl + "NbVehicleType",{
            params: {
              vehicleTypeName: vehicleTypeName
            },
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            } catch (error) {
            console.error('Error fetching number of vehicle type:', error);
        }
    };


    putVehicleType (vtID, vehicleTypeName, passengerNumber){
        try {
          vtID = 1,
          vehicleTypeName = "test",
          passengerNumber = 5
          return axios.put(apiUrl + "putVT", {
            vtID: vtID,
            vehicleTypeName: vehicleTypeName,
            passengerNumber: passengerNumber
        });
        } catch (error) {
          console.error('Error updating vehicle type:', error);
        }
    };

    postVT (name, capacity, manufacturer, companyId){
        try {

          return axios.post(apiUrl + "postVT", {
            name : "test3",
            capacity : 77,
            manufacturer : "Manufacturer",
            companyId : 1
        });
        } catch (error) {
          console.error('Error creating vehicle type:', error);
        }
    };
}
export default new VehiculeTypeService();
