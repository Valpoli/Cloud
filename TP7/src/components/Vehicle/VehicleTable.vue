<template>
    <div>
      <table v-if="paginatedVehicle && paginatedVehicle.length">
        <thead>
          <tr>
            <th>VIN</th>
            <th>Last Message Date</th>
            <th>PNB</th>
            <th>Vehicle Type ID</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="v in paginatedVehicle" :key="v.id">
            <td>{{ v.vin }}</td>
            <td>{{ v.lastMsgDate | formatDate }}</td>
            <td>{{ v.pnb == null ? 0 : v.pnb }}</td>
            <td>{{ v.vehicleTypeId }}</td>
          </tr>
        </tbody>
      </table>
      <div v-else>No data to display</div>
  
      <!-- Contrôles de pagination -->
      <button @click="currentPage--" :disabled="currentPage <= 1">Précédent</button>
      <span>Page {{ currentPage }} sur {{ totalPages }}</span>
      <button @click="currentPage++" :disabled="currentPage >= totalPages">Suivant</button>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      vehicle: Array
    },
    data() {
      return {
        currentPage: 1,
        perPage: 15
      };
    },
    computed: {
      totalPages() {
        return Math.ceil(this.vehicle.length / this.perPage);
      },
      paginatedVehicle() {
        let start = (this.currentPage - 1) * this.perPage;
        let end = start + this.perPage;
        return this.vehicle.slice(start, end);
      }
    },
    filters: {
      formatDate(value) {
        if (!value) return '';
        let date = new Date(value);
        return date.toLocaleDateString();
      }
    }
  }
  </script>
  