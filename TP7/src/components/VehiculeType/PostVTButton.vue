<template>
    <div>
      <button v-if="!creating" @click="startCreating">Create</button>
  
      <div v-if="creating">
        <input v-model="name" placeholder="Name" />
        <input v-model="capacity" placeholder="Capacity" type="number" />
        <input v-model="manufacturer" placeholder="Manufacturer" />
        <input v-model="companyId" placeholder="Company ID" type="number" />
        <button @click="createVehicleType">Confirm</button>
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
        creating: false,  // Contrôle l'affichage du formulaire
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
  
          // Vider les champs après la création
          this.name = '';
          this.capacity = '';
          this.manufacturer = '';
          this.companyId = '';
  
          // Faire disparaître le formulaire
          this.creating = false;
  
        } catch (error) {
          console.error("Error creating vehicle type:", error);
        }
      }
    }
  }
  </script>
  