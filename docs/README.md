check new branch

# version 1
- 흐름만 파악하기 위한 개발 진행
- 예외처리와 동작여부를 다시 확인
- 개발 조건을 만족하지 못함

# version 2
### 작동 흐름
- MC : 구입금액을 입력해 주세요.
- user : [값 입력] 

- MC : [구입금액/1000]개를 구매했습니다.
- 구매 개수 만큼 로또 발행 및 번호 출력
  - 로또 번호는 1~45
  - 중복 되지 않는 6개의 숫자 

- MC : 당첨 번호를 입력해 주세요
- user : [6개의 값 입력]
  - 로또 번호는 1~45 
  - 중복 되지 않는 6개의 숫자
  - 입력 값이 오류일 경우 > IllegalArgumentException 발생 > ERROR 시작하는 에러 메시지 출력 후 다시 입력 받기


### 개발 흐름
1. 로또 Class
- 6개의 값 랜덤선택
- 예외 처리
  - 구간 내 번호
  - 중복 되지 않는 값
2. 게임 Class
- MC 말 출력 함수
- 사용자 값 받는 함수
- 수익률 함수

## (Class) Game
- money(int,구입 금액), count(int, 로또 개수), buy(List<Lotto>,로또 번호), answer(List<Integer>, 당첨 번호+보너스 번호)
- start()
    - input : null / return : void
    - 구입 금액을 받고 Int 변환 후 Game class의 money 값으로 지정
- generate()
    - input : null / return : void
    - 로또 개수(count)만큼 로또 생성 후 buy List에 add