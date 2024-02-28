package projeto;

public class Cliente {
	private static int contCliente = 0;
        
	private int id;
	private int prioridadeNormal;
	private int prioridadeEspecial;
	private int numeroDeTarefas;
	private int ordem;
	private Estado estado;
	private int contEntradas;
	private int contSaidas;
	

	public Cliente(int prioridade, int numeroDeTarefas) {
		Cliente.contCliente++;
	
		id = contCliente;
		prioridadeNormal = 0;
		contEntradas = 1;
		contSaidas = 0;
		ordem = 0;
		estado = Estado.ESPERANDO;
		
		this.numeroDeTarefas = numeroDeTarefas;
		
		if(prioridade >= 6 && prioridade <= 10)
			prioridadeEspecial = prioridade;
		else
			prioridadeEspecial = 0;
	}
	
	public void incrementarPrioridadeNormal() {
		if(prioridadeNormal < 6)
			prioridadeNormal++;
	}
	
	public boolean finalizarAtendimento() {
		if(numeroDeTarefas > 1)
			numeroDeTarefas = numeroDeTarefas - 2;
		else
			numeroDeTarefas = numeroDeTarefas - 1;
		
		if(numeroDeTarefas > 0) {
			contSaidas++;
			contEntradas++;
			estado = Estado.ESPERANDO;
			return false;
		}
		else {
			contSaidas++;
			estado = Estado.CONCLUIDO;
			ordem = 0;
			return true;
		}
	}
	
	public String infoNAFila() {
		return "  ID: " + id + " | Ordem: " + ordem + "º | Estado: " + estado + " | Tarefas: " + numeroDeTarefas + " | Prioridade Especial: " + prioridadeEspecial + " | Prioridade Normal: " + prioridadeNormal + " | Entradas: " + contEntradas + " | Saidas: " + contSaidas;
	}
	
	public String infoForaDaFila() {
		return "  ID: " + id + " | Estado: " + estado + " | Tarefas: " + numeroDeTarefas + " | Entradas: " + contEntradas + " | Saidas: " + contSaidas;
	}
	
	public int getPrioridadeEspecial() {
		return prioridadeEspecial;
	}

	public int getNumeroDeTarefas() {
		return numeroDeTarefas;
	}
	
	public void atualizarEstadoParaExecutando() {
    	estado = Estado.EXECUTAND;
    }
	
	public void atualizarEstadoParaEsperando() {
    	estado = Estado.ESPERANDO;
    }

	public int getPrioridadeNormal() {
		return prioridadeNormal;
	}
	
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
}
