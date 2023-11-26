<template>
  <div>
    <table v-if="company && company.length" class="styled-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th></th> <!-- Une colonne vide pour les actions -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="comp in company" :key="comp.id">
          <td>{{ comp.id }}</td>
          <td>{{ comp.name }}</td>
          <td><slot :company-id="comp.id"></slot></td>
           <!-- Slot pour les actions -->
        </tr>
      </tbody>
    </table>
    <div v-else>No data to display</div>
  </div>
</template>

<script>
export default {
  props: {
    company: Array
  }
}
</script>