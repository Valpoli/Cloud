<template>
  <div>
    <button v-if="!creating" @click="startCreating" class="button">Create</button>

    <div v-if="creating">
      <input v-model="name" placeholder="Name"/>
      <button @click="createCompany" class="button">Confirm</button>
    </div>
  </div>
</template>
  <script>
  import CompanyService from "../../services/CompanyService.js";
  
  export default {
    data() {
      return {
        creating: false,
        name: '',
      };
    },
  
    methods: {
      startCreating() {
        this.creating = true;
      },
 
      async createCompany() {
        try {

          await CompanyService.postCompany(this.name);

  
          // Vider les champs après la création
          this.name = '';
  
          // Faire disparaître le formulaire
          this.creating = false;
  
        } catch (error) {
          console.error("Error creating company:", error);
        }
      }
    }
  }
  </script>
