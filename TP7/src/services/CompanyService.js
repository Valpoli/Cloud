import axios from 'axios';
const apiUrl = 'http://localhost:8081/';


class CompanyService {

    // getCompanyByVin (vin){
    //     try {
    //       const response = axios.get(apiUrl + "CompanyByVin", { data: vin });
    //       return response.data;
    //     } catch (error) {
    //       console.error('Error fetching company by VIN:', error);
    //     }
    // };

    getAllCompanies(){
        try {
            return axios.get(apiUrl + 'allCompany');
        } catch (error) {
            console.error('Error fetching all companies:', error);
        }
    };

    // getCompanyById(companyId){
    //     try {
    //         const response = axios.get(apiUrl + 'companyByID', { data: companyId });
    //         return response.data;
    //     } catch (error) {
    //         console.error('Error fetching company by ID:', error);
    //     }
    // };

    putCompany(cID, companyName){
        try {
            console.log(cID)
            console.log(companyName)
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