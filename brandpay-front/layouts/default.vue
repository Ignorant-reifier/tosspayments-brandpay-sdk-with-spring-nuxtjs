<template>
  <div>

    <base-top />

    <base-header />

    <main class="container-fluid">
      <nuxt />
    </main>

    <base-footer />

  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class extends Vue {
}
</script>

<style scoped></style>