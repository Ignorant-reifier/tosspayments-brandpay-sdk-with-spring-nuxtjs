export interface RequestPaymentParams {
  amount: {
    currency: string
    value: number
  }
  orderId: string
  orderName: string
  customerName: string
  customerEmail: string
  successUrl?: string
  failUrl?: string
}