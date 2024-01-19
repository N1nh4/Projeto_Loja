public class Eletronicos extends Produto {
    private int consumoEmWatts;
    public Eletronicos(String codigo, int consumoEmWatts, String nome, double preco) {
        super(codigo, nome, preco);
        this.consumoEmWatts = consumoEmWatts;
    }

    public int getConsumoEmWatts() {
      return this.consumoEmWatts;
    }
    public void setConsumoEmWatts(int value) {
      this.consumoEmWatts = value;
    }
    
    @Override
    public double calculaValorFinal() {
        return this.preco + (this.consumoEmWatts / 10);
    }
}
