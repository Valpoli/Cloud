<script setup>
import { ref, onMounted, onUnmounted, watch } from 'vue';
import VehicleTypeService from "../../services/VehicleTypeService.js";
import CompanyService from "../../services/CompanyService.js";
import TableAndButtons from './TableAndButtons.vue';
import PostVTButton from './PostVTButton.vue';

const allvehicleTypes = ref([]);
const nbByvehicleType = ref([]);
const updateInterval = ref(5000);
const allCompany = ref([]);

const initialisation = async () => {
    try {
        const response = await VehicleTypeService.getAllVehicleTypes();
        const resNbvehicleType = await VehicleTypeService.addNbvehiclesByTypes(response.data)
        nbByvehicleType.value = resNbvehicleType
        allvehicleTypes.value = VehicleTypeService.addNumberInCirculation(response.data,nbByvehicleType.value)
        const resAllCompany = await CompanyService.getAllCompanies()
        allCompany.value = resAllCompany.data
    } catch (error) {
        allvehicleTypes.value = error;
        console.error(error);
    }
  }

const updatePrinting = async () => {
    try {
        const resAllCompany = await CompanyService.getAllCompanies()
        const response = await VehicleTypeService.getAllVehicleTypes();
        if (allCompany.value.length != resAllCompany.data.length)
        {
          const resNbvehicleType = await VehicleTypeService.addNbvehiclesByTypes(response.data)
          nbByvehicleType.value = resNbvehicleType
          allCompany.value = resAllCompany.data
        }
        allvehicleTypes.value = VehicleTypeService.addNumberInCirculation(response.data,nbByvehicleType.value)
    } catch (error) {
        allvehicleTypes.value = error;
        console.error(error);
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
  initialisation();
  intervalId = setInterval(updatePrinting, updateInterval.value);
});

</script>

<template>
  <div>
    <h3 class="fancy-subtitle">Vehicle Type Action</h3>
    <div class="bordered-container">
      Update every:
      <select v-model="updateInterval">
        <option value="1000">1 sec</option>
        <option value="3000">3 sec</option>
        <option value="5000" selected>5 sec</option>
      </select>
    </div>
    <div>
      <TableAndButtons :vehicleTypes="allvehicleTypes" />
    </div>
    <div>
      <PostVTButton :nbByvehicleType="nbByvehicleType" />
    </div>
  </div>
</template>
