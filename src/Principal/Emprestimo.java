package Principal;

import java.time.LocalDate;

public class Emprestimo {

	private IUsuario usuario; 
	private Livro livro; 
	private boolean emprestimoAtivo;
	private LocalDate dataEmprestimo; 
	private LocalDate dataDevolucaoPrevisao;
	private LocalDate dataDevolucaoReal;
	
public Emprestimo(IUsuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevisao) {
		super();
		this.usuario = usuario;
		this.livro = livro;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucaoPrevisao = dataDevolucaoPrevisao;
		this.emprestimoAtivo = true; 
	}


public IUsuario getUsuario() {
	return usuario; 
}
public Livro getLivro() {
	return livro; 
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
	return emprestimoAtivo; 
}

public void setDataDevolucaoReal(LocalDate data) {
	this.dataDevolucaoReal = data;
}
}
