<script setup>
import VehicleType from './VehicleType.vue';
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
        <h1>Main page</h1>
    </header>
    <main>
        <div class="vehicle-type-container">
        <VehicleType />
        </div>
    </main>
</template>

