<template>
  <div class="container-sm">

  <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <div v-for="product in products" :key="product.prdId" class="col mt-4 mb-4">
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
    ProductCardComponent
  }
})
export default class Index extends Vue {

  // product Dummy Data
  products: ProductModel[] = [
    {
      prdId: 2024070501,
      prdName: '햄버거세트',
      prdPrice: 10000,
      prdDesc: '01. 햄버거+감자튀김',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53509257545_33dcb17f35_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070502,
      prdName: '호박쿠키',
      prdPrice: 2000,
      prdDesc: '02. 호박쿠키',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53314517799_57b16a1606_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070503,
      prdName: '사이다',
      prdPrice: 3000,
      prdDesc: '03. 사이다',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53509144559_144cbcd1c0_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070504,
      prdName: '미니피자',
      prdPrice: 8000,
      prdDesc: '04. 미니피자(Thin+햄+치즈)',
      prdImage: 'https://loremflickr.com/cache/resized/7430_9889194026_7205ed9476_z_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070505,
      prdName: '페페로니피자',
      prdPrice: 11000,
      prdDesc: '05. 페페로니피자',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53786675707_cfecaa1439_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070506,
      prdName: '브로콜리크림파스타',
      prdPrice: 12000,
      prdDesc: '06. 브로콜리+크림+파스타',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53530004348_fc96fe9739_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070507,
      prdName: '편의점초밥',
      prdPrice: 9900,
      prdDesc: '07. 편의점초밥',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53686319102_b2517e7aaa_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070508,
      prdName: '사탕-빨강',
      prdPrice: 500,
      prdDesc: '08. 사탕-빨강',
      prdImage: 'https://loremflickr.com/cache/resized/2683_4349477798_4e9c99d84a_z_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070509,
      prdName: '피자빵샐러드',
      prdPrice: 13000,
      prdDesc: '09. 피자빵샐러드',
      prdImage: 'https://loremflickr.com/cache/resized/6058_6247428341_c4136c5c9d_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070510,
      prdName: '모카크림케이크',
      prdPrice: 6500,
      prdDesc: '10. 모카+크림+케이크',
      prdImage: 'https://loremflickr.com/cache/resized/4017_4211967140_997ff3db40_350_350_nofilter.jpg'
    },
  ]


}
</script>

<style scoped>
</style>