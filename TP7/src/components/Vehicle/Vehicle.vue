<script setup>
import { ref, onMounted, onUnmounted, watch } from 'vue';
import VehicleService from "../../services/VehicleService.js";
import VehicleTable from './VehicleTable.vue';

const updateInterval = ref(5000);
const allVehicle = ref([]);

const updatePrinting = async () => {
    try {
        const response = await VehicleService.getAllVehicles()
        allVehicle.value = response.data
    } catch (error) {
        allVehicle.value = error;
        allVehicle.error(error);
    }
};

let intervalId;

watch(updateInterval, (newInterval) => {
    clearInterval(intervalId);
    intervalId = setInterval(updatePrinting, newInterval);
});

onUnmounted(() => {
    clearInterval(intervalId);
});

onMounted(() => {
  updatePrinting();
  intervalId = setInterval(updatePrinting, updateInterval.value);
});

</script>

<template>
  <div>
    <h3>Vehicle Actions</h3>
    <div>
      Update every:
      <select v-model="updateInterval">
        <option value="1000">1 sec</option>
        <option value="3000">3 sec</option>
        <option value="5000" selected>5 sec</option>
      </select>
    </div>
    <div>
      <VehicleTable :vehicle="allVehicle" />
    </div>
  </div>
</template>

