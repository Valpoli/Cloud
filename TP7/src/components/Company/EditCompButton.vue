<template>
    <div>
      <button v-if="!editing" @click="editing = true">Modify</button>
      
      <div v-if="editing">
        <input v-model="companyName" placeholder="Company Name" />
        <button @click="confirmChanges">Confirmer</button>
      </div>
    </div>
  </template>
  
  <script>
  import CompanyService from "../../services/CompanyService.js";
  
  export default {
    props: ['id'],
    
    data() {
      return {
        editing: false,
        companyName: ''
      };
    },
    methods: {
        startEditing() {
            this.editing = true;
        },

        async confirmChanges() {
          try {
            await CompanyService.putCompany(this.id,this.companyName);
            // Vider les champs
            this.companyName = '';

            // Faire disparaître les champs et le bouton "Confirmer"
            this.editing = false;
          } catch (error) {
            console.error("Error updating company:", error);
          }
        }
    }
  
  }
  </script>
  