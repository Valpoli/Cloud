
import axios from 'axios';
const apiUrl = 'http://localhost:8081/';

class vehicleService {

    getAllVehicles(){
        try {
            return axios.get(apiUrl + "allVehicles");
        }
        catch (error) {
            console.error('Error fetching all vehicle types:', error);
        }
    };

}
export default new vehicleService();