<template>
  <!--
    https://getbootstrap.com/docs/4.6/components/card/
  -->

  <div class="card shadow">
    <img :src="product.prdImage" class="card-img-top img-thumbnail" alt="product.prdName" />

    <div class="card-header text-center d-flex justify-content-between">
      <div><span class="badge badge-info">상품명</span></div>
      <div>
        <p class="h6">{{ product.prdName }}</p>
      </div>
    </div>

    <div class="card-body">
      <div><span class="badge badge-info">상품설명</span></div>
      <small class="text-muted">{{ product.prdDesc }}</small>
    </div>

    <ul class="list-group list-group-flush">
      <li class="list-group-item text-center d-flex justify-content-between">
        <div><span class="badge badge-info">금액</span></div>
        <h6>{{ formatCurrency(product.prdPrice) }}</h6>
      </li>
    </ul>

    <div class="card-footer text-center d-flex justify-content-between">
      <div><span class="badge badge-info">상품ID</span></div>
      <div><small class="text-body-secondary">{{ product.prdId }}</small></div>
    </div>
  </div>

</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { ProductModel } from '@/models/product-model'

@Component
export default class ProductCardComponent extends Vue {

  @Prop() product!: ProductModel;

  formatCurrency(value: number): string {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " 원";
  }

}
</script>

<style scoped></style>