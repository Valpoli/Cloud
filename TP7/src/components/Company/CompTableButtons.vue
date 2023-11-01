<template>
    <div>
      <CompanyTable :company=company>
        <template v-slot:default="{ companyId }">
          <EditCompButton :id="companyId" />
          <DeleteCompButton :id="companyId" />
        </template>
      </CompanyTable>
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
  import EditCompButton from './EditCompButton.vue';
  import CompanyTable from './CompanyTable.vue';
  import DeleteCompButton from './DeleteCompButton.vue';

  
  export default {
    props: {
      company: Array
    },
    components: {
      EditCompButton,
      CompanyTable,
      DeleteCompButton
    }
  }
  </script>
  