<template>
  <div class="create-container">
    <button v-if="!creating" @click="startCreating" class="create-button">Create</button>

    <div v-if="creating" class="create-form">
      <input v-model="name" placeholder="Name" class="create-input" />
      <button @click="createCompany" class="confirm-button">Confirm</button>
    </div>
  </div>
</template>
  <script>
  import CompanyService from "../../services/CompanyService.js";
  
  export default {
    data() {
      return {
        creating: false,  // Contrôle l'affichage du formulaire
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

<style scoped>
.create-container {
  /* Your container styles here */
}

.create-button, .confirm-button {
  background-color: #3a5f70; /* Ocean blue background */
  color: #ffffff; /* White text for contrast */
  padding: 0.5em 1em;
  border-radius: 0.3em; /* Rounded corners */
  cursor: pointer;
  border: none;
  transition: background-color 0.3s ease; /* Smooth transition for hover effect */
}

.create-button:hover, .confirm-button:hover {
  background-color: #49788c; /* A lighter ocean blue for hover effect */
}

.create-input {
  padding: 0.5em;
  margin-right: 0.5em; /* Space between input and button */
  border-radius: 0.3em;
  border: 1px solid #6ea4b5; /* Border color to match the ocean theme */
}

/* If you want to add styles for the create-form */
.create-form {
  /* Additional form styles here */
}

/* Additional global styles for inputs can be added to keep consistency */
input {
  color: #102a43; /* Dark blue for text to improve readability */
}
</style>
