<template>
    <div>
      <table v-if="paginatedVehicle && paginatedVehicle.length" class="styled-table">
        <thead>
          <tr>
            <th>VIN</th>
            <th>Last Message Date</th>
            <th>PNB</th>
            <th>Vehicle Type</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="v in paginatedVehicle" :key="v.id">
            <td>{{ v.vin }}</td>
            <td>{{ v.lastMsgDate == null ? 0 : v.lastMsgDate}}</td>
            <td>{{ v.pnb == null ? 0 : v.pnb }}</td>
            <td>{{ getVehicleTypeName(v.vehicleTypeId) }}</td>
          </tr>
        </tbody>
      </table>
      <div v-else>No data to display</div>
  
      <!-- Contrôles de pagination -->
      <button @click="currentPage--" :disabled="currentPage <= 1" class="pagination-button">Précédent</button>
      <span>Page {{ currentPage }} sur {{ totalPages }}</span>
      <button @click="currentPage++" :disabled="currentPage >= totalPages" class="pagination-button">Suivant</button>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      vehicle: Array,
      vehicleTypes: Array
    },
    data() {
      return {
        currentPage: 1,
        perPage: 10
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
    methods: {
    getVehicleTypeName(vehicleTypeId) {
      const type = this.vehicleTypes.find(type => type.id === vehicleTypeId);
      return type ? type.name : 'Inconnu';
    }
    }
  }
  </script>
  