package edu.sena.bibliotecaspring.model;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Libro extends ElementoBiblioteca {
    private String autor;
    private String isbn;
    private int numeroPaginas;


    //Constructores
    public Libro() {
    }

    public Libro(String titulo, LocalDate fechaPublicacion, String autor, String isbn, int numeroPaginas) {
        super(titulo, fechaPublicacion);
        this.autor = autor;
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
    }
    //Métodos Getter y Setter
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
}
