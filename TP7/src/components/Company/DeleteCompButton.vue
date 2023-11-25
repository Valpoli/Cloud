
    <template>
    <button @click="deleteCompany" class="delete-button">Delete</button>
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

<style scoped>
.delete-button {
  background-color: #ff6b6b; /* A strong red to signify deletion */
  color: #ffffff; /* White text for contrast */
  padding: 0.5em 1em;
  border-radius: 0.3em; /* Rounded corners for the button */
  cursor: pointer;
  border: none;
  transition: background-color 0.3s ease; /* Transition for the hover effect */
}

.delete-button:hover {
  background-color: #ff7878; /* A slightly lighter red for hover effect */
}

/* Additional styling can be added if needed */
</style>