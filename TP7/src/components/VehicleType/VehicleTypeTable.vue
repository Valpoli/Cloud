<template>
  <div>
    <table v-if="vehicleTypes && vehicleTypes.length" class="styled-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Capacity</th>
          <th>Manufacturer</th>
          <th>Company Name</th>
          <th>Number in Circulation</th>
          <th></th> <!-- Une colonne vide pour les actions -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="vehicleType in vehicleTypes" :key="vehicleType.id">
          <td>{{ vehicleType.id }}</td>
          <td>{{ vehicleType.name }}</td>
          <td>{{ vehicleType.capacity }}</td>
          <td>{{ vehicleType.manufacturer }}</td>
          <td>{{ vehicleType.companyName.name }}</td>
          <td>{{ vehicleType['number in circulation'] }}</td>
          <td><slot :vehicle-id="vehicleType.id"></slot></td> <!-- Slot pour les actions -->
        </tr>
      </tbody>
    </table>
    <div v-else>No data to display</div>
  </div>
</template>

<script>
export default {
  props: {
    vehicleTypes: Array
  }
}
</script>