<script setup>
import VehicleType from './VehicleType/VehicleType.vue';
import Company from './Company/Company.vue';
import Vehicle from './Vehicle/Vehicle.vue';
</script>

<template>
    <header class="back_white"> 
        <div class="padding-20">
            <h1 class="fancy-title">Toulousian Trafic RWC 2023</h1>
            <img src="@/assets/epita.png" alt="Logo" class="header-logo" />
            <img src="@/assets/cdmRugby.svg" alt="Logo" class="header-logo" />
            <img src="@/assets/logoToulouse.png" alt="Logo" class="header-logo" />
        </div>
    </header>
    <main>
        <div class="padding-20">
        <Vehicle />
        </div>
        <div class="separator-bar"></div>
        <div class="padding-20">
        <VehicleType />
        <Company class="margin-left"/>
        </div>
    </main>
</template>