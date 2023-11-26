<script setup>
import VehicleType from './VehicleType/VehicleType.vue';
import Company from './Company/Company.vue';
</script>

<style scoped>
/* Reset pour assurer la cohérence entre les navigateurs */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body, html {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* police moderne */
  background-color: #e7eff1; /* Couleur de fond claire basée sur #3a5f70 */
}

header {
  background-color: #3a5f70; /* Utilisation de la couleur demandée pour l'en-tête */
  color: white; /* Couleur du texte pour l'en-tête */
  padding: 1rem; /* Espacement autour du contenu de l'en-tête */
  text-align: center; /* Centrage du titre */
}

h1 {
  font-size: 2rem; /* Taille du titre */
  margin-bottom: 0.5rem; /* Espacement sous le titre */
}

.main {
  display: flex; /* Utilise Flexbox pour la mise en page */
  justify-content: center; /* Centrage des enfants horizontalement */
  align-items: flex-start; /* Alignement des enfants en haut */
  gap: 2rem; /* Espace entre les composants */
  padding: 2rem; /* Espacement autour du contenu principal */
}

.vehicle-type-container {
  flex-basis: 48%; /* Utilisation de flex-basis au lieu de width pour Flexbox */
  /* background-color: #dae2e5; Couleur de fond plus claire basée sur #3a5f70 */
  padding: 1rem; /* Espacement à l'intérieur de chaque conteneur */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* Ombre portée pour la profondeur */
  border-radius: 8px; /* Bordures arrondies pour les conteneurs */
}

/* Pour les grands écrans */
@media (max-width: 768px) {
  .main {
    flex-direction: column; /* Empile les composants verticalement sur les petits écrans */
  }

  .vehicle-type-container {
    flex-basis: auto; /* Réinitialise flex-basis pour les petits écrans */
    width: 100%; /* Pleine largeur sur les petits écrans */
    margin-bottom: 1rem; /* Espacement entre les composants */
  }
}
</style>



<template>
    <header>
        <h1>Processing of automated telematics data</h1>
    </header>
    <main class="main">
        <div class="vehicle-type-container">
        <VehicleType />
        </div>
        <div class="vehicle-type-container">
        <Company />
        </div>
    </main>
</template>
