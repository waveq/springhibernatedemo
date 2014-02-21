package com.waveq.przykladspring.service;

import com.waveq.przykladspring.domain.Ball;
import com.waveq.przykladspring.domain.Player;
import java.util.List;

public interface Manager {
	
	void addBall(Ball ball);
    List<Ball> getAllBalls();
    void deleteBall(Ball ball);
    Ball findBallById(Ball ball);
    Ball findBallByMark(Ball ball);
    Ball findBallByType(Ball ball);
    void updateBall(Ball ball);
    
    void addPlayer(Player player);
    List<Player> getAllPlayers();
    void deletePlayer(Player player);
    Player findPlayerById(Player player);
    Player findPlayerByName(Player player);
    void updatePlayer(Player player);
    
    List<Ball> getBallsFromPlayer(Player player);
    void addBallToPlayer(Player player, Ball ball);
    
    void deletePlayerWithAllBalls(Player player);
    


    
    
}
