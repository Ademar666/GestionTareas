package java.com.gestiontareas.model;

import java.time.LocalDate; // opcional, solo para ejemplo

public class Tarea {
    private final int id;
    private String titulo;
    private boolean completada;
    private LocalDate fechaLimite; // atributo extra para demostrar

    public Tarea(int id, String titulo, LocalDate fechaLimite) {
        if (titulo == null || titulo.trim().isEmpty())
            throw new IllegalArgumentException("El título no puede estar vacío");
        this.id = id;
        this.titulo = titulo;
        this.completada = false;
        this.fechaLimite = fechaLimite;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public boolean isCompletada() { return completada; }
    public LocalDate getFechaLimite() { return fechaLimite; }

    public void completar() { this.completada = true; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    @Override
    public String toString() {
        String estado = completada ? "[X]" : "[ ]";
        return String.format("%d. %s %s (vencimiento: %s)", id, estado, titulo, fechaLimite);
    }
}