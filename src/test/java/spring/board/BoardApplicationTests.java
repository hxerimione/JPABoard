package spring.board;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class BoardApplicationTests {
/*
	@Autowired BoardRepository boardRepository;
	@Autowired EntityManager em;
	@Test
	@Rollback(value = false)
	public void test(){

		Member member1 = new Member();
		member1.setUsername("member1");
		em.persist(member1);


		Board board = new Board();
		board.setTitle("happy");
		board.setContent("hello hi");
		board.setMember(member1);

		Board newBoard = Board.createBoard(member1);
		em.persist(newBoard);
		//em.persist(board);

		em.flush();
		em.clear();

		Board findBoard = em.find(Board.class, newBoard.getId());

		Member member = findBoard.getMember();

		System.out.println("member = " + member.getUsername());



	}
*/
}
