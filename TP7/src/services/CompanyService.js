import axios from 'axios';
const apiUrl = 'http://localhost:8081/';


class CompanyService {

    getCompanyByVin (vin){
        try {
          const response = axios.get(apiUrl + "CompanyByVin", { data: vin });
          return response.data;
        } catch (error) {
          console.error('Error fetching company by VIN:', error);
        }
    };

    getAllCompanies(){
        try {
            const response = axios.get(apiUrl + 'allCompany');
            return response.data;
        } catch (error) {
            console.error('Error fetching all companies:', error);
        }
    };

    getCompanyById(companyId){
        try {
            const response = axios.get(apiUrl + 'companyByID', { data: companyId });
            return response.data;
        } catch (error) {
            console.error('Error fetching company by ID:', error);
        }
    };

    putCompany(cID, companyName){
        try {
            const response = axios.put(apiUrl + 'putCompany', null, {
                params: {
                    cID,
                    companyName
                }
            });
            return response.data;
        } catch (error) {
            console.error('Error updating company:', error);
        }
    };

    postCompany(companyName){
        try {
            const response = axios.post(apiUrl + 'postCompany', null, { params: { companyName: companyName } });
            return response.data;
        } catch (error) {
            console.error('Error creating a new company:', error);
        }
    };

    deleteCompany(cID){
        try {
            const response = axios.delete(apiUrl + 'deleteCompany', {
                params: {
                    cID
                }
            });
            return response.data;
        } catch (error) {
            console.error('Error deleting company:', error);
        }
    }

}
export default new CompanyService();