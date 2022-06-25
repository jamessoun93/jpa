# JPA: 엔티티 매핑 (Entity Mapping)

## 객체와 테이블 매핑 (@Entity, @Table)

### @Entity

- @Entity가 붙은 클래스는 JPA가 관리하는 엔티티임.
- JPA를 사용해서 테이블과 매핑할 클래는 @Entity 필수
- 주의할 점
  - 기본 생성자 필수 (파라미터가 없는 public 또는 protected 생성자)
  - final 클래스, enum, interface, inner 클래스는 사용 불가
  - DB에 저장할 필드에 final 사용 불가
- name 속성
  - JPA에서 사용할 엔티티의 이름을 지정
  - 기본값을 클래명 그대로이고 이름 충돌이 없으면 그냥 기본값 그대로 사용

### @Table

- 엔티티와 매핑할 테이블 지정

## 데이터베이스 스키마 자동 생성

## 필드와 컬럼 매핑

- @Column: 컬럼 매핑
  - 속성
    - name: 해당 필드와 매핑할 컬럼 이름
      ```java
      @Column(name = "name")
      private String username;
      ```
    - insertable, updatable: 등록, 변경 가능 여부 (기본값 true)
      ```java
      @Column(name = "name", updatable = false)
      private String username;
      ```
      위와 같이 해놓으면 해당 컬럼 값은 변경되지 않음 (DB에서 강제로 하는게 아니라면)
    - nullable: null 값의 허용 여부
      ```java
      @Column(name = "name", nullable = false)
      private String username;
      ```
    - unique: 특정 걸럼에 unique 제약조건을 걸 때 사
      ```java
      @Column(name = "name", unique = true)
      private String username;
      ```
      하지만 unique constraint의 이름을 랜덤으로 만들어줘서 사용하지 않고, `@Table(uniqueConstraints = ...)`를 사용함.
    - columnDefinition: DB 컬럼 정보를 직접 줄 수 있음.
    - length: 문자 길이 제약조건, String 타입에만 사용.
    - precision, scale: 아주 큰 숫자에서 소숫점 자리수 지정할 때 사용, 정밀한 소수를 다룰 때 사용
      ```java
      @Column(precision = 19, scale = ...)
      private BigDecimal price;
      ```
- @Enumerated: enum 타입 매핑할 때 사용
  - EnumType.ORDINAL: enum 순서를 그대로 DB에 저장 (기본값)
  - EnumType.STRING: enum 이름을 DB에 저장
  - EnumType.ORDINAL 사용하면 안됨. 꼭 STRING을 사용할 것.
  - enum 클래스에 가장 앞부분에 새로운 타입을 추가할 경우 순서 그대로 0번이 되어 저장되어 진다. (해결할 수 없는 버그가 된다는 뜻)
- @Temporal: 날짜/시간(java.util.Date, java.util.Calendar) 관련 필드 매핑할 때 사용
  - 사실 이건 이제 안사용해도 됨.
  - 자바8 부터 제공되는 `LocalDate`와 `LocalDateTime` 사용하면 생략가능
- @Lob: VARCHAR를 넘어가는 길이의 데이터를 저장할때 (clob, blob)
  - Lob 해놓고 String 타입으로 선언하면 기본적으로 clob으로 생성됨.
  - 나머지는 blob으로 매핑됨.
- @Transient: 매핑을 안하고 싶은 컬럼 정의할 때 사용
  - DB 저장 X, 조회 X
  - 주로 메모리상에서만 임시로 값을 보관하고 싶은 경우 사용

```java
@Entity
public class Member {

    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;
}
```

위처럼 엔티티를 정의하면 아래와 같이 만들어짐.

```sql
create table Member (
       id bigint not null,
        age integer,
        createdDate timestamp,
        description clob,
        lastModifiedDate timestamp,
        roleType varchar(255),
        name varchar(255),
        primary key (id)
    )
```

## 기본 키 매핑 (@Id)

## 연관관계 매핑 (@ManyToOne, @JoinColumn)
