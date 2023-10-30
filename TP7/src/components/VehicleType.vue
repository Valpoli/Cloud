<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import VehicleTypeService from "../services/VehicleTypeService.js";
import VehicleTypeTable from './VehicleTypeTable.vue';


const allVehiculeTypes = ref("");


const nbByVehiculeType = ref([]);

const updatePrinting = async () => {
    try {
        const response = await VehicleTypeService.getAllVehicleTypes();
        allVehiculeTypes.value = response.data;
    } catch (error) {
        allVehiculeTypes.value = error;
        console.error(error);
    }
};

let intervalId;

onUnmounted(() => {
    clearInterval(intervalId);
});

onMounted(() => {
  updatePrinting();
  intervalId = setInterval(updatePrinting, 5000);
});

</script>

<template>
  <div style="border: 1px solid black; padding: 20px; margin: 10px;">
    <h3>Vehicule Type Action</h3>
    <div style="margin-top: 20px;">
      <VehicleTypeTable :vehicleTypes=allVehiculeTypes />
    </div>
    <p>{{ allVehiculeTypes }}</p>
  </div>
</template>
