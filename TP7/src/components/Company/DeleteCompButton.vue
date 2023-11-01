
    <template>
    <button @click="deleteCompany">Delete</button>
    </template>

    <script>
    import CompanyService from "../../services/CompanyService.js";
    export default {
    props: {
        id: Number
    },
    methods: {
        async deleteCompany() {
        try {
            await CompanyService.deleteCompany(this.id);
            } catch (error) {
            console.error("Error deleting company:", error);
            }
        }
    }
    }
    </script>
  