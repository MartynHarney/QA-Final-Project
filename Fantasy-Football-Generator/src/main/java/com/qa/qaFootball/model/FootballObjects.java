package com.qa.qaFootball.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class FootballObjects {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(nullable = false, length = 20)
	private long id;
	@Column(nullable = false, length = 20)
	private String name;
	@Column(nullable = false)
	private int price;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false)
	private boolean starPlayer;
	
	
	public FootballObjects() {
		super();
	}


	public FootballObjects(long id, String name, int price, int age, boolean starPlayer) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.age = age;
		this.starPlayer = starPlayer;
	}


	public FootballObjects(String name, int price, int age, boolean starPlayer) {
		super();
		this.name = name;
		this.price = price;
		this.age = age;
		this.starPlayer = starPlayer;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public boolean isStarPlayer() {
		return starPlayer;
	}


	public void setStarPlayer(boolean starPlayer) {
		this.starPlayer = starPlayer;
	}


	@Override
	public int hashCode() {
		return Objects.hash(age, id, name, price, starPlayer);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FootballObjects other = (FootballObjects) obj;
		return age == other.age && id == other.id && Objects.equals(name, other.name) && price == other.price
				&& starPlayer == other.starPlayer;
	}


	@Override
	public String toString() {
		return "FootballObjects [id=" + id + ", name=" + name + ", price=" + price + ", age=" + age + ", starPlayer="
				+ starPlayer + "]";
	}
	
	

}
