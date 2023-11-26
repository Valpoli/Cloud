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
  <div class="company-container">
    <h3 class="title">Company Actions</h3>
    <div class="update-interval">
      Update every:
      <select v-model="updateInterval" class="interval-selector">
        <option value="1000">1 sec</option>
        <option value="3000">3 sec</option>
        <option value="5000" selected>5 sec</option>
      </select>
    </div>
    <div class="comp-table-buttons">
      <CompTableButtons :company="allCompany" />
    </div>
    <div class="post-comp-button">
      <PostCompButton />
    </div>
  </div>
</template>

<style scoped>
.company-container {
  background-color: #3a5f70; /* A blue background */
  color: white; /* Ensuring text is readable on a blue background */
  border-radius: 8px;
  padding: 20px;
  margin: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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
  border: 1px solid #eee;
  background-color: #e9ecef; /* A lighter shade for the selector */
  color: #333;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
}

.comp-table-buttons, .post-comp-button {
  margin-top: 20px;
}

/* Style adjustments for buttons to fit the blue background theme */
button {
  padding: 10px 15px;
  border-radius: 4px;
  border: none;
  color: #3a5f70;
  background-color: white;
  cursor: pointer;
  transition: background-color 0.2s, color 0.2s;
}

button:hover {
  color: white;
  background-color: #3a5f70;
}

/* Additional styles if needed */
</style>
