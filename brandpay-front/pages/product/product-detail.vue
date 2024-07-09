<template>
  <div v-if="product">
    <div class="row justify-content-md-center mt-5">

      <div class="col col-lg-5 row justify-content-center">
        <img :src="product.prdImg" class="img-thumbnail" alt="product.prdNm" />
      </div>

      <div class="col col-lg-5">

        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="orderId">주문번호</span>
          </div>

          <input type="text" class="form-control" v-model="orderId" readonly>
          
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button" @click="changeRandom">랜덤</button>
          </div>
        </div>

        <template>

          <h5><span class="badge badge-secondary">상품 정보</span></h5>

          <div class="input-group mb-2">
            <div class="input-group-prepend">
              <span class="input-group-text">상품 ID</span>
            </div>
            <input type="text" class="form-control" v-model="product.prdId" readonly>
          </div>

          <div class="input-group mb-2">
            <div class="input-group-prepend">
              <span class="input-group-text">상품 명</span>
            </div>
            <input type="text" class="form-control" v-model="product.prdNm" readonly>
          </div>

          <div class="input-group mb-2">
            <div class="input-group-prepend">
              <span class="input-group-text">상품 가격</span>
            </div>
            <input type="text" class="form-control" v-model="product.prdPrice" readonly>
          </div>
          
        </template>

        <template>

          <h5><span class="badge badge-secondary">고객 정보</span></h5>

          <div class="input-group mb-2">
            <div class="input-group-prepend">
              <span class="input-group-text">고객 Key</span>
            </div>
            <input type="text" class="form-control" v-model="customerKey" >
          </div>

          <div class="input-group mb-2">
            <div class="input-group-prepend">
              <span class="input-group-text">고객 명</span>
            </div>
            <input type="text" class="form-control" v-model="customerName" >
          </div>

          <div class="input-group mb-2">
            <div class="input-group-prepend">
              <span class="input-group-text">고객 연락처</span>
            </div>
            <input type="text" class="form-control" v-model="customerMobilePhone" >
          </div>

          <div class="input-group mb-2">
            <div class="input-group-prepend">
              <span class="input-group-text">고객 이메일</span>
            </div>
            <input type="text" class="form-control" v-model="customerEmail" >
          </div>

        </template>

      </div>
    </div>

    <div class="row justify-content-md-center mt-5">
      <button type="button" class="ml-2 mr-2 btn btn-primary" @click="">카드 결제</button>
      <button type="button" class="ml-2 mr-2 btn btn-info" @click="">계좌 결제</button>
    </div>

  </div>

</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { ProductModel } from '@/models/product-model'

import ProductCardComponent from '@/components/product/product-card.vue'

import { loadTossPayments, ANONYMOUS } from "@tosspayments/tosspayments-sdk"

@Component({
  components: {
    ProductCardComponent,
  },
})
export default class ProductDetailComponent extends Vue {

  // 파라미터 - 상품 ID
  prdId: string | null = null

  // 상품 정보
  product: ProductModel | null = null

  // 주문번호 : 랜덤생성
  orderId: string | null = null

  mounted() {
    
    this.loadData()

  }

  async loadData() {
    const prdId = this.$route.query?.prdId

    if (prdId) {
      this.prdId = prdId as string

      const result = await this.$axios.get(`/api/product/${this.prdId}`)
      if (result.status === 200) {
        console.log("result.data", result.data)
        this.product = result.data
      }
    }

    this.changeRandom()
  }

  /**
   * 고객 정보
   */
  customerKey: string = "reifier_" + new Date().toISOString().replace(/[^0-9]/g, "").slice(0, 8)
  customerName: string = "전토스결제"
  customerEmail: string = "reifier@kakao.com"
  customerMobilePhone: string = "01062690425"

  /**
   * 토스 페이먼츠
   * 브랜드페이 SDK
   */
  tossPayments: any = null
  brandpay: any = null

  // Toss 결제 테스트용 키
  clientKey: string = 'test_ck_5OWRapdA8dP6a9GmjzkR8o1zEqZK'

  created() {
    //const selectedProduct = localStorage.getItem('selectedProduct')

    //this.product = JSON.parse(selectedProduct)
    
    //localStorage.removeItem('selectedProduct')
  }

  // async mounted() {
  //   this.tossPayments = await loadTossPayments(this.clientKey)

  //   const customerKey = this.customerKey

  //   this.brandpay = await this.tossPayments.brandpay({
  //     customerKey,
  //     redirectUrl: window.location.origin + "/api/payments/callback-auth",
  //   })
  // }

  changeRandom() {
    this.orderId = "ORD_" + window.btoa(Math.random().toString()).slice(0, 16)
  }

  formatCurrency(value: number): string {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " 원";
  }

  /*
  async paymentButton() {

    await this.brandpay.requestPayment({
      amount: {
        currency: "KRW",
        //value: this.product.prdPrice
      },
      orderId: this.orderId,
      //orderName: this.product.prdNm,

      customerName: this.customerName,
      customerEmail: this.customerEmail,
      //customerMobilePhone: this.customerMobilePhone,

      successUrl: window.location.origin + "/api/payments/callback-success",
      failUrl: window.location.origin + '/api/payments/callback-fail',
    })
    
  }
  */

}
</script>

<style scoped>
.input-group-text {
  min-width: 111px;
}
</style>