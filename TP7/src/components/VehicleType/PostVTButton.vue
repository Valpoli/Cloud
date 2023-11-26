<template>
  <div>
    <button v-if="!creating" @click="startCreating" class="button">Create</button>

    <div v-if="creating">
      <input v-model="name" placeholder="Name" />
      <input v-model="capacity" placeholder="Capacity" type="number" />
      <input v-model="manufacturer" placeholder="Manufacturer" />
      <input v-model="companyId" placeholder="Company ID" type="number" />
      <button @click="createVehicleType" class="button">Confirm</button>
    </div>
    <span v-if="errorMessage">{{ errorMessage }}</span>
  </div>
</template>

<script>
import VehicleTypeService from "../../services/VehicleTypeService.js";
import CompanyService from "../../services/CompanyService.js";

export default {
  props: {
    nbByvehicleType: Array
  },
  data() {
    return {
      creating: false, // Controls the display of the form
      name: '',
      capacity: '',
      manufacturer: '',
      companyId: '',
      errorMessage: ''
    };
  },

  methods: {
    startCreating() {
      this.creating = true;
      this.errorMessage = '';
    },

    async createVehicleType() {
      try {
        const companies = await CompanyService.getAllCompanies();
        const companyExists = companies.data.some(company => company.id === this.companyId);

        if (!companyExists) {
          this.errorMessage = "This company ID doesn't exist.";
        }
        else
        {
          const newVehicleType = {
            name: this.name,
            capacity: this.capacity,
            manufacturer: this.manufacturer,
            companyId: this.companyId
          };
          
          await VehicleTypeService.postVT(newVehicleType);

          this.nbByvehicleType.push(0);

          // Clear the fields after creation
          this.name = '';
          this.capacity = '';
          this.manufacturer = '';
          this.companyId = '';
      }
      // Hide the form
      this.creating = false;
      } catch (error) {
        console.error("Error creating vehicle type:", error);
      }
    }
  }
}
</script>
