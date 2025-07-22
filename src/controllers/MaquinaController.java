package controllers;

import java.util.*;
import models.Maquina;

public class MaquinaController {


    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> resultado = new Stack<>();
        for (Maquina maquina : maquinas) {
            if (maquina.getSubred() > umbral) {
                resultado.push(maquina);
            }
        }
        return resultado;
    }


    public TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        TreeSet<Maquina> resultado = new TreeSet<>((m1, m2) -> {
            int subredCompare = Integer.compare(m2.getSubred(), m1.getSubred());
            if (subredCompare != 0) return subredCompare;


            return m1.getNombre().compareTo(m2.getNombre());
        });
        resultado.addAll(pila);
        return resultado;
    }


    public TreeMap<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
        TreeMap<Integer, Queue<Maquina>> resultado = new TreeMap<>();
        for (Maquina maquina : maquinas) {
            int riesgo = maquina.getRiesgo();
            resultado.putIfAbsent(riesgo, new LinkedList<>());
            resultado.get(riesgo).add(maquina);
        }
        return resultado;
    }


    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
    int maxCantidad = 0;
    int maxRiesgo = -1;
    Queue<Maquina> grupoSeleccionado = null;

    for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
        int cantidad = entry.getValue().size();
        int riesgo = entry.getKey();

        if (cantidad > maxCantidad || (cantidad == maxCantidad && riesgo > maxRiesgo)) {
            maxCantidad = cantidad;
            maxRiesgo = riesgo;
            grupoSeleccionado = entry.getValue();
        }
    }

    Stack<Maquina> resultado = new Stack<>();
    if (grupoSeleccionado != null) {
        while (!grupoSeleccionado.isEmpty()) {
            resultado.push(grupoSeleccionado.poll());
        }
    }
    return resultado;
    }
}
