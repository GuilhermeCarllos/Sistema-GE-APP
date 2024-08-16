public class Evento {
    String nome;
    String data;
    Participante[] participantes;
    int numeroParticipantes;
    Local local;

    public Evento(String nome, String data, Local local, int capacidadeMaxima) {
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.participantes = new Participante[capacidadeMaxima];
        this.numeroParticipantes = 0;
    }

    public void adicionarParticipante(Participante participante) {
        if (numeroParticipantes < participantes.length) {
            this.participantes[numeroParticipantes++] = participante;
        } else {
            System.out.println("Capacidade máxima de participantes atingida!");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Evento{")
                .append("nome='").append(nome).append('\'')
                .append(", data='").append(data).append('\'')
                .append(", local=").append(local.nome)
                .append(", participantes=[");
        for (int i = 0; i < numeroParticipantes; i++) {
            sb.append(participantes[i].toString());
            if (i < numeroParticipantes - 1) sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
