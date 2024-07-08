<template>
  <div class="container-sm">

    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
      <div v-for="product in products" :key="product.prdId" class="col mt-4 mb-4">
        <nuxt-link :to="{ path: '/product/product-detail/', query: { prdId: product.prdId } }">
          <product-card-component :product="product" />
        </nuxt-link>
      </div>
    </div>

  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

import { ProductModel } from '@/models/product-model'

import ProductCardComponent from '@/components/product/product-card.vue'

@Component({
  components: {
    ProductCardComponent,
  }
})
export default class Index extends Vue {

  // product Dummy Data
  products: ProductModel[] = []

  mounted() {
    this.getProductList()
  }

  async getProductList() {
    await this.$axios.get('/api/product/product-list')
      .then((res) => {
        this.products = res.data
      })
      .catch((err) => {
        console.log(err)
      })
  }

}
</script>

<style scoped></style>