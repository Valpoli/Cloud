<script setup>
import { ref, onMounted, onUnmounted, watch } from 'vue';
import CompanyService from "../../services/CompanyService.js";
import CompTableButtons from './CompTableButtons.vue';
import PostCompButton from './PostCompButton.vue';

const updateInterval = ref(5000);
const allCompany = ref([]);

const updatePrinting = async () => {
    try {
        const response = await CompanyService.getAllCompanies()
        allCompany.value = response.data
    } catch (error) {
        allvehicleTypes.value = error;
        allCompany.error(error);
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
    <h3 class="fancy-subtitle">Company Actions</h3>
    <div class="bordered-container">
      Update every:
      <select v-model="updateInterval">
        <option value="1000">1 sec</option>
        <option value="3000">3 sec</option>
        <option value="5000" selected>5 sec</option>
      </select>
    </div>
    <div>
      <CompTableButtons :company="allCompany" />
    </div>
    <div>
      <PostCompButton />
    </div>
  </div>
</template>

