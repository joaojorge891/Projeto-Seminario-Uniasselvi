package br.com.uniasselvi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "participantes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Participante {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Tecnologia> tecnologias;
    
    @NotEmpty(message = "Campo nome não pode ser vazio")
    @Column(nullable = false, length = 100)
    private String nome;

    @Email(message = "Email inválido")
    @NotEmpty(message = "Campo email não pode ser vazio")
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @NotEmpty(message = "Campo cpf não pode ser vazio")
    @Column(nullable = false, length = 15)
    private String cpf;
    
    @NotEmpty(message = "Campo cep não pode ser vazio")
    @Column(nullable = false, length = 10)
    private String cep;
    
    @NotEmpty(message = "Campo logradouro não pode ser vazio")
    @Column(nullable = false, length = 200)
    private String logradouro;
    
    @NotEmpty(message = "Campo bairro não pode ser vazio")
    @Column(nullable = false, length = 100)
    private String bairro;
    
    @NotEmpty(message = "Campo localidade não pode ser vazio")
    @Column(nullable = false, length = 100)
    private String localidade;
    
    @NotEmpty(message = "Campo uf não pode ser vazio")
    @Column(nullable = false, length = 2)
    private String uf;
    
    @NotEmpty(message = "Campo area de especialidade não pode ser vazio")
    @Column(nullable = false, length = 20)
    private String especialidade;
    
    @NotEmpty(message = "Campo senioridade não pode ser vazio")
    @Column(nullable = false, length = 20)
    private String senioridade;
              
    @Column(length = 300)
    private String comentarios;

	@Override
	public String toString() {
		return "Participante [id=" + id + ", tecnologias=" + tecnologias + ", nome=" + nome + ", email=" + email
				+ ", cpf=" + cpf + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro
				+ ", localidade=" + localidade + ", uf=" + uf + ", especialidade=" + especialidade + ", senioridade="
				+ senioridade + ", comentarios=" + comentarios + "]";
	}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public List<Tecnologia> getTecnologias() {
//		return tecnologias;
//	}
//
//	public void setTecnologias(ArrayList<Tecnologia> tecnologias) {
//		if (tecnologias == null) {
//            throw new IllegalArgumentException("Tecnologias não pode ser nulo.");
//        }
//		this.tecnologias = tecnologias;
//	}
//	
//	
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getCpf() {
//		return cpf;
//	}
//
//	public void setCpf(String cpf) {
//		this.cpf = cpf;
//	}
//
//	public String getCep() {
//		return cep;
//	}
//
//	public void setCep(String cep) {
//		this.cep = cep;
//	}
//
//	public String getLogradouro() {
//		return logradouro;
//	}
//
//	public void setLogradouro(String logradouro) {
//		this.logradouro = logradouro;
//	}
//
//	public String getBairro() {
//		return bairro;
//	}
//
//	public void setBairro(String bairro) {
//		this.bairro = bairro;
//	}
//
//	public String getLocalidade() {
//		return localidade;
//	}
//
//	public void setLocalidade(String localidade) {
//		this.localidade = localidade;
//	}
//
//	public String getUf() {
//		return uf;
//	}
//
//	public void setUf(String uf) {
//		this.uf = uf;
//	}
//
//	public String getEspecialidade() {
//		return especialidade;
//	}
//
//	public void setEspecialidade(String especialidade) {
//		this.especialidade = especialidade;
//	}
//
//	public String getSenioridade() {
//		return senioridade;
//	}
//
//	public void setSenioridade(String senioridade) {
//		this.senioridade = senioridade;
//	}
//
//	public String getComentarios() {
//		return comentarios;
//	}
//
//	public void setComentarios(String comentarios) {
//		this.comentarios = comentarios;
//	}
  
    
   
}
