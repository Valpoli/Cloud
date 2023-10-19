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
            console.log(vehicleTypeName)
            const response = axios.get(apiUrl + "NbVehicleType",vehicleTypeName, {
                headers: {
                    'Content-Type': 'text/plain'
                }
            });
            console.log(response)
            return response.data;
            } catch (error) {
            console.error('Error fetching number of vehicle type:', error);
        }
    };

    putVehicleType (VtID, vehicleTypeName, passengerNumber){
        try {
          const response = axios.put(apiUrl + "putVT", null, {
            params: {
              VtID,
              vehicleTypeName,
              passengerNumber
            }
          });
          return response.data;
        } catch (error) {
          console.error('Error updating vehicle type:', error);
        }
    };

    postVT (createVTDTO){
        try {
          const response = axios.post(apiUrl + "postVT", createVTDTO);
          return response.data;
        } catch (error) {
          console.error('Error creating vehicle type:', error);
        }
    };
}
export default new VehiculeTypeService();
