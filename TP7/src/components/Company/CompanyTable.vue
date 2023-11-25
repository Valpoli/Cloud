<template>
  <div class="table-container">
    <table v-if="company && company.length">
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
    <div v-else class="no-data">No data to display</div>
  </div>
</template>

<script>
export default {
  props: {
    company: Array
  }
}
</script>

<style scoped>
.table-container {
  overflow-x: auto;
  background-color: #ebf5fb; /* Fond bleu clair de l'océan pour le conteneur */
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1em;
  table-layout: auto; /* Ajusté à 'auto' pour gérer l'enveloppement du texte */
  text-align: left;
}

th, td {
  padding: 0.75em; /* Augmentation de l'espacement pour une meilleure lisibilité */
  border-bottom: 1px solid #6ea4b5; /* Bordure bleue douce de l'océan */
  word-break: break-word; /* Permet au texte de s'envelopper dans la cellule */
}

th {
  background-color: #3a5f70; /* Bleu profond de l'océan pour les entêtes */
  color: #e0f7fa; /* Cyan doux pour le texte de l'entête, offrant un contraste élevé */
  min-width: 100px; /* Assurant une largeur minimale pour les entêtes */
}

td {
  color: #102a43; /* Bleu foncé, presque marine, pour une meilleure lisibilité */
}

tr:hover {
  background-color: #c3dde0; /* Un bleu océan atténué pour l'effet de survol */
}

tr:last-child td {
  border-bottom: none;
}

td:last-child {
  text-align: center;
  border-right: none;
}

.no-data {
  margin-top: 1em;
  text-align: center;
  color: #3a5f70; /* Reprenant la couleur de l'entête pour la cohérence */
  padding: 1em; /* Ajout de rembourrage pour un confort visuel */
}
</style>
