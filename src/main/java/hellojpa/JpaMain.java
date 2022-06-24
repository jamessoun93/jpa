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
            // 1차 캐시에 저장되는 것 확인용

            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속
            System.out.println("=== BEFORE ===");
            em.persist(member); // 1차 캐시에 저장
            System.out.println("=== AFTER ===");

            // 1차 캐시로부터 가져옴 (select 문이 안나감)
            Member foundMember = em.find(Member.class, 101L);

            System.out.println("foundMember.id = " + foundMember.getId());
            System.out.println("foundMember.name = " + foundMember.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
