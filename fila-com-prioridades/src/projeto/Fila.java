package projeto;

public class Fila {
    private List<Cliente> filaClientes;
    private List<Cliente> clientesAtendidos;

    public Fila() {
        filaClientes = new ArrayList<>();
        clientesAtendidos = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        if (filaClientes.size() < 2) {
            filaClientes.add(cliente);
        } 
        else {
            int i = filaClientes.size() - 1;
            while(i > 1 && (cliente.getPrioridadeEspecial() > filaClientes.get(i).getPrioridadeEspecial() || (cliente.getPrioridadeEspecial() == filaClientes.get(i).getPrioridadeEspecial() && cliente.getPrioridadeNormal() > filaClientes.get(i).getPrioridadeNormal()))) {
                i--;
            }
            filaClientes.add(cliente, i+1);
        }
        
        if(filaClientes.size() == 1)
        	cliente.atualizarEstadoParaExecutando();
        
        if(filaClientes.size() > 1)
        	filaClientes.get(filaClientes.size() - 1).setOrdem(filaClientes.size() - 1);
    }

    public Cliente atenderCliente() {
        if (!filaClientes.isEmpty()) {
        	Cliente auxiliar = filaClientes.get(0);
        	
        	if(!filaClientes.get(0).finalizarAtendimento())
        		adicionarCliente(auxiliar);
        	else
                    clientesAtendidos.add(auxiliar);
       
        	atualizarPrioridadeNormalDosClientes();
        	
        	if(filaClientes.size() > 1)
        		filaClientes.get(1).atualizarEstadoParaExecutando();
        	atualizarOrdemDosClientesDaFila();
            return filaClientes.remove(0);
        } 
        else {
            throw new ArrayIndexOutOfBoundsException("Fila está vazia");
        }
    }

    public boolean estaVazia() {
        return filaClientes.isEmpty();
    }
    
    public boolean naoTemClientesAtendidos() {
    	return clientesAtendidos.isEmpty();
    }
    
    public void mostrarEstadoDaFila() {
    	int numClientes = filaClientes.size();
        
    	System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------|");
    	System.out.println("- Atendendo:");
    	System.out.println(filaClientes.get(0).infoForaDaFila());
    	if(numClientes > 1) {
    		System.out.println("- Fila:");
    		for(int i = 1; i < numClientes; i++) {
    			System.out.println(filaClientes.get(i).infoNAFila());
    		}
    	}
    	mostrarClientesAtendidos();
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------|\n");
    }
    
    public void mostrarClientesAtendidos() {
    	int numClientesAtendidos = clientesAtendidos.size();
    	if(numClientesAtendidos > 0) {
    		System.out.println("- Atendidos:");
    		for(int i = 0; i < numClientesAtendidos; i++) {
    			System.out.println(clientesAtendidos.get(i).infoForaDaFila());
    		}
    	}
    }
    
    private void atualizarPrioridadeNormalDosClientes() {
    	int numClientes = filaClientes.size();
    	for(int i = 0; i < numClientes; i++) {
    		filaClientes.get(i).incrementarPrioridadeNormal();
    	}
    }
    
    private void atualizarOrdemDosClientesDaFila() {
    	int numClientesNaFila = filaClientes.size() - 1;
    	for(int i = 1; i <= numClientesNaFila; i++) {
    		filaClientes.get(i).setOrdem(i-1);
    	}
    }
    
    public int size() {
    	return filaClientes.size();
    }
 
}


