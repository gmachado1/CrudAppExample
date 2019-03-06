package br.com.metraton.prova.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "telefone")
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_telefone")
	private Integer id;
	
	@Column(name = "ddd")
	private Integer ddd;

	@Column(name = "numero")
	private Integer number;

	@Column(name = "id_usuario")
	private int userId;

	public Phone(Integer id,Integer ddd, Integer number, int userId) {
		this.id = id;
		this.ddd = ddd;
		this.number = number;
		this.userId = userId;
	}

	public Phone() {

	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", number=" + number + ", user=" + userId + "]";
	}

}
