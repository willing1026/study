- apache commons-net
- ftp 접속 > 임시폴더 생성 > 파일 다운 > 연결해제
- ftp접속정보, 임시폴더정보는 application.properties에 작성해서 사용
    - ex> `ftp.test.hostname=127.0.0.1`
    
- FTPServiceTest 실행해서 기능 확인

- `retrieveFileStream` 사용해서 여러 파일을 다운받는 경우, 마지막으로 `completePendingCommand` 메서드를 통해 작업을 종료해줘야한다.
```
retrieveFileStream 메서드에 달린 설명.

* <b>To finalize the file transfer you must call
* {@link #completePendingCommand  completePendingCommand } and
* check its return value to verify success.</b>
```
`completePendingCommand`를 안 할 경우 해당 `inputStream`이 계속 열려있어, 다음 파일에 대한 inputStream을 얻지 못하는 문제를 경험했다.

- disConnect를 하게되면 해당 inputStream이 닫히지만, 로그인 한 상태에서 여러 파일을 받기 위해선 필요하다.
- inputStream이 null인 경우 completePendingCommand를 수행하게 되면 무한대기에 빠짐