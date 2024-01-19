import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        // Pegando a data atual do sistema
        // criamos um locale para o Brasil
        Locale localBR = new Locale("pt", "BR");

        // obter a data e hora atual
        Calendar agora = Calendar.getInstance();

        // formatar de acordo com o formato de
        // data longa no português brasileiro
        DateFormat formatoDeData = DateFormat.getDateInstance(DateFormat.FULL, localBR);

        // Declaraçao da variavel menu do tipo integer
        int menu = 0;

        // Instanciando um novo objeto
        // da classe produtos
        Loja loja1 = new Loja("nome qualuqer", "endereco qualque");

        do {
            String menuOpcoes = " Sistema De Cadastro De Produtos: Mercadinho   \n\n"
                    + formatoDeData.format(agora.getTime()) + "\n\nQual item deseja escolher? " + "\n\n 1 - Cadastrar Produtos (Eletonicos/Vestuario)"
                    + "\n 2 - Exibir Produtos "  + "\n 3 - Alterar Produtos "
                    + "\n 4 - Excluir Produtos " + "\n 5 - Sair \n\n";

            try {
                menu = Integer.parseInt(JOptionPane.showInputDialog(null, menuOpcoes));

                switch (menu) {
                    case 1:
                        cadastrarProduto(loja1);
                        break;
                    case 2:
                        exibirProdutos(loja1);
                        break;
                    case 3:
                        alterarProduto(loja1);
                        break;
                    case 4:
                        excluirProduto(loja1);
                        break;
                    case 5:
                        sairPrograma();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Por favor escolha uma opção correta entre 1 e 5!",
                                "Cadastro De Produtos: Mercadinho", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido!",
                        "Cadastro De Produtos: Mercadinho", JOptionPane.ERROR_MESSAGE);
            }

        } while (menu != 0);
    }

    private static void cadastrarProduto(Loja loja) {
        try {
            String codigo;
            boolean codigoExistente;

            do {
                codigo = JOptionPane.showInputDialog(null, "Digite o código do produto: ");
                codigoExistente = loja.verificarCodigoExistente(codigo);

                if (codigoExistente) {
                    JOptionPane.showMessageDialog(null, "Código já existente. Por favor, insira um código diferente.",
                            "Cadastro De Produtos: Mercadinho", JOptionPane.ERROR_MESSAGE);
                }

            } while (codigoExistente);

            String nome = JOptionPane.showInputDialog(null, "Digite o nome do produto: ");
            double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o preço do produto: "));

            String tipoProduto = JOptionPane.showInputDialog(null,
                    "Digite o tipo do produto (V para Vestuário, E para Eletrônicos): ").toUpperCase();

            if (tipoProduto.equals("V")) {
                String tamanho = JOptionPane.showInputDialog(null, "Digite o tamanho do produto (para Vestuário): ").toUpperCase();
                Vestuario vestuario = new Vestuario(codigo, tamanho, nome, preco);
                loja.setProdutos(vestuario);
            } else if (tipoProduto.equals("E")) {
                int consumoEmWatts = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o consumo em watts (para Eletrônicos): "));
                Eletronicos eletronico = new Eletronicos(codigo, consumoEmWatts, nome, preco);
                loja.setProdutos(eletronico);
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de produto inválido!", "Cadastro De Produtos: Mercadinho",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Um dos dados está incorreto!", "Cadastro De Produtos: Mercadinho",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    private static void exibirProdutos(Loja loja) {
        StringBuilder listaProdutos = new StringBuilder("Lista do(s) produto(s) cadastrado(s):\n");

        for (Produto produto : loja.getProdutos()) {
            listaProdutos.append("Código: ").append(produto.getCodigo())
                    .append(" | Nome: ").append(produto.getNome())
                    .append(" | Preço: R$ ").append(produto.getPreco());

            // Adicionar informações específicas do tipo de produto
            if (produto instanceof Vestuario) {
                Vestuario vestuario = (Vestuario) produto;
                listaProdutos.append(" | Tamanho: ").append(vestuario.getTamanho());
                listaProdutos.append(" | Valor Final: R$ ").append(vestuario.calculaValorFinal());
            } else if (produto instanceof Eletronicos) {
                Eletronicos eletronico = (Eletronicos) produto;
                listaProdutos.append(" | Consumo em Watts: ").append(eletronico.getConsumoEmWatts());
                listaProdutos.append(" | Valor Final: R$ ").append(eletronico.calculaValorFinal());
            }

            listaProdutos.append("\n");
        }

        JOptionPane.showMessageDialog(null, listaProdutos.toString(), "Cadastro De Produtos: Mercadinho",
                JOptionPane.INFORMATION_MESSAGE);
    }


    private static void alterarProduto(Loja loja) {
        String codigoAlterar = JOptionPane.showInputDialog(null, "Digite o código do produto a ser alterado: ");
        String novaDescricao = JOptionPane.showInputDialog(null, "Digite o novo nome do produto: ");
        double novoPreco = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o novo preço do produto: "));
        loja.alterarProduto(codigoAlterar, novaDescricao, novoPreco);
    }

    private static void excluirProduto(Loja loja) {
        String codigoExcluir = JOptionPane.showInputDialog(null, "Digite o código do produto a ser excluído: ");
        loja.excluirProduto(codigoExcluir);
        JOptionPane.showMessageDialog(null, "Produto excluído com sucesso.", "Cadastro De Produtos: Mercadinho",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void sairPrograma() {
        int sair = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do programa?", "Mercadinho",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}






