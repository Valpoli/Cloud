<script setup>
import VehicleType from './VehicleType/VehicleType.vue';
import Company from './Company/Company.vue';
import Vehicle from './Vehicle/Vehicle.vue';
</script>

<template>
    <header>
        <h1>Processing of automated telematics data</h1>
    </header>
    <main>
        <div>
        <Vehicle />
        </div>
        <div>
        <VehicleType />
        </div>
        <div>
        <Company />
        </div>
    </main>
</template>
