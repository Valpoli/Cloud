<template>
    <table v-if="company && company.length" border="1">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th></th> <!-- Une colonne vide pour le bouton -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="comp in company" :key="comp.id">
          <td>{{ comp.id }}</td>
          <td>{{ comp.name }}</td>
          <td><slot :company-id="comp.id"></slot></td>
           <!-- Slot pour le bouton -->
        </tr>
      </tbody>
    </table>
    <div v-else>No data to display</div>
  </template>
  
  <script>
  export default {
    props: {
      company: Array
    }
  }
  </script>
  
  <style scoped>
  td:last-child {
    border: none; /* Supprimer la bordure pour la dernière colonne */
  }
  </style>
  