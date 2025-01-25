import java.util.ArrayList;

public class Agenda {
    private ArrayList<Agendamento> agendamentos;

    public Agenda() {
        agendamentos = new ArrayList<>();
    }

    public boolean adicionarAgendamento(Agendamento agendamento) {
        if (verificarHorario(agendamento.getHora())) {
            agendamentos.add(agendamento);
            return true;
        } else {
            return false;
        }
    }

    public boolean verificarHorario(int hora) {
        for (Agendamento ag : agendamentos) {
            if (ag.getHora() == hora) {
                return false;
            }
        }
        return true;
    }

    public Agendamento buscarPorHorario(int hora) {
        for (Agendamento ag : agendamentos) {
            if (ag.getHora() == hora) {
                return ag;
            }
        }
        return null;
    }

    public ArrayList<Agendamento> buscarPorNome(String nome) {
        ArrayList<Agendamento> resultados = new ArrayList<>();
        for (Agendamento ag : agendamentos) {
            if (ag.getPessoa().getNome().equalsIgnoreCase(nome)) {
                resultados.add(ag);
            }
        }
        return resultados;
    }

    public boolean excluirAgendamento(int hora) {
        return agendamentos.removeIf(ag -> ag.getHora() == hora);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Agendamento ag : agendamentos) {
            sb.append(ag.toString()).append("\n");
        }
        return sb.toString();
    }
}
