### s3 bucket 연결 + 파일 다운로드

- credentials > use-default-aws-credentials-chain: true 설정필요
    - ~~profileProvider가 없다고 에러 남~~
    - accessKey 랑 secretKey 못 불러와서 발생했던 문제. 

- cloud.aws.stack.auto = false
    - cloudFormation 설정 off // null은 true로 인식.
    
- creadential 관련 설정을 별도 yml파일에 넣고 돌릴려고 했는데, 인식이 안 됨 
    - application.yml에 넣어야지만 정상동작 하는데 이건 뭔경우인가?
    
    
- ~~yaml 파일을 여러개로 분리해서 관리할 경우, 하이라키 구조를 제대로 인식 못하는것 같다.~~    
    - @PropertySource를 통해서 yml 파일들을 불러왔더니, 데이터가 다 분리되어 저장됨
    그래서 해당 값을 찾지 못함. .properties 포맷이랑 동일하게 작성해줘야 함