<template>
  <div>
    <div class="row justify-content-center mt-5">
      <img width="100px" src="https://static.toss.im/illusts/check-blue-spot-ending-frame.png" />
    </div>
    <div class="row justify-content-center mt-1">
      <h2>결제를 완료했어요</h2>
    </div>

    <div>

      <div class="row justify-content-center mt-2">
        <div class="col-3 text-left">
          <strong>주문번호</strong>
        </div>
        <div class="col-3 text-right">
          <p>{{ this.$route.query?.orderId }}</p>
        </div>
      </div>

      <div class="row justify-content-center">
        <div class="col-3 text-left">
          <strong>결제금액</strong>
        </div>
        <div class="col-3 text-right">
          <p>{{ this.$route.query?.amount }}</p>
        </div>
      </div>

      <div class="row justify-content-center">
        <div class="col-3 text-left">
          <strong>paymentKey</strong>
        </div>
        <div class="col-3 text-right">
          <p>{{ this.$route.query?.paymentKey }}</p>
        </div>
      </div>

      <div class="row justify-content-center">
        <div class="col-3 text-left">
          <strong>customerKey</strong>
        </div>
        <div class="col-3 text-right">
          <p>{{ this.$route.query?.customerKey }}</p>
        </div>
      </div>

    </div>

    <div>
      <b>Response Data :</b>
      <div>
        <pre>{{ formattedResponseData }}</pre>
      </div>
    </div>

  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'

@Component({
    components: {},
})
export default class BrandpaySuccess extends Vue {

    responseData: any = {}

    mounted() {
      console.log('success')

      this.loadData()
    }

    async loadData() {

      console.log("this.$route.query", this.$route.query)

      if (this.$route.query) {
        const requestData = {
          orderId: this.$route.query?.orderId,
          amount: this.$route.query?.amount,
          paymentKey: this.$route.query?.paymentKey,
          customerKey: this.$route.query?.customerKey,
        }

        console.log("requestData", requestData)

        await this.$axios.post('/api/payments/brandpay-confirm', requestData).then((res) => {
          console.log(res.data)
          this.responseData = res.data
        }).catch((err) => {
          console.log(err)
          this.$router.push(`/brandpay-fail?code=${err.code}&message=${err.message}`)
        })
      }
      
    }

    get formattedResponseData(): string {
      console.log("formattedResponseData 1", this.responseData)
      console.log("formattedResponseData 2", JSON.stringify(this.responseData, null, 4))
      return this.responseData ? JSON.stringify(this.responseData, null, 4) : ''
    }

}
</script>

<style scoped></style>