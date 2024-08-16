public class Local {
    String nome;
    Evento[] eventos;
    int numeroEventos;

    public Local(String nome, int capacidadeMaxima) {
        this.nome = nome;
        this.eventos = new Evento[capacidadeMaxima];
        this.numeroEventos = 0;
    }

    public void adicionarEvento(Evento evento) {
        if (numeroEventos < eventos.length) {
            this.eventos[numeroEventos++] = evento;
        } else {
            System.out.println("Capacidade máxima do evento atingida!");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Local{")
                .append("nome='").append(nome).append('\'')
                .append(", eventos=[");
        for (int i = 0; i < numeroEventos; i++) {
            sb.append(eventos[i].toString());
            if (i < numeroEventos - 1) sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
