package model.entities;

import java.util.ArrayList;
import java.util.List;

import pattern.observer.Observer;
import pattern.observer.Subject;

public class Livro implements Subject {
	private String codigo;
	private String titulo;
	private String editora;
	private String autor;
	private String edicao;
	private String anoPublicacao;
	private List<Exemplar> listaExemplares;
	private List<Observer> observadores;
	private int reservasSimultaneas = 0;

	public Livro(String codigo, String titulo, String editora, String autor, String edicao, String anopublicacao) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.editora = editora;
		this.autor = autor;
		this.edicao = edicao;
		this.anoPublicacao = anopublicacao;
		this.listaExemplares = new ArrayList<>();
		this.observadores = new ArrayList<>();
	}

	public String getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getEditora() {
		return editora;
	}

	public String getAutor() {
		return autor;
	}

	public String getEdicao() {
		return edicao;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public List<Exemplar> getListaExemplares() {
		return listaExemplares;
	}
	
	public List<Observer> getObservadores() {
		return observadores;
	}

	public int getReservasSimultaneas() {
		return reservasSimultaneas;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public void setListaExemplares(List<Exemplar> listaExemplares) {
		this.listaExemplares = listaExemplares;
	}
	
	public void addReservasSimultaneas() {
		this.reservasSimultaneas++;
	}
	
	public void removeReservasSimultaneas() {
		this.reservasSimultaneas--;
	}
	
    public void verificarReservasSimultaneas() {
        if (reservasSimultaneas > 2) {
        	notifyObserver();
        }
    }

	@Override
	public void addObserver(Observer observador) {
		observadores.add(observador);
	}

	@Override
	public void removeObserver(Observer observador) {
		observadores.remove(observador);
	}

	@Override
	public void notifyObserver() {
		for (Observer observador : observadores) {
            observador.update();
        }
		
	}




}
