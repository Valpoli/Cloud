<template>
  <div class="create-form">
    <button class="create-btn" v-if="!creating" @click="startCreating">Create</button>

    <div v-if="creating" class="input-group">
      <input class="input-field" v-model="name" placeholder="Name" />
      <input class="input-field" v-model="capacity" placeholder="Capacity" type="number" />
      <input class="input-field" v-model="manufacturer" placeholder="Manufacturer" />
      <input class="input-field" v-model="companyId" placeholder="Company ID" type="number" />
      <button class="confirm-btn" @click="createVehicleType">Confirm</button>
    </div>
  </div>
</template>

<script>
import VehicleTypeService from "../../services/VehicleTypeService.js";

export default {
  props: {
    nbByVehiculeType: Array
  },
  data() {
    return {
      creating: false, // Controls the display of the form
      name: '',
      capacity: '',
      manufacturer: '',
      companyId: ''
    };
  },

  methods: {
    startCreating() {
      this.creating = true;
    },

    async createVehicleType() {
      try {
        const newVehicleType = {
          name: this.name,
          capacity: this.capacity,
          manufacturer: this.manufacturer,
          companyId: this.companyId
        };
        
        await VehicleTypeService.postVT(newVehicleType);

        this.nbByVehiculeType.push(0);

        // Clear the fields after creation
        this.name = '';
        this.capacity = '';
        this.manufacturer = '';
        this.companyId = '';

        // Hide the form
        this.creating = false;

      } catch (error) {
        console.error("Error creating vehicle type:", error);
      }
    }
  }
}
</script>

<style scoped>
.create-form {
  text-align: center;
  margin-top: 1em;
}

.input-group {
  margin-bottom: 1em;
}

.input-field {
  margin: 0.5em;
  padding: 0.5em;
  border: 1px solid #6ea4b5;
  border-radius: 4px;
}

.create-btn, .confirm-btn {
  background-color: #2a4a5b; /* Darker ocean blue as default */
  color: #e0f7fa; /* Soft cyan text */
  border: none;
  padding: 0.5em 1em;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.create-btn:hover, .confirm-btn:hover {
  background-color: #6ea4b5; /* Lighter ocean blue on hover */
}

.create-btn:active, .confirm-btn:active {
  transform: translateY(2px);
}

/* Adjusted styles to make the buttons look disabled when not 'creating' */
.create-btn:not(:hover):not(:active) {
  background-color: #3a5f70; /* Even lighter for disabled state */
  cursor: not-allowed;
}

.confirm-btn:not(:hover):not(:active) {
  background-color: #3a5f70; /* Same as above for consistency */
  cursor: not-allowed;
}

</style>
