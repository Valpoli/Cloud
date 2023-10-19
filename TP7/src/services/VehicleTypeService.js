import axios from 'axios';
const apiUrl = 'http://localhost:8081/';


class VehiculeTypeService {
    getAllVehicleTypes(title){
    return axios.get(apiUrl + "allVehicleTypes");
    }
}
export default new VehiculeTypeService();
