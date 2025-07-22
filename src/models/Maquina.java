package models;

import java.util.List;

public class Maquina {
    private String nombre;
    private String ip;
    private List<Integer> codigos;


    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
    }


    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }
    
    public int getSubred() {
        String[] partes = ip.split("\\.");
        return Integer.parseInt(partes[2]);
    }

    
    public int getRiesgo() {
        int sumaCodigos = codigos.stream().filter(c -> c % 5 == 0).mapToInt(Integer::intValue).sum();
        long caracteresUnicos = nombre.replace(" ", "").chars().distinct().count();
        return (int) (sumaCodigos * caracteresUnicos);
    }
    @Override
    public String toString() {
        return nombre + " — " + ip + " (subred: " + getSubred() + ", riesgo: " + getRiesgo() + ")";
    }
}
