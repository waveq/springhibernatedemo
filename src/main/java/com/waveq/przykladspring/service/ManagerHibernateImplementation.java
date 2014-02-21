package com.waveq.przykladspring.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.waveq.przykladspring.domain.Ball;
import com.waveq.przykladspring.domain.Player;

@Component
@Transactional
public class ManagerHibernateImplementation implements Manager {
	
	 @Autowired
     private SessionFactory sessionFactory;
	 
	 public SessionFactory getSessionFactory() {
         return sessionFactory;
	 }

	 public void setSessionFactory(SessionFactory sessionFactory) {
         this.sessionFactory = sessionFactory;
 	}

	public void addBall(Ball ball) {
		 ball.setId(null);
         sessionFactory.getCurrentSession().persist(ball);
	}	
	
    @SuppressWarnings("unchecked")
	public List<Ball> getAllBalls() {
		return sessionFactory.getCurrentSession().getNamedQuery("ball.all").list();
	}

    public void deleteBall(Ball ball) {
		sessionFactory.getCurrentSession().delete(ball);
	}

	public Ball findBallById(Ball ball) {
		return (Ball) sessionFactory.getCurrentSession().
				getNamedQuery("ball.byId").setLong("id", ball.getId()).uniqueResult();
	}
	
	public Ball findBallByMark(Ball ball) {
		return (Ball) sessionFactory.getCurrentSession().
				getNamedQuery("ball.byMark").setString("mark", ball.getMark()).uniqueResult();
	}
	
	public Ball findBallByType(Ball ball) {
		return (Ball) sessionFactory.getCurrentSession().
				getNamedQuery("ball.byType").setString("type", ball.getType()).uniqueResult();
	}
	
	public void updateBall(Ball ball) {
		sessionFactory.getCurrentSession().update(ball);
	}

	@Override
	public void addPlayer(Player player) {
		 player.setId(null);
         sessionFactory.getCurrentSession().persist(player);
	}

	@Override
	public Player findPlayerById(Player player) {
		return (Player) sessionFactory.getCurrentSession().
				getNamedQuery("player.byId").setLong("id", player.getId()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Player> getAllPlayers() {
		return sessionFactory.getCurrentSession().getNamedQuery("player.all").list();
	}

	@Override
	public void deletePlayer(Player player) {
		sessionFactory.getCurrentSession().delete(player);	
	}

	@Override
	public void updatePlayer(Player player) {
		sessionFactory.getCurrentSession().update(player);	
	}
	
	@Override
	public List<Ball> getBallsFromPlayer(Player player) {
              List<Ball> balls = new ArrayList<Ball>(player.getBalls());
              return balls;
      }
	
	public void addBallToPlayer(Player player, Ball ball) {
		player.getBalls().add(ball);
	}

	@Override
	public Player findPlayerByName(Player player) {
		return (Player) sessionFactory.getCurrentSession().
				getNamedQuery("player.byName").setString("name", player.getName()).uniqueResult();
	}

	@Override
	public void deletePlayerWithAllBalls(Player player) {
		List<Ball> ballsFromPlayer = getBallsFromPlayer(player);
		
		for(int i=0;i<ballsFromPlayer.size();i++) {
			sessionFactory.getCurrentSession().delete(ballsFromPlayer.get(i));
		}
		player.getBalls().clear();
	}
}
