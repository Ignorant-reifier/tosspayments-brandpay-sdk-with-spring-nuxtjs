<template>
  <div class="row row-cols-1 row-cols-sm-2">

    <div v-if="product">
      <product-card-component :product="product" />
    </div>

    <div>
      <div>
        <div class="form-group row mb-2">
          <label for="orderId" class="col-sm-2 col-form-label">주문번호</label>
          <div class="col-sm-8">
            <input type="text" class="form-control-plaintext" id="orderId" v-model="orderId" readonly>
          </div>
          <button type="button" class="btn btn-primary" @click="changeRandom('orderId')">랜덤</button>
        </div>

        <div class="form-group row mb-2">
          <label for="customerKey" class="col-sm-2 col-form-label">고객 Key</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="customerKey" v-model="customerKey">
          </div>
        </div>

        <div class="form-group row">
          <label for="customerName" class="col-sm-2 col-form-label">고객 명</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="customerName" v-model="customerName">
          </div>
        </div>

        <div class="form-group row">
          <label for="customerEmail" class="col-sm-2 col-form-label">고객 이메일</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="customerEmail" v-model="customerEmail">
          </div>
        </div>

        <div class="form-group row">
          <label for="customerMobilePhone" class="col-sm-2 col-form-label">고객 휴대폰번호</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="customerMobilePhone" v-model="customerMobilePhone">
          </div>
        </div>

        <template v-if="product">
          <div class="form-group row">
            <label for="prdId" class="col-sm-2 col-form-label">상품ID</label>
            <div class="col-sm-10">
              <input type="text" class="form-control-plaintext" id="prdId" v-model="product.prdId" readonly>
            </div>
          </div>

          <div class="form-group row">
            <label for="prdNm" class="col-sm-2 col-form-label">상품명</label>
            <div class="col-sm-10">
              <input type="text" class="form-control-plaintext" id="prdNm" v-model="product.prdNm" readonly>
            </div>
          </div>

          <div class="form-group row">
            <label for="prdPrice" class="col-sm-2 col-form-label">금액</label>
            <div class="col-sm-10">
              <input type="text" class="form-control-plaintext" id="prdPrice" v-model="product.prdPrice" readonly>
            </div>
          </div>
        </template>

        <div>
          <button type="button" class="btn btn-primary" @click="paymentButton()">결제하기</button>
        </div>
      </div>
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
  }

  // 주문번호 : 랜덤생성
  orderId: string = window.btoa(Math.random().toString()).slice(0, 20)

  // 고객 Key : 랜덤생성
  customerKey: string = "reifier_20240708"
  // 고객명
  customerName: string = "전석호"
  // 고객이메일
  customerEmail: string = "reifier@kakao.com"
  // 고객 휴대폰번호
  customerMobilePhone: string = "01062690425"

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

  changeRandom(division: string) {
    if (division === "orderId") {
      this.orderId = window.btoa(Math.random().toString()).slice(0, 20)
    } else {
      this.customerKey = window.btoa(Math.random().toString()).slice(0, 20)
    }
  }

  formatCurrency(value: number): string {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " 원";
  }

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

}
</script>

<style scoped></style>