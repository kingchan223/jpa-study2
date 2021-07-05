package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

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

        try{
            //Order테이블에서 주문한 멤버를 찾기위해서는
            // 1.Order테이블에서 해당 order객체를 조회한다.
            Order order = em.find(Order.class, 1L);
            // 2.order객체 에서 멤버id를 찾는다.
            Long memberId = order.getMemberId();
            // 3.Member테이블에서 id로 멤버를 찾아온다.
            Member member = em.find(Member.class, memberId);
            // --> 객체지향적인 코드가 아님. 관계향 디비에 맞춘 설계

//            Order order = em.find(Order.class, 1L);
//            Member findMember = order.getMember();


            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }
}
