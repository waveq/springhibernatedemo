package com.waveq.przykladspring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ball")
@NamedQueries({ 
        @NamedQuery(name = "ball.all", query = "Select b from Ball b"),
        @NamedQuery(name = "ball.byId", query = "Select b from Ball b where b.id = :id"),
        @NamedQuery(name = "ball.byMark", query = "Select b from Ball b where b.mark = :mark"),
        @NamedQuery(name = "ball.byType", query = "Select b from Ball b where b.type = :type"),
        @NamedQuery(name = "ball.byColor", query = "Select b from Ball b where b.color = :color"),
        @NamedQuery(name = "ball.byYop", query = "Select b from Ball b where b.yop = :yop")
})
public class Ball {
	
	private Long id;
	
	private String mark = "";	
	private String type = "";
	private String color = "";
	private int yop = 1970;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "mark")
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "color")
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Column(name = "yop")
	public int getYop() {
		return yop;
	}
	public void setYop(int yop) {
		this.yop = yop;
	}
}
