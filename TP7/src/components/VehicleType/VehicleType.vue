<script setup>
import { ref, onMounted, onUnmounted, watch } from 'vue';
import VehicleTypeService from "../../services/VehicleTypeService.js";
import CompanyService from "../../services/CompanyService.js";
// import VehicleTypeTable from './VehicleTypeTable.vue';
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
          console.log(allCompany.value.length)
          console.log(resAllCompany.data.length)
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
  <div class="vehicle-type-container">
    <h3 class="title">Vehicle Type Action</h3>
    <div class="update-interval">
      Update every:
      <select class="interval-selector" v-model="updateInterval">
        <option value="1000">1 sec</option>
        <option value="3000">3 sec</option>
        <option value="5000" selected>5 sec</option>
      </select>
    </div>
    <div class="table-and-buttons">
      <TableAndButtons :vehicleTypes="allvehicleTypes" />
    </div>
    <div class="post-button">
      <PostVTButton :nbByvehicleType="nbByvehicleType" />
    </div>
  </div>
</template>

<style scoped>
.vehicle-type-container {
  background-color: #3a5f70; /* Darker shade based on #3a5f70 */
  border: 1px solid #3a5f70; /* Border matches the background */
  border-radius: 8px;
  padding: 20px;
  margin: 10px;
  color: white; /* For better readability */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Enhanced shadow for depth */
}

.title {
  margin-bottom: 10px;
  font-size: 1.2rem;
}

.update-interval {
  margin-bottom: 20px;
}

.interval-selector {
  padding: 5px;
  border-radius: 4px;
  border: none; /* Remove default border */
  background-color: #e9ecef; /* Light background for the dropdown */
  color: #333;
}

.table-and-buttons, .post-button {
  margin-top: 20px;
}

/* You may want to style the TableAndButtons and PostVTButton components as well */
</style>

