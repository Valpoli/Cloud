<template>
  <table v-if="vehicleTypes && vehicleTypes.length" border="1">
    <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Capacity</th>
        <th>Manufacturer</th>
        <th>Company Name</th>
        <th>number in circulation</th>
        <th></th> <!-- Une colonne vide pour le bouton -->
      </tr>
    </thead>
    <tbody>
      <tr v-for="vehicleType in vehicleTypes" :key="vehicleType.id">
        <td>{{ vehicleType.id }}</td>
        <td>{{ vehicleType.name }}</td>
        <td>{{ vehicleType.capacity }}</td>
        <td>{{ vehicleType.manufacturer }}</td>
        <td>{{ vehicleType.companyName.name }}</td>
        <td>{{ vehicleType["number in circulation"] }}</td>
        <td><slot :vehicle-id="vehicleType.id"></slot></td> <!-- Slot pour le bouton -->
      </tr>
    </tbody>
  </table>
  <div v-else>No data to display</div>
</template>

<script>
export default {
  props: {
    vehicleTypes: Array
  }
}
</script>

<style scoped>
td:last-child {
  border: none; /* Supprimer la bordure pour la dernière colonne */
}
</style>
