<script setup>
import { ref, onMounted } from 'vue';
import VehicleTypeService from "../services/VehicleTypeService.js";

const printing = ref("");
const showGoButton = ref({});

const prepareSearch = (funcName) => {
  for (let key in showGoButton.value) {
    showGoButton.value[key] = false;
  }
  showGoButton.value[funcName] = true;
};

const executeFunction = async (funcName, ...args) => {
  try {
    let response;
    if (funcName === "getAllVehicleTypes") {
      response = await VehicleTypeService.getAllVehicleTypes();
    } else if (funcName === "getNbVehicleType") {
      response = await VehicleTypeService.getNbVehicleType(args[0]);
    } else if (funcName === "putVehicleType") {
      response = await VehicleTypeService.putVehicleType(args[0], args[1], args[2]);
    } else if (funcName === "postVT") {
      response = await VehicleTypeService.postVT(args[0]);
    }
    printing.value = response;
  } catch (error) {
    printing.value = error;
    console.error(error);
  }
  showGoButton.value[funcName] = false;
};

// Init the showGoButton with function names
onMounted(() => {
  showGoButton.value = {
    getAllVehicleTypes: false,
    getNbVehicleType: false,
    putVehicleType: false,
    postVT: false
  };
});
</script>

<template>
  <div style="border: 1px solid black; padding: 20px; margin: 10px;">
    <h3>Vehicule Type Action</h3>
    
    <button @click="prepareSearch('getAllVehicleTypes')">Afficher Vehicle Types</button>
    <div v-if="showGoButton.getAllVehicleTypes" style="margin-bottom: 10px;">
      <button @click="executeFunction('getAllVehicleTypes')">Go</button>
    </div>

    <button @click="prepareSearch('getNbVehicleType')">Nombre de Type de Véhicule</button>
    <div v-if="showGoButton.getNbVehicleType" style="margin-bottom: 10px;">
      <input v-model="vehicleTypeName" placeholder="Vehicle Type Name" />
      <button @click="executeFunction('getNbVehicleType', vehicleTypeName)">Go</button>
    </div>

    <button @click="prepareSearch('putVehicleType')">Mettre à jour le type de véhicule</button>
    <div v-if="showGoButton.putVehicleType" style="margin-bottom: 10px;">
      <input v-model="VtID" placeholder="VtID" />
      <input v-model="vehicleTypeName" placeholder="Vehicle Type Name" />
      <input v-model="passengerNumber" placeholder="Passenger Number" />
      <button @click="executeFunction('putVehicleType', VtID, vehicleTypeName, passengerNumber)">Go</button>
    </div>

    <button @click="prepareSearch('postVT')">Créer un type de véhicule</button>
    <div v-if="showGoButton.postVT" style="margin-bottom: 10px;">
      <input v-model="createVTDTO" placeholder="Create Vehicle Type DTO" />
      <button @click="executeFunction('postVT', createVTDTO)">Go</button>
    </div>

    <div style="margin-top: 20px;">
      <p>{{ printing }}</p>
    </div>
  </div>
</template>
