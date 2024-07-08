<template>
  <div class="container-sm">

    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
      <div v-for="product in products" :key="product.prdId" class="col mt-4 mb-4" @click="goProductDetail(product)">
        <ProductCardComponent :product="product" />
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
    await this.$axios.get('http://localhost:8080/product/product-list')
      .then((res) => {
        console.log(res.data)
        this.products = res.data
      })
      .catch((err) => {
        console.log(err)
      })
  }

  goProductDetail(product: ProductModel) {
    console.log(product.prdId)
    localStorage.setItem('selectedProduct', JSON.stringify(product));
    this.$router.push({ path: '/product/product-detail', params: { prdId: product.prdId.toString() } })
  }

}
</script>

<style scoped></style>