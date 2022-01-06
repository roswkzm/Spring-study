package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){

        //return new MemoryMemberRepository(); //메모리에 저장하는 코드
        //return new JdbcMemberRepository(dataSource); //순수 하코딩 jdbc에 관련된 부분
        //return new JdbcTemplateMemberRepository(dataSource);    // jdbc템플릿을 통한 db접근 방법
        return new JpaMemberRepository(em);   // Jpa를 통한 db접근 방법
    }
}
