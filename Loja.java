import javax.swing.*;
import java.util.ArrayList;

public class Loja {
  private String nome;
  private String endereco;
  private ArrayList<Produto> produtos = new ArrayList<>();
  //private Produto[] produtos;

  public Loja(String nome, String endereco) {
      this.nome = nome;
      this.endereco = endereco;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String value) {
    this.nome = value;
  }
  public String getEndereco() {
    return this.endereco;
  }
  public void setEndereco(String value) {
    this.endereco = value;
  }
  public ArrayList<Produto> getProdutos() {
    return this.produtos;
  }
  public void setProdutos(Produto value) {
    this.produtos.add(value);
  }


  public void alterarProduto(String codigo, String novaDescricao, double novoPreco) {
    for (Produto produto : this.produtos) {
      if (produto.getCodigo().equals(codigo)) {
        produto.setNome(novaDescricao);
        produto.setPreco(novoPreco);

        if (produto instanceof Vestuario) {
          // Se for produto de vestuário, pergunte ao usuário se deseja alterar o tamanho
          int opcaoAlterarTamanho = JOptionPane.showConfirmDialog(
                  null, "Deseja alterar o tamanho do produto?", "Alteração de Tamanho",
                  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

          if (opcaoAlterarTamanho == JOptionPane.YES_OPTION) {
            String novoTamanho = JOptionPane.showInputDialog(null, "Digite o novo tamanho: ");
            ((Vestuario) produto).setTamanho(novoTamanho);
          }
        } else if (produto instanceof Eletronicos) {
          // Se for produto eletrônico, pergunte ao usuário se deseja alterar o consumo de watts
          int opcaoAlterarConsumo = JOptionPane.showConfirmDialog(
                  null, "Deseja alterar o consumo em watts do produto eletrônico?", "Alteração de Consumo",
                  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

          if (opcaoAlterarConsumo == JOptionPane.YES_OPTION) {
            int novoConsumo = Integer.parseInt(JOptionPane.showInputDialog(
                    null, "Digite o novo consumo em watts: "));
            ((Eletronicos) produto).setConsumoEmWatts(novoConsumo);
          }
        }

        JOptionPane.showMessageDialog(null, "Produto alterado com sucesso.", "Cadastro De Produtos: Mercadinho", JOptionPane.INFORMATION_MESSAGE);
        return;
      }
    }

    JOptionPane.showMessageDialog(null, "Produto não encontrado.", "Cadastro De Produtos: Mercadinho", JOptionPane.ERROR_MESSAGE);
  }

  public void excluirProduto(String codigoExcluir) {
    Produto produtoParaExcluir = null;

    for (Produto produto : this.produtos) {
      if (produto.getCodigo().equals(codigoExcluir)) {
        produtoParaExcluir = produto;
        break;
      }
    }

    if (produtoParaExcluir != null) {
      this.produtos.remove(produtoParaExcluir);
      JOptionPane.showMessageDialog(null, "Produto excluído com sucesso.", "Cadastro De Produtos: Mercadinho", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, "Produto não encontrado.", "Cadastro De Produtos: Mercadinho", JOptionPane.ERROR_MESSAGE);
    }
  }

  public boolean verificarCodigoExistente(String codigo) {
    for (Produto produto : this.produtos) {
      if (produto.getCodigo().equals(codigo)) {
        return true; // Código já existe
      }
    }
   // Código não existe
      return false;
  }

}
