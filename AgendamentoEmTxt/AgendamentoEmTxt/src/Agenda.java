import java.io.*;
import java.util.ArrayList;

public class Agenda {
    private final String arquivo = "agendamentos.txt";

    public Agenda() {
        // Garante que o arquivo exista
        try {
            File file = new File(arquivo);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Erro ao inicializar o arquivo: " + e.getMessage());
        }
    }

    public boolean adicionarAgendamento(Agendamento agendamento) {
        if (verificarHorario(agendamento.getHora())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
                bw.write(agendamento.toString());
                bw.newLine();
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao adicionar agendamento: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean verificarHorario(int hora) {
        ArrayList<Agendamento> agendamentos = lerAgendamentos();
        for (Agendamento ag : agendamentos) {
            if (ag.getHora() == hora) {
                return false;
            }
        }
        return true;
    }

    public Agendamento buscarPorHorario(int hora) {
        ArrayList<Agendamento> agendamentos = lerAgendamentos();
        for (Agendamento ag : agendamentos) {
            if (ag.getHora() == hora) {
                return ag;
            }
        }
        return null;
    }

    public ArrayList<Agendamento> buscarPorNome(String nome) {
        ArrayList<Agendamento> resultados = new ArrayList<>();
        ArrayList<Agendamento> agendamentos = lerAgendamentos();
        for (Agendamento ag : agendamentos) {
            if (ag.getPessoa().getNome().equalsIgnoreCase(nome)) {
                resultados.add(ag);
            }
        }
        return resultados;
    }

    public boolean excluirAgendamento(int hora) {
        ArrayList<Agendamento> agendamentos = lerAgendamentos();
        boolean removido = agendamentos.removeIf(ag -> ag.getHora() == hora);

        if (removido) {
            escreverAgendamentos(agendamentos);
        }
        return removido;
    }

    @Override
    public String toString() {
        ArrayList<Agendamento> agendamentos = lerAgendamentos();
        StringBuilder sb = new StringBuilder();
        for (Agendamento ag : agendamentos) {
            sb.append(ag.toString()).append("\n");
        }
        return sb.toString();
    }

    private ArrayList<Agendamento> lerAgendamentos() {
        ArrayList<Agendamento> agendamentos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Agendamento ag = parseAgendamento(linha);
                if (ag != null) {
                    agendamentos.add(ag);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler agendamentos: " + e.getMessage());
        }
        return agendamentos;
    }

    private void escreverAgendamentos(ArrayList<Agendamento> agendamentos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (Agendamento ag : agendamentos) {
                bw.write(ag.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever agendamentos: " + e.getMessage());
        }
    }

    private Agendamento parseAgendamento(String linha) {
        try {
            String[] partes = linha.split(", ");
            int hora = Integer.parseInt(partes[0].split(": ")[1]);
            String codigoStr = partes[1].split(": ")[1];
            int codigo = Integer.parseInt(partes[1].split(": ")[1]);
            String nome = partes[2].split(": ")[1];
            String telefone = partes[3].split(": ")[1];
            Pessoa pessoa = new Pessoa(codigo, nome, telefone);
            return new Agendamento(hora, pessoa);
        } catch (Exception e) {
            System.err.println("Erro ao interpretar linha: " + linha);
        }
        return null;
    }
}
