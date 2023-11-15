package entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "autores", schema = "museos23")
public class AutoresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nombre", nullable = true, length = 200)
    private String nombre;
    @Basic
    @Column(name = "fecha_nacimiento", nullable = true)
    private Date fechaNacimiento;
    @Basic
    @Column(name = "fecha_fallecimiento", nullable = true)
    private Date fechaFallecimiento;
    @Basic
    @Column(name = "nacionalidad", nullable = true, length = 200)
    private String nacionalidad;
    @OneToMany(mappedBy = "autoresByIdAutor")
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoresEntity that = (AutoresEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(that.fechaNacimiento) : that.fechaNacimiento != null)
            return false;
        if (fechaFallecimiento != null ? !fechaFallecimiento.equals(that.fechaFallecimiento) : that.fechaFallecimiento != null)
            return false;
        if (nacionalidad != null ? !nacionalidad.equals(that.nacionalidad) : that.nacionalidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        result = 31 * result + (fechaFallecimiento != null ? fechaFallecimiento.hashCode() : 0);
        result = 31 * result + (nacionalidad != null ? nacionalidad.hashCode() : 0);
        return result;
    }

    public Collection<ObrasEntity> getObrasById() {
        return obrasById;
    }

    public void setObrasById(Collection<ObrasEntity> obrasById) {
        this.obrasById = obrasById;
    }
}
