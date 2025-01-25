public class Agendamento {
    private int hora;
    private Pessoa pessoa;

    public Agendamento(int hora, Pessoa pessoa) {
        this.hora = hora;
        this.pessoa = pessoa;
    }

    public int getHora() {
        return hora;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    @Override
    public String toString() {
        return "Hora: " + hora + ", Pessoa: [" + pessoa.toString() + "]";
    }
}
