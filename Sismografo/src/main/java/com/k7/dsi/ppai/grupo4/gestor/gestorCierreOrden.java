package com.k7.dsi.ppai.grupo4.gestor;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.k7.dsi.ppai.grupo4.entidades.*;
public class gestorCierreOrden {
    private usuario responsableInspecciones;
    private ArrayList<Integer> nroOrden;
    private ArrayList<String> fechaHoraFinalizacion;
    private ArrayList<String> nombreEstacion;
    private ArrayList<String> identificadorSismografo;


    public void conocerRI(sesion sesion) {
        this.responsableInspecciones = sesion.conocerRI().getRIlogeado();

    }

    public void buscarOrdenesRealizadas(usuario responsableInspecciones , ArrayList<ordenInspeccion> ordenesRealizadas, ArrayList<sismografo> sismografos) {
        for (ordenInspeccion orden : ordenesRealizadas) {
            if (this.validarEmpleadoLog(orden.getEmpleado())) {
                if(this.estaRealizada(orden)){
                    this.nroOrden.add(orden.getNumeroOrden());
                    this.fechaHoraFinalizacion.add(orden.getFechaCierre());
                    this.buscarEstacionSismonologica(orden, sismografos);
                }
            }     
        }
    
    }

    public boolean  validarEmpleadoLog(empleado empleadoOrden) {
        if (empleadoOrden.equals(this.responsableInspecciones.getEmpleado())) {
            return true;
        } else {
            return false;
        }

    }
    public boolean estaRealizada(ordenInspeccion orden) {
        if (orden.getEstado().getNombreEstado().equals("Realizada")) {
            return true;
        } else {
            return false;
        }
    }
    public void buscarEstacionSismonologica(ordenInspeccion orden, ArrayList<sismografo> sismografos) {
        this.nombreEstacion.add(orden.getNombre());
        for (sismografo sismografo : sismografos) {
            if (sismografo.getEstacionSismonologica().equals(orden.getEstacionSismonologica())) {
                this.identificadorSismografo.add(orden.getEstacionSismonologica().getIdentificadorSismografo(sismografo));
            } else {
                this.identificadorSismografo.add("No se encontro el sismografo");
            }
        }

    }
    
}
