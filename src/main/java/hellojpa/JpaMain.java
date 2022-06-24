package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            // 최초에는 1차 캐시에 아무것도 없기 때문에 select 문이 날라감
//            // 이후에는 안날라가는 것 확인
//
//            Member member1 = em.find(Member.class, 101L);
//            Member member2 = em.find(Member.class, 101L);
//
//            // 동일성 보장 (true)
//            System.out.println(member1 == member2);


//            // 쓰기 지연
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("=====================");
//
//            // 위 프린트문이 찍힌 다음 DB 쿼리가 날라가는것을 확인할 수 있음.
//
//            tx.commit();

            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZ");

            System.out.println("========");

            // persist 없이도 setter 통해서만 커밋 시점에 update 쿼리 날라가는 것 확인
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
