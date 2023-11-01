<script setup>
import { ref, onMounted, onUnmounted, watch } from 'vue';
import VehicleTypeService from "../../services/VehicleTypeService.js";
import CompanyService from "../../services/CompanyService.js";
// import VehicleTypeTable from './VehicleTypeTable.vue';
import TableAndButtons from './TableAndButtons.vue';
import PostVTButton from './PostVTButton.vue';

const allVehiculeTypes = ref([]);
const nbByVehiculeType = ref([]);
const updateInterval = ref(5000);
const allCompany = ref([]);

const initialisation = async () => {
    try {
        const response = await VehicleTypeService.getAllVehicleTypes();
        const resNbVehiculeType = await VehicleTypeService.addNbVehiculesByTypes(response.data)
        nbByVehiculeType.value = resNbVehiculeType
        allVehiculeTypes.value = VehicleTypeService.addNumberInCirculation(response.data,nbByVehiculeType.value)
        const resAllCompany = await CompanyService.getAllCompanies()
        allCompany.value = resAllCompany.data
    } catch (error) {
        allVehiculeTypes.value = error;
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
          const resNbVehiculeType = await VehicleTypeService.addNbVehiculesByTypes(response.data)
          nbByVehiculeType.value = resNbVehiculeType
          allCompany.value = resAllCompany.data
        }
        allVehiculeTypes.value = VehicleTypeService.addNumberInCirculation(response.data,nbByVehiculeType.value)
    } catch (error) {
        allVehiculeTypes.value = error;
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
  <div style="border: 1px solid black; padding: 20px; margin: 10px;">
    <h3>Vehicule Type Action</h3>
    <div>
      Update every:
      <select v-model=updateInterval>
        <option value="1000">1 sec</option>
        <option value="3000">3 sec</option>
        <option value="5000" selected>5 sec</option>
      </select>
    </div>
    <div style="margin-top: 20px;">
      <TableAndButtons :vehicleTypes=allVehiculeTypes />
    </div>
    <div style="margin-top: 20px;">
      <PostVTButton :nbByVehiculeType=nbByVehiculeType />
    </div>
    <!-- <p>{{ allVehiculeTypes }}</p>
    <p>{{ allCompany }}</p> -->
  </div>
</template>
