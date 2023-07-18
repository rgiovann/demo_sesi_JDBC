package org.example.model;

import java.util.Date;

/**
 * Represents an event in the database table "evento".
 */
public class Evento {

    private Integer id;

    private String nome;

    private String nomeAdmin;

    private Date data;

    /**
     * Default constructor for the Evento class.
     */
    public Evento() {}

    /**
     * Constructs an Evento object with the specified parameters.
     *
     * @param id        The ID of the event.
     * @param nome      The name of the event.
     * @param nomeAdmin The name of the event administrator.
     * @param data      The date of the event.
     */
    public Evento(Integer id, String nome, String nomeAdmin, Date data) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.nomeAdmin = nomeAdmin;
    }

    /**
     * Retrieves the ID of the event.
     *
     * @return The ID of the event.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the event.
     *
     * @param id The ID of the event.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the event.
     *
     * @return The name of the event.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retrieves the name of the event administrator.
     *
     * @return The name of the event administrator.
     */
    public String getNomeAdmin() {
        return this.nomeAdmin;
    }

    /**
     * Sets the name of the event administrator.
     *
     * @param nomeAdmin The name of the event administrator.
     */
    public void setNomeAdmin(String nomeAdmin) {
        this.nomeAdmin = nomeAdmin;
    }

    /**
     * Sets the name of the event.
     *
     * @param nome The name of the event.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retrieves the date of the event.
     *
     * @return The date of the event.
     */
    public Date getData() {
        return this.data;
    }

    /**
     * Sets the date of the event.
     *
     * @param data The date of the event.
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Generates a hash code for the Evento object based on its ID.
     *
     * @return The hash code value for the Evento object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Checks if the Evento object is equal to another object.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Evento other = (Evento) obj;

        if (id == null || other.id == null) {
            return false;
        }

        return id.equals(other.id);
    }
}