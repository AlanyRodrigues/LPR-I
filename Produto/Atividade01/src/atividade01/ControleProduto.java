/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade01;

/**
 *
 * @author 0077116
 * 
 */
import java.util.ArrayList;

public class ControleProduto {
    private ArrayList<Produto> produtos;

    public ControleProduto() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public String getProdutos() {
        StringBuilder sb = new StringBuilder();
        for (Produto p : produtos) {
            sb.append(p.descricaoProduto()).append("\n\n");
        }
        return sb.toString();
    }
}
