<template>
    <div>
      <button v-if="!editing" @click="editing = true">Modify</button>
      
      <div v-if="editing">
        <input v-model="vehicleTypeName" placeholder="Vehicle Type Name" />
        <input v-model="maxPassengerNumber" placeholder="Max Passenger Number" type="number" />
        <button @click="confirmChanges">Confirmer</button>
      </div>
    </div>
  </template>
  
  <script>
  import VehicleTypeService from "../../services/VehicleTypeService.js";
  
  export default {
    props: ['id'],
    
    data() {
      return {
        editing: false,
        vehicleTypeName: '',
        maxPassengerNumber: ''
      };
    },
    methods: {
        startEditing() {
            this.editing = true;
        },

        async confirmChanges() {
          try {
            await VehicleTypeService.putVehicleType(this.id,this.vehicleTypeName,this.maxPassengerNumber);
            // Vider les champs
            this.vehicleTypeName = '';
            this.maxPassengerNumber = '';

            // Faire disparaître les champs et le bouton "Confirmer"
            this.editing = false;
          } catch (error) {
            console.error("Error updating vehicle type:", error);
          }
        }
    }
  
  }
  </script>
  
  <style scoped>
  div {
    margin-bottom: 1em;
    background-color: #ebf5fb; /* Fond bleu clair de l'océan pour le conteneur */
  }
  
  button {
    background-color: #3a5f70; /* Bleu profond de l'océan pour les boutons */
    color: #ffffff; /* Texte blanc pour le contraste */
    border: none;
    padding: 0.5em 1em;
    border-radius: 0.3em; /* Bords arrondis pour le bouton */
    cursor: pointer;
    margin-right: 0.5em;
    transition: background-color 0.3s ease; /* Transition pour l'effet de survol */
  }
  
  button:hover {
    background-color: #285d70; /* Assombrissement du bouton au survol */
  }
  
  button:active {
    transform: scale(0.98); /* Effet de clic */
  }
  
  input {
    border: 1px solid #6ea4b5; /* Bordure bleue douce de l'océan */
    border-radius: 0.3em; /* Bords arrondis pour le champ de saisie */
    /* padding: 0.5em; */
    margin-right: 0.5em;
    width: calc(100%); /* Prend toute la largeur disponible avec un peu d'espace */
  }
  
  input:focus {
    outline: none;
    box-shadow: 0 0 0.5em rgba(58, 95, 112, 0.25); /* Lueur quand le champ est sélectionné */
  }
  
  /* Styles spécifiques pour les boutons lorsqu'ils sont côte à côte */
  .editing-buttons {
    display: flex;
    justify-content: start;
    align-items: center;
    /* gap: 0.5em; Espace entre les éléments */
  }
  </style>