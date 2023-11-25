<script setup>
import Main from './components/Main.vue'
</script>

<template>
  <header>
    <div class="wrapper">
      <Main></Main>
    </div>
</header>
</template>

<style scoped>
header {
  background: linear-gradient(to right, #5f8a88, #9cbebd); /* Un dégradé subtil de gauche à droite */
  color: #fff; /* Couleur du texte blanche pour contraste */
  /* padding: 1rem 0; Espacement en haut et en bas de l'en-tête */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Ombre portée légère pour donner de la profondeur */
}

.wrapper {
  max-width: 1200px; /* Largeur maximale pour le wrapper pour une meilleure lisibilité sur les grands écrans */
  margin: 0 auto; /* Centrage du wrapper */
  padding: 0 2rem; /* Espacement à gauche et à droite */
  /* display: flex; Utilise Flexbox pour une mise en page flexible */
  justify-content: center; /* Centrage horizontal du contenu */
  align-items: center; /* Centrage vertical du contenu */
  height: 100vh; /* Hauteur de l'en-tête étendue à tout l'écran visible */
}

</style>

