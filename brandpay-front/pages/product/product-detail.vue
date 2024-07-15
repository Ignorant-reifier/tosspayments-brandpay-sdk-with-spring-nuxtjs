<template>
  <div v-if="product">
    <div class="row mt-5">

      <div class="col-sm-12 col-md-6">
        <img :src="product.prdImg" class="img-thumbnail" alt="product.prdNm" style="width: 100%; height: 100%;" />
      </div>

      <div class="col-sm-12 col-md-6">

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

          <h5><span class="badge badge-info">상품 정보</span></h5>

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

          <h5><span class="badge badge-danger">고객 정보</span></h5>

          <div class="btn-group btn-group-toggle mt-1 mb-1" style="width: 100%;">
            <label v-for="(customer, index) in customers" :key="index" class="btn btn-outline-secondary"
              style="font-size: 12px;" :class="{ active: customerKey === customer.customerKey }"
              @click="selectedCustomer(customer)">
              <input type="radio" name="options" :checked="customerKey === customer.customerKey">{{ customer.customerName
                === "" ? "직접입력" : customer.customerName }}
            </label>
          </div>

          <div class="input-group mb-2">
            <div class="input-group-prepend">
              <span class="input-group-text">
                고객 Key
                <span class="ml-2" style="font-weight: bold; color: red;">*</span>
              </span>
            </div>
            <input type="text" class="form-control" v-model="customerKey">
          </div>

          <div class="input-group mb-2">
            <div class="input-group-prepend">
              <span class="input-group-text">
                고객 명
                <span class="ml-2" style="font-weight: bold; color: red;">*</span>
              </span>
            </div>
            <input type="text" class="form-control" v-model="customerName">
          </div>

          <div class="input-group mb-1">
            <div class="input-group-prepend">
              <span class="input-group-text">
                고객 메일
                <span class="ml-2" style="font-weight: bold; color: red;">*</span>
              </span>
            </div>
            <input type="text" class="form-control" v-model="customerEmail">
          </div>

          <button type="button" class="btn btn-outline-danger btn-sm" style="width: 100%;" @click="applyCustomer()">
            고객정보 적용하기
            <span class="ml-2" style="font-weight: bold; color: red;">*</span>
          </button>

        </template>

      </div>
    </div>

    <div v-if="brandpayFlag">
      <div class="row justify-content-md-center mt-5">
        <button type="button" class="ml-2 mr-2 btn btn-primary" @click="requestPayment('REDIRECT')">Redirect 결제</button>
        <button type="button" class="ml-2 mr-2 btn btn-info" @click="requestPayment('PROMISE')">Promise 결제</button>
      </div>

      <div class="row justify-content-md-center mt-5">
        <button type="button" class="ml-2 mr-2 btn btn-primary" @click="addPaymentMethod()">결제수단추가</button>
        <button type="button" class="ml-2 mr-2 btn btn-primary" @click="changeOneTouchPay()">원터치페이설정변경</button>
        <button type="button" class="ml-2 mr-2 btn btn-primary" @click="isOneTouchPayEnabled()">원터치결제사용가능여부 조회</button>
        <button type="button" class="ml-2 mr-2 btn btn-primary" @click="changePassword()">비밀번호변경</button>
        <button type="button" class="ml-2 mr-2 btn btn-primary" @click="openSettings()">브랜드페이 설정 열기</button>
      </div>
    </div>

  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Watch } from 'vue-property-decorator'
import { ProductModel } from '@/models/product-model'

import ProductCardComponent from '@/components/product/product-card.vue'

import { loadTossPayments, ANONYMOUS } from "@tosspayments/tosspayments-sdk"
import { RequestPaymentParams } from '@/models/payments-model'

import { BvToast } from 'bootstrap-vue'

@Component({
  components: {
    ProductCardComponent,
  },
})
export default class ProductDetailComponent extends Vue {

  // 파라미터 - 상품 ID
  prdId: string | null = null

  // 주문번호 : 랜덤생성
  orderId: string | null = null

  // 상품 정보
  product: ProductModel | null = null

  // 고객 더미 데이터
  customers: { customerKey: string, customerName: string, customerEmail: string }[] = []
  /**
   * 고객 정보
   */
  customerKey: string = ""
  customerName: string = ""
  customerEmail: string = ""

  mounted() {
    this.loadData()

    // 브랜드페이 SDK 객체 생성
    //this.fetchBrandpay()

  }

  selectedCustomer(customer: { customerKey: string, customerName: string, customerEmail: string }) {
    this.customerKey = customer.customerKey
    this.customerName = customer.customerName
    this.customerEmail = customer.customerEmail

    this.brandpayFlag = false
    this.brandpay = null
  }

  async loadData() {
    const prdId = this.$route.query?.prdId

    if (prdId) {
      this.prdId = prdId as string

      const productInfo = await this.$axios.get(`/api/product/${this.prdId}`)
      if (productInfo.status === 200) {
        this.product = productInfo.data
      }

      const customerInfo = await this.$axios.get(`/api/customer`)
      if (customerInfo.status === 200) {
        this.customers = customerInfo.data
      }

      this.customers.unshift({ customerKey: "", customerName: "", customerEmail: "" })

    }

    this.changeRandom()
  }


  count: number = 0

  makeToast(title: string, message: string, variant = null) {

    // Use a shorter name for this.$createElement
    const h = this.$createElement

    // Create the message
    const vNodeMsg = h(
      'p',
      { class: ['text-center', 'mb-0'] },
      [
        h('b-spinner', { props: { type: 'grow', small: true } }),
        ' ', message, ' ',
        h('b-spinner', { props: { type: 'grow', small: true } })
      ]
    )

    this.$bvToast.toast([vNodeMsg], {
      title: title,
      variant: variant,
      solid: true,
      toaster: 'b-toaster-bottom-right',
    })
  }

  applyCustomer() {

    let toastTitle = ""

    if (this.customerKey === "") {
      toastTitle += " [ 고객 Key ] "
    }

    if (this.customerName === "") {
      toastTitle += " [ 고객 명 ] "
    }

    if (this.customerEmail === "") {
      toastTitle += " [ 고객 메일 ] "
    }

    if (toastTitle !== "") {
      this.makeToast(toastTitle, "필수정보를 입력해주세요.", "danger")
      return
    }

    this.fetchBrandpay()
  }

  /**
   * < STEP 0 >
   * 
   * 토스 페이먼츠 객체
   * 클라이언트 키
   */
  brandpayFlag: boolean = false
  brandpay: any = null
  clientKey: string = 'test_ck_5OWRapdA8dP6a9GmjzkR8o1zEqZK'

  /**
   * < STEP 1 >
   * 
   * 모듈 임포트 연동방식
   * 
   * loadTossPayments를 사용하여 SDK 초기화
   * @todo : clientKey = 개발자센터 > API 키 > API 개별 연동키 > 클라이언트키 로 변경
   * 
   * 
   * 브랜드페이 객체 생성
   * @todo : reditectUrl = 개발자센터 > 브랜드페이 > 리다이렉트 URL 아래 URL 추가
   */
  async fetchBrandpay() {

    try {

      console.log("===== # 01. 토스페이먼츠 : 초기화 =====")

      const customerKey = this.customerKey

      /**
       * 토스페이먼츠 초기화
       * @docs https://docs.tosspayments.com/sdk/v2/js#토스페이먼츠-초기화
       */
      const tossPayments = await loadTossPayments(this.clientKey)

      /**
       * 브랜드페이 초기화
       * @docs https://docs.tosspayments.com/sdk/v2/js#tosspaymentsbrandpay
       */
      const brandpay = tossPayments.brandpay({
        customerKey,
        redirectUrl: window.location.origin + "/api/payments/callback-auth",
      })

      this.brandpay = brandpay

      this.makeToast("# 01. 브랜드페이 객체 생성 - 성공", "자세한 내용은 개발자 콘솔을 확인하세요.", "success")

      this.brandpayFlag = true
    } catch (error) {
      this.makeToast("# 01. 브랜드페이 객체 생성 - 실패", "01. 토스페이먼츠 초기화 ERROR : 자세한 내용은 개발자 콘솔을 확인하세요.", "danger")
      console.error(error)

      this.brandpayFlag = false
    }

  }

  /**
   * < STEP 2 >
   * 
   * Redirect 방식 : 해당 URL이 이동하기 때문에 `void`가 응답
   * 
   * [ 결제 ] 버튼 클릭 > 결제창 오픈
   * @docs https://docs.tosspayments.com/sdk/v2/js#brandpayrequestpayment
   */
  async requestPayment(method: "REDIRECT" | "PROMISE") {

    console.log("===== # 02. 결제창 오픈 (" + method + " 방식) =====")

    const requestPaymentParams: RequestPaymentParams = {

      amount: {
        /**
         * 결제 통화
         */
        currency: "KRW",

        /**
         * 결제 금액
         */
        value: this.product.prdPrice
      },

      /**
       * 주문번호
       * 각 주문을 구분하는 무작위한 고유값
       * 영문 대소문자, 숫자, 특수문자 `-`, `_`, `=`로 이루어진
       * 6자 이상 64자 이하의 문자열
       */
      orderId: this.orderId,

      /**
       * 구매상품
       * ex) `생수 외 1건` 같은 형식
       * 최대 길이 100자
       */
      orderName: this.product.prdNm,

      /**
       * 구매자명
       * 최대 길이 100자
       */
      customerName: this.customerName,

      /**
       * 구매자 이메일
       * 결제 상태가 바뀌면 이메일 주소로 결제내역이 전송
       * 최대 길이 100자
       */
      customerEmail: this.customerEmail,

      /**
       * 결제 금액 중 면세 금액
       * 면세 상점 혹은 복합 과세 상점으로 계약된 상점만 사용
       * 자세한 내용은 세금 처리 가이드에서 확인
       */
      //taxFreeAmount: 0,
    }

    /**
     * 리다이렉트 방식일 경우
     */
    if (method === "REDIRECT") {
      /**
       * 결제 요청이 성공하면 리다이렉트되는 URL
       * `https://www.example.com/success`와 같이 오리진을 포함한 형태로 설정
       * 리다이렉트되면 URL의 쿼리 파라미터로 `amount`, `orderId`, `paymentKey`가 추가
       * 
       * 결제 요청이 성공하면 파라미터로 설정한 `successUrl`로 이동
       * 쿼리 파라미터의 `amount` 값이 파라미터로 설정한 `amount`와 같은지 반드시 확인
       * [브랜드페이 결제 승인 API](/reference/brandpay#결제-승인)를 호출해서 결제를 완료해야 함
       * {successUrl}?amount={AMOUNT}&orderId={ORDER_ID}&paymentKey={PAYMENT_KEY}
       */

      requestPaymentParams.successUrl = window.location.origin + `/payments/brandpay-success?customerKey=${this.customerKey}`

      /**
       * 결제 요청이 실패하면 리다이렉트되는 URL
       * `https://www.example.com/fail`와 같이 오리진을 포함한 형태로 설정
       * 리다이렉트되면 URL의 쿼리 파라미터로 에러 코드와 메시지를 확인
       * 
       * 결제 요청이 실패하면 파라미터로 설정한 `failUrl`로 이동
       * 쿼리 파라미터로 에러 코드와 메시지를 확인
       * {failUrl}?code={ERROR_CODE}&message={ERROR_MESSAGE}&orderId={ORDER_ID}
       */
      requestPaymentParams.failUrl = window.location.origin + "/payments/brandpay-fail"
    }

    console.log("requestPaymentParams (" + method + ")", requestPaymentParams)

    /**
     * 결제 요청 전
     * @todo orderId, amount 를 서버에 저장
     * 
     * 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도
     */

    // TODO 아래 로직 추가해야함


    if (method === "REDIRECT") {
      await this.brandpay.requestPayment(requestPaymentParams)
        .catch((err: any) => {
          console.log("결제 실패 (Redirect)")
          console.dir(err)

          if (err.code === "USER_CANCEL") {
            console.log("사용자 취소")
            let message = `${err.code} : ${err.message}`
            this.makeToast("# 02. 결제창 오픈 (Redirect) : 실패", message, "danger")
          } else {
            console.log("기타 에러 상황", err.code, err.message)
            this.$router.push(`/brandpay-fail?code=${err.code}&message=${err.message}`);
          }
        })
    } else {
      await this.brandpay.requestPayment(requestPaymentParams)
        .then((res: any) => {
          console.log("결제 성공", res)

          // 결제 승인 요청
          res.customerKey = this.customerKey

          this.$router.push(`/payments/brandpay-success?orderId=${res.orderId}&amount=${res.amount.value}&paymentKey=${res.paymentKey}&customerKey=${res.customerKey}`)
        })
        .catch((err: any) => {
          console.log("결제 실패 (Promise)")
          console.dir(err)

          if (err.code === "USER_CANCEL") {
            console.log("사용자 취소")
            let message = `${err.code} : ${err.message}`
            this.makeToast("# 02. 결제창 오픈 (Promise) : 실패", message, "danger")
          } else {
            console.log("기타 에러 상황", err.code, err.message)
            this.$router.push(`/brandpay-fail?code=${err.code}&message=${err.message}`);
          }
        })
    }

  }

  /**
   * brandpay 결제수단 추가
   */
  async addPaymentMethod() {
    console.log("===== 결제수단 추가 =====")
    await this.brandpay.addPaymentMethod()
  }

  /**
   * brandpay 결제 관리 설정창 열기
   */
  async openSettings() {
    console.log("===== 설정창 열기 =====")
    await this.brandpay.openSettings()
  }

  /**
   * brandpay 비밀번호 변경
   */
  async changePassword() {
    console.log("===== 비밀번호 변경 =====")
    await this.brandpay.changePassword()
  }

  /**
   * brandpay 원터치 결제 변경
   */
  async changeOneTouchPay() {
    console.log("===== 원터치 결제 변경 =====")
    await this.brandpay.changeOneTouchPay()
  }

  /**
   * brandpay 원터치 결제 활성화 여부
   */
  async isOneTouchPayEnabled() {
    console.log("===== 원터치 결제 활성화 여부 =====")
    const result = await this.brandpay.isOneTouchPayEnabled()
    console.log(result)
  }

  /**
   * 주문번호 랜덤생성
   * ex) "ORD_" + 랜덤한 16자리 문자열
   */
  changeRandom() {
    this.orderId = "ORD_" + window.btoa(Math.random().toString()).slice(0, 16)
  }

  /**
   * 금액 포맷팅
   * @param value 
   */
  formatCurrency(value: number): string {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " 원";
  }

}
</script>

<style scoped>
.input-group-text {
  min-width: 111px;
}
</style>