<template>
  <div>
    <VehicleTypeTable :vehicleTypes=vehicleTypes>
      <template v-slot:default="{ vehicleId }">
        <EditVTButton :id="vehicleId" />
      </template>
    </VehicleTypeTable>
  </div>
</template>

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
