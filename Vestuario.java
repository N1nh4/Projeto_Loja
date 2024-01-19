public class Vestuario extends Produto{

    private String tamanho;

    public Vestuario(String codigo, String tamanho, String nome, double preco) {
        super(codigo, nome, preco);
        this.tamanho = tamanho;
    }   

    public String getTamanho() {
      return this.tamanho;
    }
    public void setTamanho(String value) {
      this.tamanho = value;
    }

    @Override
    public double calculaValorFinal() {
        if ("G".equals(this.tamanho)) {
            return this.preco + 3;
        } else if ("M".equals(this.tamanho)) {
            return this.preco + 2;
        } else if ("P".equals(this.tamanho)) {
            return this.preco + 1;
        }

        return this.preco; // Se o tamanho não for G, M ou P, retorna o preço original
    }
}
