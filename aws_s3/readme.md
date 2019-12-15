### s3 bucket 연결 + 파일 다운로드

- spring-cloud-aws-autoconfigure에서 자동으로 설정 잡아줌
- 별도의 Configuration 클래스 작성할 필요 없이, application property에 필요한 정보들만 등록.

- credentials > ~~use-default-aws-credentials-chain: true 설정필요~~
    - ~~profileProvider가 없다고 에러 남~~
    - accessKey 랑 secretKey 못 불러와서 발생했던 문제. 

- cloud.aws.stack.auto = false
    - cloudFormation 설정 off // null은 true로 인식.
    
- creadential 관련 설정을 별도 yml파일에 넣고 돌릴려고 했는데, 인식이 안 됨 
    - application.yml에 넣어야지만 정상동작 하는데 이건 뭔경우인가?
    
- ~~yaml 파일을 여러개로 분리해서 관리할 경우, 하이라키 구조를 제대로 인식 못하는것 같다.~~    
    - @PropertySource를 통해서 yml 파일들을 불러왔더니, 데이터가 다 분리되어 저장됨
    그래서 해당 값을 찾지 못함. .properties 포맷이랑 동일하게 작성해줘야 함
    
- sdk 1.x 랑 2.x의 차이 확인해볼 필요 있음. [2.x가이드](https://github.com/awsdocs/aws-java-developer-guide-v2)
