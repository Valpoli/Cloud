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
  