<template>
    <div>
      <button v-if="!editing" @click="editing = true">Modify</button>
      
      <div v-if="editing">
        <input v-model="vehicleTypeName" placeholder="Vehicle Type Name" />
        <button @click="confirmChanges">Confirmer</button>
      </div>
    </div>
  </template>
  
  <script>
  import VehicleTypeService from "../../services/VehicleTypeService.js";
  
  export default {
    props: ['id'],
    
    data() {
      return {
        editing: false,
        vehicleTypeName: '',
      };
    },
    methods: {
        startEditing() {
            this.editing = true;
        },

        async confirmChanges() {
          try {
            await VehicleTypeService.putVehicleType(this.id,this.vehicleTypeName);
            // Vider les champs
            this.vehicleTypeName = '';

            // Faire disparaître les champs et le bouton "Confirmer"
            this.editing = false;
          } catch (error) {
            console.error("Error updating vehicle type:", error);
          }
        }
    }
  
  }
  </script>