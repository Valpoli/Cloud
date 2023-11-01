<script setup>
import VehicleType from './VehiculeType/VehicleType.vue';
import Company from './Company/Company.vue';
</script>

Copy code
<style scoped>

.vehicle-type-container {
  float: left;  /* Pour positionner le composant à gauche */
  width: 100%;   /* Pour limiter sa largeur à la moitié de l'écran */
}
</style>

<template>
    <header>
        <h1>Processing of automated telematics data</h1>
    </header>
    <main>
        <div class="vehicle-type-container">
        <VehicleType />
        </div>
        <div class="vehicle-type-container">
        <Company />
        </div>
    </main>
</template>

