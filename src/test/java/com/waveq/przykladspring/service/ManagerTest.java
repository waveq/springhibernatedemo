package com.waveq.przykladspring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.waveq.przykladspring.domain.Ball;
import com.waveq.przykladspring.domain.Player;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ManagerTest {
	
	
	@Autowired
    Manager manager;

    private final String MARK_1 = "Adidas";
    private final String TYPE_1 = "Soccer";
    private final String COLOR_1 = "Black";
    private final int YOP_1 = 2013;
    
    private final String MARK_2 = "Nike";
    private final String TYPE_2 = "Football";
    private final String COLOR_2 = "White";
    private final int YOP_2 = 2012;
    
    private final String NAME_1 = "Christiano";
    private final String LASTNAME_1 = "Ronaldo";
    private final int YOB_1 = 1986;
    private final String TEAM_1 = "Real Madrid";
    
    private final String NAME_2 = "Leo";
    private final String LASTNAME_2 = "Messi";
    private final int YOB_2 = 1988;
    private final String TEAM_2 = "FC Barcelona";
    
    @Test
    public void addBallCheck() {
            Ball ball = new Ball();
            ball.setMark(MARK_1);
            ball.setType(TYPE_1);
            ball.setColor(COLOR_1);
            ball.setYop(YOP_1);
            manager.addBall(ball);

            Ball retrievedBall = manager.findBallById(ball);

            assertEquals(MARK_1, retrievedBall.getMark());
            assertEquals(TYPE_1, retrievedBall.getType());
            assertEquals(COLOR_1, retrievedBall.getColor());
            assertEquals(YOP_1, retrievedBall.getYop());     
    }
    
    // Taki sam test jak add
    @Test 
    public void findByIdCheck() {
    	 Ball ball = new Ball();
         ball.setMark(MARK_1);
         ball.setType(TYPE_1);
         ball.setColor(COLOR_1);
         ball.setYop(YOP_1);
         
         manager.addBall(ball);
         
         Ball retrievedBall = manager.findBallById(ball);

         assertEquals(MARK_1, retrievedBall.getMark());
         assertEquals(TYPE_1, retrievedBall.getType());
         assertEquals(COLOR_1, retrievedBall.getColor());
         assertEquals(YOP_1, retrievedBall.getYop());
    }
    @Test 
    public void getAllBallsCheck() {
    	Ball ball1 = new Ball();
        ball1.setMark(MARK_1);
        ball1.setType(TYPE_1);
        ball1.setColor(COLOR_1);
        ball1.setYop(YOP_1);
        
        
        int count = manager.getAllBalls().size();
        manager.addBall(ball1);
        assertEquals(count+1, manager.getAllBalls().size());
        
        Ball ball2 = new Ball();
        ball2.setMark(MARK_2);
        ball2.setType(TYPE_2);
        ball2.setColor(COLOR_2);
        ball2.setYop(YOP_2);
        manager.addBall(ball2);
        assertEquals(count+2, manager.getAllBalls().size());
        
    }
    @Test
    public void deleteBallCheck() {
    	Ball ball1 = new Ball();
        ball1.setMark(MARK_1);
        ball1.setType(TYPE_1);
        ball1.setColor(COLOR_1);
        ball1.setYop(YOP_1);
        
        Ball ball2 = new Ball();
        ball2.setMark(MARK_2);
        ball2.setType(TYPE_2);
        ball2.setColor(COLOR_2);
        ball2.setYop(YOP_2);
        
        // Dodaj ballsy
        manager.addBall(ball1);
        manager.addBall(ball2);
        
        // Sprawdz czy ball1 zostala dodana
        Ball retrievedBall1 = manager.findBallById(ball1);
        assertEquals(MARK_1, retrievedBall1.getMark());
        assertEquals(TYPE_1, retrievedBall1.getType());
        assertEquals(COLOR_1, retrievedBall1.getColor());
        assertEquals(YOP_1, retrievedBall1.getYop());
        
        int count;
        // Zapisz ilosc balls przed usunieciem
        count = manager.getAllBalls().size();
        
        // Usun ball1
        manager.deleteBall(ball1);
        
        // Sprawdz czy usunieta pilka jest w bazie
        assertNull(manager.findBallById(ball1));
               
        //sprawdz czy ilosc balls zmienila sie o 1 po usunieciu
        assertEquals(count-1, manager.getAllBalls().size());
        
        // Sprawdz czy ball2 nie zostala usunieta
        Ball retrievedBall2 = manager.findBallById(ball2);
        assertEquals(MARK_2, retrievedBall2.getMark());
        assertEquals(TYPE_2, retrievedBall2.getType());
        assertEquals(COLOR_2, retrievedBall2.getColor());
        assertEquals(YOP_2, retrievedBall2.getYop());    
    }
    
    @Test
    public void updateBallCheck() {
    	Ball ball1 = new Ball();
        ball1.setMark(MARK_1);
        ball1.setType(TYPE_1);
        ball1.setColor(COLOR_1);
        ball1.setYop(YOP_1);
        
        Ball ball2 = new Ball();
        ball2.setMark(MARK_2);
        ball2.setType(TYPE_2);
        ball2.setColor(COLOR_2);
        ball2.setYop(YOP_2);
        
        // dodaj ballsy
        manager.addBall(ball1);
        manager.addBall(ball2);
        
        // Zapisz liczbe elementow w bazie
        int count;
        count = manager.getAllBalls().size();
        
        // Sprawdz czy dodano ball1
        Ball retrievedBall1 = manager.findBallById(ball1);
        assertEquals(MARK_1, retrievedBall1.getMark());
        assertEquals(TYPE_1, retrievedBall1.getType());
        assertEquals(COLOR_1, retrievedBall1.getColor());
        assertEquals(YOP_1, retrievedBall1.getYop());
        
        // Zapisz id dodanej ball
        Long id = retrievedBall1.getId();
        
        // Przypisz nowe wartosci do ball1
        ball1.setMark(MARK_2);
        ball1.setColor(COLOR_2);
        ball1.setType(TYPE_2);
        ball1.setYop(YOP_2);
        // Uaktualnij ball1
        manager.updateBall(ball1);
        
        // Sprawdz czy ball1 ma wartosci z ball2
        retrievedBall1 = manager.findBallById(ball1);
        assertEquals(MARK_2, retrievedBall1.getMark());
        assertEquals(TYPE_2, retrievedBall1.getType());
        assertEquals(COLOR_2, retrievedBall1.getColor());
        assertEquals(YOP_2, retrievedBall1.getYop());   
        
        //Dodatkowo sprawdz czy id ball1 sie nie zmienilo
        assertEquals(id, retrievedBall1.getId());
        
        // Sprawdz czy ball2 nie zostala zmieniona/usunieta
        Ball retrievedBall2 = manager.findBallById(ball2);
        assertEquals(MARK_2, retrievedBall2.getMark());
        assertEquals(TYPE_2, retrievedBall2.getType());
        assertEquals(COLOR_2, retrievedBall2.getColor());
        assertEquals(YOP_2, retrievedBall2.getYop());    
        
        //sprawdz czy ilosc balls zmienila sie po update
        assertEquals(count, manager.getAllBalls().size());
    }
    
    //#############################PLAYER##################################
    @Test
    public void addPlayerCheck() {
            Player player = new Player();
            player.setName(NAME_1);
            player.setLastName(LASTNAME_1);
            player.setYob(YOB_1);
            player.setTeam(TEAM_1);
            manager.addPlayer(player);

            Player retrievedPlayer = manager.findPlayerById(player);

            assertEquals(NAME_1, retrievedPlayer.getName());
            assertEquals(LASTNAME_1, retrievedPlayer.getLastName());
            assertEquals(YOB_1, retrievedPlayer.getYob());
            assertEquals(TEAM_1, retrievedPlayer.getTeam());     
    }
    
    @Test 
    public void findPlayerByIdCheck() {
    	 Player player = new Player();
         player.setName(NAME_1);
         player.setLastName(LASTNAME_1);
         player.setYob(YOB_1);
         player.setTeam(TEAM_1);
         
         manager.addPlayer(player);
         
         Player retrievedPlayer = manager.findPlayerById(player);

         assertEquals(NAME_1, retrievedPlayer.getName());
         assertEquals(LASTNAME_1, retrievedPlayer.getLastName());
         assertEquals(YOB_1, retrievedPlayer.getYob());
         assertEquals(TEAM_1, retrievedPlayer.getTeam());     
    }
    @Test 
    public void getAllPlayersCheck() {
    	 Player player1 = new Player();
         player1.setName(NAME_1);
         player1.setLastName(LASTNAME_1);
         player1.setYob(YOB_1);
         player1.setTeam(TEAM_1);
        
        int count = manager.getAllPlayers().size();
        manager.addPlayer(player1);
        assertEquals(count+1, manager.getAllPlayers().size());
        
        Player player2 = new Player();
        player2.setName(NAME_2);
        player2.setLastName(LASTNAME_2);
        player2.setYob(YOB_2);
        player2.setTeam(TEAM_2);
        manager.addPlayer(player2);
        assertEquals(count+2, manager.getAllPlayers().size());
    }
    @Test
    public void deletePlayerCheck() {
    	Player player1 = new Player();
    	player1.setName(NAME_1);
    	player1.setLastName(LASTNAME_1);
    	player1.setYob(YOB_1);
    	player1.setTeam(TEAM_1);
    	
        Player player2 = new Player();
        player2.setName(NAME_2);
        player2.setLastName(LASTNAME_2);
        player2.setYob(YOB_2);
        player2.setTeam(TEAM_2);
       
        // Dodaj playerow
        manager.addPlayer(player1);
        manager.addPlayer(player2);
        
        // Sprawdz czy player1 zostal dodany
        Player retrievedPlayer1 = manager.findPlayerById(player1);
        assertEquals(NAME_1, retrievedPlayer1.getName());
        assertEquals(LASTNAME_1, retrievedPlayer1.getLastName());
        assertEquals(YOB_1, retrievedPlayer1.getYob());
        assertEquals(TEAM_1, retrievedPlayer1.getTeam());     
        
        int count;
        // Zapisz ilosc players przed usunieciem
        count = manager.getAllPlayers().size();
        // Usun player1
        manager.deletePlayer(player1);
        
        // Sprawdz czy usuniety player jest w bazie
        assertNull(manager.findPlayerById(player1));
               
        //sprawdz czy ilosc players zmienila sie o 1 po usunieciu
        assertEquals(count-1, manager.getAllPlayers().size());
        
        // Sprawdz czy player2 nie zostal usuniety
        Player retrievedPlayer2 = manager.findPlayerById(player2);
        assertEquals(NAME_2, retrievedPlayer2.getName());
        assertEquals(LASTNAME_2, retrievedPlayer2.getLastName());
        assertEquals(YOB_2, retrievedPlayer2.getYob());
        assertEquals(TEAM_2, retrievedPlayer2.getTeam());        
    }
    
    @Test
    public void updatePlayerCheck() {
    	Player player1 = new Player();
    	player1.setName(NAME_1);
    	player1.setLastName(LASTNAME_1);
    	player1.setYob(YOB_1);
    	player1.setTeam(TEAM_1);
        
        Player player2 = new Player();
        player2.setName(NAME_2);
        player2.setLastName(LASTNAME_2);
        player2.setYob(YOB_2);
        player2.setTeam(TEAM_2);
        
        // dodaj playerow
        manager.addPlayer(player1);
        manager.addPlayer(player2);
        
        // Zapisz liczbe elementow w bazie
        int count;
        count = manager.getAllPlayers().size();
        
        // Sprawdz czy dodano player
        Player retrievedPlayer1 = manager.findPlayerById(player1);
        assertEquals(NAME_1, retrievedPlayer1.getName());
        assertEquals(LASTNAME_1, retrievedPlayer1.getLastName());
        assertEquals(YOB_1, retrievedPlayer1.getYob());
        assertEquals(TEAM_1, retrievedPlayer1.getTeam());     
        
        // Zapisz id dodanej ball
        Long id = retrievedPlayer1.getId();
        
        // Przypisz nowe wartosci do player1
        player1.setName(NAME_2);
        player1.setLastName(LASTNAME_2);
        player1.setYob(YOB_2);
        player1.setTeam(TEAM_2);
        // Uaktualnij player1
        manager.updatePlayer(player1);
        
        // Sprawdz czy player ma wartosci z player
        retrievedPlayer1 = manager.findPlayerById(player1);
        assertEquals(NAME_2, retrievedPlayer1.getName());
        assertEquals(LASTNAME_2, retrievedPlayer1.getLastName());
        assertEquals(YOB_2, retrievedPlayer1.getYob());
        assertEquals(TEAM_2, retrievedPlayer1.getTeam());   
        
        //Dodatkowo sprawdz czy id ball1 sie nie zmienilo
        assertEquals(id, retrievedPlayer1.getId());
        
        // Sprawdz czy player2 nie zostal zmieniony/usuniety
        Player retrievedPlayer2 = manager.findPlayerById(player2);
        assertEquals(NAME_2, retrievedPlayer2.getName());
        assertEquals(LASTNAME_2, retrievedPlayer2.getLastName());
        assertEquals(YOB_2, retrievedPlayer2.getYob());
        assertEquals(TEAM_2, retrievedPlayer2.getTeam()); 
        
        //sprawdz czy ilosc playerow zmienila sie po update
        assertEquals(count, manager.getAllPlayers().size());
    }
    
    @Test
    public void addBallToPlayerCheck() {
    	Ball ball1 = new Ball();
        ball1.setMark(MARK_1);
        ball1.setType(TYPE_1);
        ball1.setColor(COLOR_1);
        ball1.setYop(YOP_1);
        
      	Player player1 = new Player();
    	player1.setName(NAME_1);
    	player1.setLastName(LASTNAME_1);
    	player1.setYob(YOB_1);
    	player1.setTeam(TEAM_1);
    	
    	manager.addPlayer(player1);
        Player retrievedPlayer1 = manager.findPlayerById(player1);
        
        manager.addBall(ball1);
        Ball retrievedBall1 = manager.findBallById(ball1);
        
        manager.addBallToPlayer(retrievedPlayer1, retrievedBall1);
        
        List<Ball> ballsFromPlayer = manager.getBallsFromPlayer(retrievedPlayer1);
        
        assertEquals(1, ballsFromPlayer.size());
        assertEquals(MARK_1, ballsFromPlayer.get(0).getMark());
        assertEquals(TYPE_1, ballsFromPlayer.get(0).getType());
        assertEquals(COLOR_1, ballsFromPlayer.get(0).getColor());
        assertEquals(YOP_1, ballsFromPlayer.get(0).getYop());

    }
    
    @Test
    public void deletePlayerWithAllBallsCheck() {
    	Ball ball1 = new Ball();
        ball1.setMark(MARK_1);
        ball1.setType(TYPE_1);
        ball1.setColor(COLOR_1);
        ball1.setYop(YOP_1);
        
        Ball ball2 = new Ball();
        ball2.setMark(MARK_2);
        ball2.setType(TYPE_2);
        ball2.setColor(COLOR_2);
        ball2.setYop(YOP_2);
        
      	Player player1 = new Player();
    	player1.setName(NAME_1);
    	player1.setLastName(LASTNAME_1);
    	player1.setYob(YOB_1);
    	player1.setTeam(TEAM_1);
    	
    	manager.addPlayer(player1);
    	manager.addBall(ball1);
    	manager.addBall(ball2);
    	
        Player retrievedPlayer1 = manager.findPlayerById(player1);
        assertEquals(NAME_1, retrievedPlayer1.getName());
        assertEquals(LASTNAME_1, retrievedPlayer1.getLastName());
        assertEquals(YOB_1, retrievedPlayer1.getYob());
        assertEquals(TEAM_1, retrievedPlayer1.getTeam()); 
    	
    	manager.addBallToPlayer(player1, ball1);
    	manager.addBallToPlayer(player1, ball2);
    	
    	// Sprawdz czy dodano pilki do gracza
    	List<Ball> ballsFromPlayer = manager.getBallsFromPlayer(player1);
    	
    	assertEquals(2, ballsFromPlayer.size());
    	
        int countBall;
        // Zapisz ilosc balls przed usunieciem
        countBall = manager.getAllBalls().size();
    	
    	manager.deletePlayerWithAllBalls(player1);
        
        
        // Sprawdz czy usuniety player jest w bazie
        assertEquals(countBall-2,manager.getAllBalls().size());
        
        // Sprawdz czy nie da sie pobrac usunietych pilek
        assertNull(manager.findBallById(ball1));
        assertNull(manager.findBallById(ball2));         
    }
    
}
