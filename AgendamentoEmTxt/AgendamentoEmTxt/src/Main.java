import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
            Agenda agenda = new Agenda();
            int opcao;

            do {
                String menu = """
                1. Adicionar Agendamento
                2. Buscar por Horário
                3. Buscar por Nome
                4. Excluir Agendamento
                5. Ver Todos os Agendamentos
                0. Sair
                Escolha uma opção:
                """;
                opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

                switch (opcao) {
                    case 1 -> {
                        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da pessoa:"));
                        String nome = JOptionPane.showInputDialog("Digite o nome da pessoa:");
                        String telefone = JOptionPane.showInputDialog("Digite o telefone da pessoa:");
                        int hora = Integer.parseInt(JOptionPane.showInputDialog("Digite a hora do agendamento:"));

                        Pessoa pessoa = new Pessoa(codigo, nome, telefone);
                        Agendamento agendamento = new Agendamento(hora, pessoa);

                        if (agenda.adicionarAgendamento(agendamento)) {
                            JOptionPane.showMessageDialog(null, "Agendamento adicionado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Horário já ocupado!");
                        }
                    }
                    case 2 -> {
                        int hora = Integer.parseInt(JOptionPane.showInputDialog("Digite a hora para buscar:"));
                        Agendamento ag = agenda.
                                buscarPorHorario(hora);
                        if (ag != null) {
                            JOptionPane.showMessageDialog(null, ag.toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum agendamento encontrado nesse horário.");
                        }
                    }
                    case 3 -> {
                        String nome = JOptionPane.showInputDialog("Digite o nome para buscar:");
                        ArrayList<Agendamento> resultados = agenda.buscarPorNome(nome);
                        if (!resultados.isEmpty()) {
                            StringBuilder sb = new StringBuilder();
                            for (Agendamento ag : resultados) {
                                sb.append(ag.toString()).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, sb.toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum agendamento encontrado para esse nome.");
                        }
                    }
                    case 4 -> {
                        int hora = Integer.parseInt(JOptionPane.showInputDialog("Digite a hora do agendamento para excluir:"));
                        if (agenda.excluirAgendamento(hora)) {
                            JOptionPane.showMessageDialog(null, "Agendamento excluído com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum agendamento encontrado nesse horário.");
                        }
                    }
                    case 5 -> JOptionPane.showMessageDialog(null, agenda.toString());
                    case 0 -> JOptionPane.showMessageDialog(null, "Saindo...");
                    default -> JOptionPane.showMessageDialog(null, "Opção inválida!");
                }
            } while (opcao != 0);
        };
    }