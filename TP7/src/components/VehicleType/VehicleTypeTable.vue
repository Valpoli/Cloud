<template>
  <div class="table-container">
    <table v-if="vehicleTypes && vehicleTypes.length">
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
    <div v-else class="no-data">No data to display</div>
  </div>
</template>

<script>
export default {
  props: {
    vehicleTypes: Array
  }
}
</script>
<style scoped>
.table-container {
  overflow-x: auto;
  background-color: #ebf5fb; /* Light ocean blue background for the container */
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1em;
  table-layout: auto; /* Adjusted to 'auto' from 'fixed' to handle text wrapping */
  text-align: left;
}

th, td {
  padding: 0.75em; /* Increased padding for better spacing */
  border-bottom: 1px solid #3a5f70; /* Soft ocean blue for borders */
  word-break: break-word; /* Allows the text to wrap within the cell */
}

th {
  background-color: #3a5f70; /* Deeper ocean blue for headers */
  color: #e0f7fa; /* Soft cyan for header text, providing high contrast */
  min-width: 100px; /* Ensuring minimum width for headers */
}

td {
  color: #102a43; /* Dark blue, almost navy, for better readability */
}

tr:hover {
  background-color: #c3dde0; /* A muted ocean blue for hover effect */
}

tr:last-child td {
  border-bottom: none;
}

td:last-child {
  text-align: center;
  border-right: none;
}

.no-data {
  margin-top: 1em;
  text-align: center;
  color: #3a5f70; /* Echoing the header color for consistency */
  padding: 1em; /* Adding padding for visual comfort */
}
</style>

