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

    async addNbVehiculesByTypes(vehicleTypes) {
        let res = []
        try {
        for (const vehicleType of vehicleTypes) {
                const response = await this.getNbVehicleType(vehicleType.name);
                if (response && response.data) {
                    res.push(response.data)
                }
            } 
        }
        catch (error) {
                console.error('Error fetching number of vehicle type:', error);
        }
        return res
    }

    addNumberInCirculation(vehicleList, circulationNumbers) {
        circulationNumbers = JSON.parse(JSON.stringify(circulationNumbers));
        if (vehicleList.length !== circulationNumbers.length) {
            console.log(vehicleList.length)
            console.log(circulationNumbers.length)
            for (let i = 0; i < vehicleList.length; i++) {
                vehicleList[i]['number in circulation'] = 0;
            }
            console.error('Les deux listes doivent avoir la même longueur.');
        }
        else
        {
            for (let i = 0; i < vehicleList.length; i++) {
                vehicleList[i]['number in circulation'] = circulationNumbers[i];
            }
        }
    
        return vehicleList;
    }
    


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
