public abstract class Produto {
    protected String codigo;
    protected String nome;
    protected double preco;

    public Produto(String codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
      return this.nome;
    }
    public void setNome(String value) {
      this.nome = value;
    }

    public double getPreco() {
      return this.preco;
    }
    public void setPreco(double value) {
      this.preco = value;
    }

    public double calculaValorFinal() {
        return this.preco;
    }

    public String getCodigo() {
        return this.codigo;
    }
}
