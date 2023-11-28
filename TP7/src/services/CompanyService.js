import axios from 'axios';
const apiUrl = 'http://localhost:8081/';
// const apiUrl = '/telematics/';

class CompanyService {
    
    getAllCompanies(){
        try {
            return axios.get(apiUrl + 'allCompany');
        } catch (error) {
            console.error('Error fetching all companies:', error);
        }
    };

    putCompany(cID, companyName){
        try {
            return axios.put(apiUrl + 'putCompany', {
                id : cID,
                name: companyName
            });
        } catch (error) {
            console.error('Error updating company:', error);
        }
    };

    postCompany(companyName){
        try {
            return axios.post(apiUrl + 'postCompany',
            { name: companyName} );
        } catch (error) {
            console.error('Error creating a new company:', error);
        }
    };

    deleteCompany(cID){
        try {
            return axios.delete(apiUrl + 'deleteCompany?cID=' + String(cID))
        } catch (error) {
            console.error('Error deleting company:', error);
        }
    }

}
export default new CompanyService();