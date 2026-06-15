package java.com.gestiontareas.service;

import java.com.gestiontareas.model.Tarea;
import java.com.gestiontareas.service.exception.TareaNoEncontradaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TareaService {
    private final Map<Integer, Tarea> tareas = new HashMap<>();
    private int siguienteId = 1;

    public Tarea agregarTarea(String titulo, LocalDate fechaLimite) {
        Tarea nueva = new Tarea(siguienteId++, titulo, fechaLimite);
        tareas.put(nueva.getId(), nueva);
        return nueva;
    }

    public List<Tarea> listarTareas() {
        return new ArrayList<>(tareas.values());
    }

    public void marcarComoCompletada(int id) throws TareaNoEncontradaException {
        Tarea t = tareas.get(id);
        if (t == null) throw new TareaNoEncontradaException("Tarea con ID " + id + " no existe.");
        t.completar();
    }

    public boolean existeTarea(int id) {
        return tareas.containsKey(id);
    }
}
