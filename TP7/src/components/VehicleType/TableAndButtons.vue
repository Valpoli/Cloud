<template>
  <div>
    <VehicleTypeTable :vehicleTypes=vehicleTypes>
      <template v-slot:default="{ vehicleId }">
        <EditVTButton :id="vehicleId" />
      </template>
    </VehicleTypeTable>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  justify-content: space-between;  /* Si vous voulez de l'espace entre le tableau et les boutons */
  align-items: start;  /* Aligne les éléments au sommet du conteneur */
}

.buttons {
  margin-left: 20px;  /* Ajoutez une marge à gauche des boutons pour les espacer un peu du tableau */
}
</style>

<script>
import EditVTButton from './EditVTButton.vue';
import VehicleTypeTable from './VehicleTypeTable.vue';

export default {
  props: {
    vehicleTypes: Array
  },
  components: {
    EditVTButton,
    VehicleTypeTable
  }
}
</script>
