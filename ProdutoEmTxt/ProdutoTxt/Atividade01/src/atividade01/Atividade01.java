/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade01;

/**
 *
 * @author 0077116
 */
import javax.swing.JOptionPane;

public class Atividade01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ControleProduto controle = new ControleProduto();
        
        while (true) {
            String[] opcoes = {"Adicionar Produto", "Listar Produtos", "Sair"};
            int opcao = JOptionPane.showOptionDialog(null, 
                                                     "Escolha uma opção", 
                                                     "Controle de Produtos", 
                                                     JOptionPane.DEFAULT_OPTION, 
                                                     JOptionPane.INFORMATION_MESSAGE, 
                                                     null, opcoes, opcoes[0]);

            if (opcao == 0) {
                try {
                    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto:"));
                    String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
                    double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do produto:"));
                    String unidade = JOptionPane.showInputDialog("Digite a unidade de medida do produto:");
                    int estoque = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque:"));
                    
                    Produto produto = new Produto(codigo, nome, preco, unidade, estoque);
                    controle.adicionarProduto(produto);
                    JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir dados! Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } 
            else if (opcao == 1) {
                String produtos = controle.getProdutos();
                if (produtos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Não há produtos cadastrados.");
                } else {
                    JOptionPane.showMessageDialog(null, produtos);
                }
            } 
            else if (opcao == 2) {
                JOptionPane.showMessageDialog(null, "Sistema encerrado.");
                break;
            }
        }
    }
}
    