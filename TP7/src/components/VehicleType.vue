<script setup>
import { ref, onMounted } from 'vue';
import VehicleTypeService from "../services/VehicleTypeService.js";

const allVT = ref("Unable to connect");

const searchVehicleType = () => {
  VehicleTypeService.getAllVehicleTypes()
  .then((response) => {
    allVT.value = response.data;
    console.log(response);
  }).catch(error => {
    console.error(error);
  });
};

onMounted(() => {
  searchVehicleType();
});
</script>

<template>
  <div>
    <p>coucou</p>
    <p>{{ allVT }}</p>
  </div>
</template>
