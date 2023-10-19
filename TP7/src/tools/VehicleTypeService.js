import axios from 'axios';
const allVehicleTypesURL = 'http://localhost:8081/allVehicleTypes';
const companyByVinURL = 'http://localhost:8081/CompanyByVin';
const nbVehicleTypeURL = 'http://localhost:8081/NbVehicleType';
const putVTURL = 'http://localhost:8081/putVT?VtID=1&vehicleTypeName=test&passengerNumber=5';
const postVTURL = 'http://localhost:8081/postVT';
const allCompanyURL = 'http://localhost:8081/allCompany';
const companyByIDURL = 'http://localhost:8081/companyByID';
const putCompanyURL = 'http://localhost:8081/putCompany?cID=1&companyName=test';
const postCompanyURL = 'http://localhost:8081/postCompany?companyName=test2';
const deleteCompanyURL = 'http://localhost:8081/deleteCompany?cID=1';






class VehicleTypeService {
getAuthors(title){
return axios.get(apiUrl,{params: {'title':title}});
}
}
export default new AuthorService();