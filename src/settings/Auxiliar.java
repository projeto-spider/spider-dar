
package settings;

/**
 *
 * @author Bleno Vale
 */
public class Auxiliar implements Comparable<Auxiliar>{ 

    private int id;
    private String alternativa;
    private float satifacao;
    private int posicao;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    public float getSatifacao() {
        return satifacao;
    }

    public void setSatifacao(float satifacao) {
        this.satifacao = satifacao;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    @Override
    public int compareTo(Auxiliar aux) { 
        if (this.satifacao > aux.satifacao) {
            return -1;
        }
        if (this.satifacao > this.satifacao) {
            return 1;
        }
        return 0;

    }
}
