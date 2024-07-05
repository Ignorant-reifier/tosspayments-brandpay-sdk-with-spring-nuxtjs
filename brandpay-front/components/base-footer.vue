<template>
  <footer class="bd-footer py-4 py-md-5 mt-5 bg-body-tertiary">
    <div class="container py-4 py-md-5 px-4 px-md-3 text-body-secondary">
      <div class="row">

        <div class="col-lg-3 mb-3">
          <div class="d-flex justify-content-between">
            <div v-for="githubLink in githubLinks" :key="githubLink.name">
              <githubLink :githubLink="githubLink" />
            </div>
          </div>

          <ul class="list-unstyled small">
            <li class="mb-2">Sample Project</li>
            <li class="mb-2">Toss Payments Brand Pay</li>
            <li class="mb-2">version 2</li>
          </ul>
        </div>

        <div class="col-6 col-lg-2 offset-lg-1 mb-3">
          <h5>Links</h5>
          <ul class="list-unstyled">
            <li class="mb-2">
              <a href="/" @click="goNavigateTo('/')">Home</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

import githubLink from '@/components/github/github-link.vue';

@Component({
  components: {
    githubLink
  }
})
export default class BaseFooter extends Vue {

  githubLinks: { url: string; name: string; }[] = [
    { url: 'https://github.com/niwin92/tosspayments-brandpay-sdk-with-spring-nuxtjs', name: 'niwin92', },
    { url: 'https://github.com/oseongryu', name: 'oseongryu', }
  ]

  goNavigateTo(route: string) {
    this.$router.push(route);
  }

}
</script>

<style></style>