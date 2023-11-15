package entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "museos", schema = "museos23")
public class MuseosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;
    @Basic
    @Column(name = "direccion", nullable = true, length = 200)
    private String direccion;
    @Basic
    @Column(name = "ciudad", nullable = true, length = 200)
    private String ciudad;
    @Basic
    @Column(name = "pais", nullable = true, length = 200)
    private String pais;
    @OneToMany(mappedBy = "museosByIdMuseo")
    private Collection<ObrasEntity> obrasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MuseosEntity that = (MuseosEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (direccion != null ? !direccion.equals(that.direccion) : that.direccion != null) return false;
        if (ciudad != null ? !ciudad.equals(that.ciudad) : that.ciudad != null) return false;
        if (pais != null ? !pais.equals(that.pais) : that.pais != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        return result;
    }

    public Collection<ObrasEntity> getObrasById() {
        return obrasById;
    }

    public void setObrasById(Collection<ObrasEntity> obrasById) {
        this.obrasById = obrasById;
    }
}
