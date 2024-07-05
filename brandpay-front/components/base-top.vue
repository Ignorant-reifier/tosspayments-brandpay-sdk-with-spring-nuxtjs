<template>
  <div class="d-block text-center text-bold bd-announcement">
    <div :class="['collapse', { show: isNavCollapsed }]" id="navbarToggleExternalContent">
      <div class="bg-dark p-4">
        <h5 class="text-white h4">Toss Payments - Brand Pay SDK 2</h5>
        <h6>
          <span class="badge badge-secondary">Back-End</span>
          <span class="text-muted"> Java + Spring Boot</span>
        </h6>
        <h6>
          <span class="badge badge-secondary">Front-End</span>
          <span class="text-muted">Vue.js 2 + Nuxt.js 2 + TypeScript + Bootstrap 4.6</span>
        </h6>
      </div>
    </div>

    <nav class="navbar navbar-dark bg-dark">
      <button class="navbar-toggler" type="button" @click="isNavCollapsed = !isNavCollapsed"
        aria-controls="navbarToggleExternalContent" :aria-expanded="isNavCollapsed" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <h5 class="text-white h4">Toss Payments - Brand Pay SDK 2</h5>
    </nav>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component({})
export default class BaseTop extends Vue {

  isNavCollapsed: boolean = false

  goNavigateTo(route: string) {
    this.$router.push(route)
  }

}
</script>

<style></style>