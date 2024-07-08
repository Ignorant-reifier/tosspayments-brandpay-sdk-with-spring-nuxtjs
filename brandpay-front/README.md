## 환경
| 사용 | 버전 |
|----------|---------|
| Node     | 16      |
| Vue      | 2       |
| Vue Property Decorator | 9.1.2 |
| Nuxt     | 2       |

## 토스페이먼츠 SDK v2를 npm 패키지로 설치
```bash
$ npm install @tosspayments/tosspayments-sdk --save
```

## 토스
```javsscript
// 스크립트 태그 연동방식
const tossPayments = TossPayments("test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm");

// 모듈 임포트 연동방식
import { loadTossPayments } from "@tosspayments/tosspayments-sdk"
const tossPayments = await loadTossPayments("test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm");
```

## 참고
| name | url |
|----------|---------|
| Nuxt     | https://v2.nuxt.com      |
| Toss Payments         | https://docs.tosspayments.com/sdk/v2/js |