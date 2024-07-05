<template>
  <div>
    <div>
      <h1>{{ error.statusCode }}</h1>
      <h2>{{ error.message }}</h2>
      <h3>{{ error.path }}</h3>
      <h3>{{ error }}</h3>
    </div>
    <div>
      <button @click="goBack">이전 페이지로 이동</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator"

@Component
export default class extends Vue {

  @Prop({ default: () => ({}), type: Object }) readonly error!: { statusCode: number; path: string; message: string }

  goBack() {
    this.$router.go(-1);
  }

}
</script>

<style>
</style>