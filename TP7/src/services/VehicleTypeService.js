import axios from 'axios';
const apiUrl = 'http://localhost:8081/';

class vehicleTypeService {

    getAllVehicleTypes(){
        try {
            return axios.get(apiUrl + "allVehicleTypes");
        }
        catch (error) {
            console.error('Error fetching all vehicle types:', error);
        }
    };

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

    async addNbvehiclesByTypes(vehicleTypes) {
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
    };

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
    };

    putVehicleType (vtID, vehicleTypeName){
        try {
          return axios.put(apiUrl + "putVT", {
            vtID: vtID,
            vehicleTypeName: vehicleTypeName
        });
        } catch (error) {
          console.error('Error updating vehicle type:', error);
        }
    };

    postVT(newVehicleType){
        try {
            if (newVehicleType.capacity < 0)
            {
                newVehicleType.capacity = 1;
            }
          return axios.post(apiUrl + "postVT", {
            name : newVehicleType.name,
            capacity : newVehicleType.capacity,
            manufacturer : newVehicleType.manufacturer,
            companyId : newVehicleType.companyId
        });
        } catch (error) {
          console.error('Error creating vehicle type:', error);
        }
    };
}
export default new vehicleTypeService();
