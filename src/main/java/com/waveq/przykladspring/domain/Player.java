package com.waveq.przykladspring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Entity
@Table(name = "player")
@NamedQueries({ 
    @NamedQuery(name = "player.all", query = "Select p from Player p"),
    @NamedQuery(name = "player.byId", query = "Select p from Player p where p.id = :id"),
    @NamedQuery(name = "player.byName", query = "Select p from Player p where p.name = :name"),
    @NamedQuery(name = "player.byLastName", query = "Select p from Player p where p.lastName = :lastNAme"),
    @NamedQuery(name = "player.byYob", query = "Select p from Player p where p.yob = :yob"),
    @NamedQuery(name = "player.byTeam", query = "Select p from Player p where p.team = :team"),
})
public class Player {
	
	private Long id;
	
	private String name;
	private String lastName;
	private int yob;
	private String team;
	private List<Ball> balls = new ArrayList<Ball>();
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name = "yob")
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}
	@Column(name = "team")
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Ball> getBalls() {
            return balls;
    }
	 public void setBalls(List<Ball> balls) {
         this.balls = balls;
 }

}
