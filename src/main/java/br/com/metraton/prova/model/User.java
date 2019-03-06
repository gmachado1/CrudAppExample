package br.com.metraton.prova.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "usuario")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int id;
	@Column(name = "nome")
	private String name;
	@Column(name = "id_cargo")
	private int roleId;
	
	//@OneToOne(cascade = CascadeType.ALL)
	//@Embedded
	@Transient
	private String phone;
	@Transient
	private String role;
	@Transient
	private Phone phoneEntity;
	@Transient
	private Role roleEntity;
	

	public User(String name, String role, String phone) {
		this.name = name;
		this.role = role;
		this.phone = phone;

	}

	public User() {
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", role=" + role + ", phone=" + phone + ",roleEntity="+ roleEntity+", phoneEntity"+phoneEntity+" ]";
	}

	public Role getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(Role roleEntity) {
		this.roleEntity = roleEntity;
	}

	public Phone getPhoneEntity() {
		return phoneEntity;
	}

	public void setPhoneEntity(Phone phoneEntity) {
		this.phoneEntity = phoneEntity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
