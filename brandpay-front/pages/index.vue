<template>
  <div class="container-sm">

    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
      <div v-for="product in products" :key="product.prdId" class="col mt-4 mb-4" @>
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
      prdDesc: '01. 풍미 가득한 햄버거와 바삭한 감자튀김 세트',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53509257545_33dcb17f35_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070502,
      prdName: '호박쿠키',
      prdPrice: 2000,
      prdDesc: '02. 달콤하고 부드러운 호박 맛이 가득한 쿠키',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53314517799_57b16a1606_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070503,
      prdName: '사이다',
      prdPrice: 3000,
      prdDesc: '03. 시원하고 청량감 넘치는 탄산 음료 사이다',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53509144559_144cbcd1c0_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070504,
      prdName: '미니피자',
      prdPrice: 8000,
      prdDesc: '04. 얇은 도우에 햄과 치즈를 얹어 구워낸 작은 피자',
      prdImage: 'https://loremflickr.com/cache/resized/7430_9889194026_7205ed9476_z_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070505,
      prdName: '페페로니피자',
      prdPrice: 11000,
      prdDesc: '05. 얇은 도우에 햄과 치즈를 얹어 구운 미니피자',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53786675707_cfecaa1439_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070506,
      prdName: '브로콜리파스타',
      prdPrice: 12000,
      prdDesc: '06. 신선한 브로콜리와 크림이 어우러진 파스타',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53530004348_fc96fe9739_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070507,
      prdName: '편의점초밥',
      prdPrice: 9900,
      prdDesc: '07. 간편하게 즐길 수 있는 신선한 편의점 초밥',
      prdImage: 'https://loremflickr.com/cache/resized/65535_53686319102_b2517e7aaa_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070508,
      prdName: '사탕-빨강',
      prdPrice: 500,
      prdDesc: '08. 달콤한 빨간색 사탕, 어린이들이 좋아하는 맛',
      prdImage: 'https://loremflickr.com/cache/resized/2683_4349477798_4e9c99d84a_z_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070509,
      prdName: '피자빵샐러드',
      prdPrice: 13000,
      prdDesc: '09. 피자빵과 신선한 샐러드가 어우러진 한 접시',
      prdImage: 'https://loremflickr.com/cache/resized/6058_6247428341_c4136c5c9d_350_350_nofilter.jpg'
    },
    {
      prdId: 2024070510,
      prdName: '모카크림케이크',
      prdPrice: 6500,
      prdDesc: '10. 모카 향이 가득한 부드러운 크림 케이크',
      prdImage: 'https://loremflickr.com/cache/resized/4017_4211967140_997ff3db40_350_350_nofilter.jpg'
    },
  ]


}
</script>

<style scoped></style>