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
- 

## 데이터베이스 스키마 자동 생성

## 필드와 컬럼 매핑 (@Column)

## 기본 키 매핑 (@Id)

## 연관관계 매핑 (@ManyToOne, @JoinColumn)