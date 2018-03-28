package mx.handel.ws.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * @author Marco Antonio Salazar
 * The Embeddable class for the permiso database table.
 * 
 */
@Embeddable
public class WSPermisoPK implements Serializable {
	private static final long serialVersionUID = 1L;

    @Column(name = "id_servicio")
    private Integer idServicio;
    @Column(name = "id_usuario_tipo")
    private Integer idUsuarioTipo;

	public WSPermisoPK() {
	}

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idServicio;
        hash += (int) idUsuarioTipo;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof WSPermisoPK))
        {
            return false;
        }
        WSPermisoPK other = (WSPermisoPK) object;
        if (this.idServicio != other.idServicio)
        {
            return false;
        }
        if (this.idUsuarioTipo != other.idUsuarioTipo)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.WSPermisoPK[ idServicio=" + idServicio + ", idUsuarioTipo=" + idUsuarioTipo + " ]";
    }

}