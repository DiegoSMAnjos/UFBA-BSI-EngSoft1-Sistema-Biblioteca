package Principal;

import java.time.LocalDate;

public class Emprestimo {

	private IUsuario usuario; 
	private Exemplar exemplar; 
	private boolean isAtivo;
	private LocalDate dataEmprestimo; 
	private LocalDate dataDevolucaoPrevisao;
	private LocalDate dataDevolucaoReal;
	
public Emprestimo(IUsuario usuario, Exemplar exemplar, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevisao) {
		super();
		this.usuario = usuario;
		this.exemplar = exemplar;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucaoPrevisao = dataDevolucaoPrevisao;
		this.isAtivo = true;
	}


public IUsuario getUsuario() {
	return usuario; 
}
public Exemplar getExemplar() {
	return exemplar; 
}
public LocalDate getDataEmprestimo() {
	return dataEmprestimo; 
}
public LocalDate getDataDevolucaoPrevisao() {
	return dataDevolucaoPrevisao;
}
public LocalDate getDataDevolucaoReal() {
	return dataDevolucaoReal; 
}
public boolean getEmprestimoAtivo() {
	return isAtivo; 
}

public void setDataDevolucaoReal(LocalDate data) {
	this.dataDevolucaoReal = data;
}
}
