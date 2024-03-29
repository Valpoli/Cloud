<script setup>
import { ref, onMounted, onUnmounted, watch, computed } from 'vue';

import VehicleService from "../../services/VehicleService.js";
import VehicleTypeService from "../../services/VehicleTypeService.js";
import VehicleTable from './VehicleTable.vue';

const updateInterval = ref(5000);
const allVehicle = ref([]);
const allvehicleTypes = ref([]);
const changes = ref([]);
const totalPassengers = computed(() => allVehicle.value.reduce((acc, v) => acc + (v.pnb || 0), 0));


const initialisation = async () => {
    try {
        const response = await VehicleTypeService.getAllVehicleTypes();
        allvehicleTypes.value = response.data
    } catch (error) {
        console.log(error);
    }
  }

const updatePrinting = async () => {
    try {
        const response = await VehicleService.getAllVehicles();
        const newChanges = detectChanges(allVehicle.value, response.data);
        if (newChanges.length > 0) {
            changes.value = newChanges;
        }
        allVehicle.value = response.data;
    } catch (error) {
        allVehicle.value = error;
        allVehicle.error(error);
    }
};

const detectChanges = (oldData, newData) => {
    const changes = [];

    newData.forEach(newItem => {
        const oldItem = oldData.find(o => o.vin === newItem.vin);
        if (oldItem && oldItem.pnb !== newItem.pnb) {
            changes.push({ vin: newItem.vin, pnb: newItem.pnb });
        }
    });
    return changes;
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
  initialisation();
  updatePrinting();
  intervalId = setInterval(updatePrinting, updateInterval.value);
});

</script>

<template>
  <div>
    <h2 class="fancy-subtitle">Vehicles</h2>
    <div class="bordered-container padding-vertical">
      Update every:
      <select v-model="updateInterval">
        <option value="1000">1 sec</option>
        <option value="3000">3 sec</option>
        <option value="5000" selected>5 sec</option>
      </select>
    </div>
    <div class="flex-container">
    <VehicleTable :vehicle="allVehicle" :vehicleTypes="allvehicleTypes" />
    <div class="padding-left">
    <h3 class="padding-passenger fancy-element">Total Passengers: {{ totalPassengers }}</h3>
    <div v-if="changes.length > 0">
      <h4 class="fancy-element"> Latest trams sended</h4>
      <ul>
        <li v-for="change in changes" :key="change.vin">
          VIN: {{ change.vin }}, Passengers: {{ change.pnb }}
        </li>
      </ul>
    </div>
  </div>
  </div>
  </div>
</template>

