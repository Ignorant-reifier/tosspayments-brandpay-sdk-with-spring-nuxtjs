<template>
  <div>
    <h1>Home</h1>
    <button @click="goTutorial">Go to Tutorial</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class Index extends Vue {
  
  // 튜토리얼 페이지로 이동하는 버튼 추가
  goTutorial() {
    this.$router.push('/tutorial');
  }

}
</script>


<style>

</style>